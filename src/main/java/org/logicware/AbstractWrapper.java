/*
 * #%L
 * prolobjectlink-jpe
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware;

/**
 * Common implementation for {@link Wrapper} interface.
 * 
 * @author Jose Zalacain
 * @see Wrapper
 * @since 1.0
 */
public class AbstractWrapper implements Wrapper {

	public final <K> K unwrap(Class<K> cls) {
		return unwrap(this, cls);
	}

	public final <K> K unwrap(Object o, Class<K> cls) {
		try {
			if (cls.isAssignableFrom(o.getClass())) {
				return cls.cast(o);
			}
		} catch (Exception e) {
			throw new RuntimeError(

					"Impossible unwrap " + cls

							+ " to " + cls.getName()

					, e);
		}
		return null;
	}

	public final boolean isWrappedFor(Class<?> cls) {
		return cls.isInstance(this);
	}

	public final boolean isWrappedFor(Object o, Class<?> cls) {
		return cls.isInstance(o);
	}

}
