/*
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Prolobjectlink Project nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package io.github.prolobjectlink.prolog;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class ReflectionUtil {

	/**
	 * Check if the given java class member (filed, method or constructor) is
	 * accessible or not.
	 * 
	 * @param member java member (filed, method or constructor) to be checked
	 * @return true if the given java class member is accessible, false if not.
	 * @since 1.0
	 */
	public static boolean isAccessible(AccessibleObject member) {
		return member.isAccessible();
	}

	public static void doAccessibleIfNeed(AccessibleObject member) {
		if (!isAccessible(member)) {
			member.setAccessible(true);
		}
	}

	public static void undoAccessibleIfNeed(AccessibleObject member) {
		if (!isAccessible(member)) {
			member.setAccessible(false);
		}
	}

	public static Class<?> classForName(String className) {
		Class<?> clazz = null;
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		}
		return clazz;
	}

	/**
	 * Find some constructor for create an empty instance. This method create an
	 * object instance under critical situations. There are non-empty declared
	 * constructor, restricted access for alternative constructor with any number
	 * and type of parameters. The alternative constructor with any number and type
	 * of parameter will be create with an array with the default value (0 for
	 * primitive types, false for boolean and null for any class type). This is the
	 * way for invoke {@link Constructor#newInstance(Object...)}. The public empty
	 * constructor simplify the situation because no have additional parameters
	 * analysis.
	 * 
	 * @param clazz type to be create an instance
	 * @return instance from given class.
	 * @since 1.0
	 */
	public static Object newInstance(Class<?> clazz) {
		Object instance = null;
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		if (constructors.length > 0) {
			Constructor<?> constructor = constructors[0];
			Class<?>[] classes = constructor.getParameterTypes();
			Object[] arguments = new Object[classes.length];
			for (int i = 0; i < classes.length; i++) {
				if (classes[i] == String.class) {
					arguments[i] = "";
				} else if (classes[i] == boolean.class) {
					arguments[i] = false;
				} else if (classes[i].isPrimitive()) {
					arguments[i] = 0;
				}
			}
			try {

				doAccessibleIfNeed(constructor);
				instance = constructor.newInstance(arguments);
				undoAccessibleIfNeed(constructor);

			} catch (InstantiationException e) {
				Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
			} catch (IllegalAccessException e) {
				Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
			} catch (InvocationTargetException e) {

				// This exception is reported when a constructor not
				// initialize directly yours fields, but make the job

			}

		}
		return instance;
	}

	public static <T> T newInstance(Class<T> cls, Class<?>[] parameterTypes, Object[] parameterValues) {
		T value = null;
		try {
			Constructor<T> constructor = cls.getDeclaredConstructor(parameterTypes);
			doAccessibleIfNeed(constructor);
			value = constructor.newInstance(parameterValues);
			undoAccessibleIfNeed(constructor);
		} catch (InstantiationException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (IllegalAccessException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (SecurityException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (InvocationTargetException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		}
		return value;
	}

	/**
	 * Read field value given some object owner
	 * 
	 * @param field  class field for read object value
	 * @param object object that contain the filed to read
	 * @return object value read from given field
	 * @since 1.0
	 */
	public static Object readValue(Field field, Object object) {
		Object value = null;
		try {
			doAccessibleIfNeed(field);
			value = field.get(object);
			undoAccessibleIfNeed(field);
		} catch (IllegalArgumentException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (IllegalAccessException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		}
		return value;
	}

	/**
	 * Write in a field value given some owner object and object value. This method
	 * treated an special case when try write an object array. In this case the
	 * array will be a copy with the array component type expected and defined in
	 * the filed.
	 * 
	 * @param field  class field for write object value
	 * @param object object field owner where will be wrote the value
	 * @param value  value to be write.
	 * @since 1.0
	 */
	public static void writeValue(Field field, Object object, Object value) {
		try {

			Class<?> clazz = field.getType();
			if (value == null) {
				if (clazz == boolean.class) {
					value = false;
				} else if (clazz == int.class) {
					value = 0;
				} else if (clazz == long.class) {
					value = 0L;
				} else if (clazz == float.class) {
					value = 0F;
				} else if (clazz == double.class) {
					value = 0D;
				}
			} else if (value instanceof Object[] && clazz != Object[].class) {
				value = castComponentType((Object[]) value, clazz);
			}

			doAccessibleIfNeed(field);
			field.set(object, value);
			undoAccessibleIfNeed(field);
		} catch (IllegalArgumentException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (IllegalAccessException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		}
	}

	/**
	 * Make a copy from given array to new array whit cast component type.
	 * 
	 * 
	 * @param array object array to be copied
	 * @param type  new component type
	 * @return new array of the class type
	 * @since 1.0
	 */
	public static Object castComponentType(Object[] array, Class<?> type) {
		Class<? extends Object[]> clazz = (Class<? extends Object[]>) type;
		return Arrays.copyOf(array, array.length, clazz);
	}

	public static Field getDeclaredField(Class<?> clazz, String attributeName) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(attributeName);
		} catch (NoSuchFieldException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (SecurityException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		}
		return field;
	}

	public static Method getDeclaredMethod(Class<? extends Object> class1, String methodName,
			Class<?>... parameterTypes) {
		Method method = null;
		try {
			Method[] methods = class1.getDeclaredMethods();
			for (Method method2 : methods) {
				if (method2.getName().equals(methodName)) {
					Class<?>[] types = method2.getParameterTypes();
					if (Arrays.equals(types, parameterTypes)) {
						method = class1.getDeclaredMethod(methodName, parameterTypes);
					} else {
						method = method2;
					}
				}
			}
		} catch (NoSuchMethodException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (SecurityException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		}
		return method;
	}

	/**
	 * Invoke a method from some given owner object with given arguments.
	 * 
	 * @param object    method owner.
	 * @param method    method to be invoke.
	 * @param arguments signature method arguments required.
	 * @return object result of invoke the given method.
	 */
	public static Object invoke(Object object, Method method, Object... arguments) {
		Object ret = null;
		try {
			doAccessibleIfNeed(method);
			ret = method.invoke(object, arguments);
			undoAccessibleIfNeed(method);
		} catch (IllegalArgumentException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (IllegalAccessException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		} catch (InvocationTargetException e) {
			Prolog.getProvider().getLogger().error(ReflectionUtil.class, e.getMessage(), e);
		}
		return ret;
	}

	protected ReflectionUtil() {
	}

}
