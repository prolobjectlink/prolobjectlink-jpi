/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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

import static io.github.prolobjectlink.prolog.PrologTermType.MAP_ENTRY_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.MAP_TYPE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class PrologInternalMap extends PrologLinkedMap<PrologTerm, PrologTerm> implements PrologMap {

	// replace this provider by passed one in constructors
	private static final PrologProvider provider = Prolog.getProvider();
	private static final long serialVersionUID = -3388690044067536880L;
	private static final PrologTerm nil = provider.prologNil();

	PrologInternalMap() {
		this(16);
	}

	PrologInternalMap(int initialCapacity) {
		table = new HashEntry[initialCapacity];
		Arrays.fill(table, nil);
	}

	PrologInternalMap(Map<? extends PrologTerm, ? extends PrologTerm> m) {
		if (m != null) {
			table = new HashEntry[m.size()];
			Arrays.fill(table, nil);
			putAll(m);
		}
	}

	protected PrologHashEntry[] getArgs() {
		return (PrologHashEntry[]) table;
	}

	protected PrologHashEntry getArgt(int index) {
		checkIndex(index, size);
		return (PrologHashEntry) table[index];
	}

	public PrologTerm getHead() {
		return iterator().next();
	}

	public PrologTerm getTail() {
		// try to implement a specific view ???
		PrologInternalMap m = new PrologInternalMap(this);
		m.remove(((Entry<?, ?>) getHead()).getKey());
		return m;
	}

	public String getIndicator() {
		return getFunctor() + "/" + getArity();
	}

	public boolean hasIndicator(String functor, int arity) {
		return getFunctor().equals(functor) && getArity() == arity;
	}

	public int getType() {
		return MAP_TYPE;
	}

	public boolean isAtom() {
		return false;
	}

	public boolean isNumber() {
		return false;
	}

	public boolean isFloat() {
		return false;
	}

	public boolean isInteger() {
		return false;
	}

	public boolean isDouble() {
		return false;
	}

	public boolean isLong() {
		return false;
	}

	public boolean isVariable() {
		return false;
	}

	public final boolean isVariableBound() {
		return false;
	}

	public final boolean isVariableNotBound() {
		return false;
	}

	public boolean isNil() {
		return false;
	}

	public boolean isAtomic() {
		return false;
	}

	public boolean isCompound() {
		return true;
	}

	public boolean isEvaluable() {
		return false;
	}

	public boolean isTrueType() {
		return false;
	}

	public boolean isFalseType() {
		return false;
	}

	public boolean isNullType() {
		return false;
	}

	public boolean isVoidType() {
		return false;
	}

	public boolean isObjectType() {
		return false;
	}

	public boolean isReference() {
		return false;
	}

	public boolean isEntry() {
		return false;
	}

	public boolean isMap() {
		return true;
	}

	public boolean isField() {
		return false;
	}

	public boolean isMixin() {
		return false;
	}

	public boolean isClass() {
		return false;
	}

	public Object getObject() {
		// can be returned the current instance ???
		return new LinkedHashMap<PrologTerm, PrologTerm>(this);
	}

	public PrologTerm getTerm() {
		return this;
	}

	public PrologTerm[] getArguments() {
		PrologTerm[] arguments = new PrologTerm[size];
		Iterator<PrologTerm> i = iterator();
		for (int idx = 0; i.hasNext() && table[idx] != nil; idx++) {
			arguments[idx] = i.next();
		}
		return arguments;
	}

	public PrologTerm getArgument(int index) {
		int idx = 0;
		PrologTerm term = null;
		checkIndex(index, size());
		Iterator<PrologTerm> i = iterator();
		for (; i.hasNext() && idx <= index && table[idx] != nil; idx++) {
			term = i.next();
			if (idx == index) {
				return term;
			}
		}
		return term;
	}

	public final boolean unify(PrologTerm term) {
		PrologTerm thisTerm = this;
		PrologTerm otherTerm = term;
		if (thisTerm == otherTerm) {
			return true;
		} else if (thisTerm.isVariable()) {
			if (thisTerm == thisTerm.getTerm()) {
				return true;
			}
			return thisTerm.getTerm().unify(otherTerm);
		} else if (otherTerm.isVariable()) {
			if (otherTerm == otherTerm.getTerm()) {
				return true;
			}
			return otherTerm.getTerm().unify(thisTerm);
		} else if (otherTerm.isCompound()) {
			int thisArity = thisTerm.getArity();
			int otherArity = otherTerm.getArity();
			String thisFunctor = thisTerm.getFunctor();
			String otherFunctor = otherTerm.getFunctor();
			if (thisFunctor.equals(otherFunctor) && thisArity == otherArity) {
				PrologTerm[] thisArguments = new PrologTerm[0];
				PrologTerm[] otherArguments = new PrologTerm[0];

				if (thisTerm instanceof PrologInternalMap) {
					thisArguments = ((PrologInternalMap) thisTerm).getArgs();
				} else {
					thisArguments = thisTerm.getArguments();
				}
				if (otherTerm instanceof PrologInternalMap) {
					otherArguments = ((PrologInternalMap) otherTerm).getArgs();
				} else {
					otherArguments = otherTerm.getArguments();
				}

				if (thisArguments.length == otherArguments.length) {
					for (int i = 0; i < thisArguments.length; i++) {
						if (thisArguments[i] != null && otherArguments[i] != null) {
							PrologTerm thisArgument = thisArguments[i];
							PrologTerm otherArgument = otherArguments[i];
							if (!thisArgument.unify(otherArgument)) {
								return false;
							}
						}
					}
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	public Map<String, PrologTerm> match(PrologTerm term) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrologProvider getProvider() {
		return provider;
	}

	public <T extends PrologTerm> T cast() {
		return (T) this;
	}

	public final int compareTo(PrologTerm term) {
		PrologTerm thisCompound = this;
		PrologTerm otherCompound = term;

		if (!otherCompound.isCompound()) {
			if (otherCompound.isEmptyList() && thisCompound.isEmptyList()) {
				return 0;
			}
			return 1;
		}

		if (otherCompound.isEmptyList() && thisCompound.isEmptyList()) {
			return 0;
		}

		// comparison by arity
		if (thisCompound.getArity() < otherCompound.getArity()) {
			return -1;
		} else if (thisCompound.getArity() > otherCompound.getArity()) {
			return 1;
		}

		// alphabetic functor comparison
		int result = thisCompound.getFunctor().compareTo(otherCompound.getFunctor());
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		}

		// arguments comparison
		PrologTerm[] thisArguments = new PrologTerm[0];
		PrologTerm[] otherArguments = new PrologTerm[0];

		if (thisCompound instanceof PrologInternalMap) {
			thisArguments = ((PrologInternalMap) thisCompound).getArgs();
		} else {
			thisArguments = thisCompound.getArguments();
		}
		if (otherCompound instanceof PrologInternalMap) {
			otherArguments = ((PrologInternalMap) otherCompound).getArgs();
		} else {
			otherArguments = otherCompound.getArguments();
		}

		for (int i = 0; i < thisArguments.length; i++) {
			PrologTerm thisArgument = thisArguments[i];
			PrologTerm otherArgument = otherArguments[i];
			if (thisArgument != null && otherArgument != null) {
				result = thisArgument.compareTo(otherArgument);
				if (result != 0) {
					return result;
				}
			}
		}

		return 0;
	}

	public Iterator<PrologTerm> iterator() {
		return new PrologMapIterator();
	}

	public boolean isList() {
		return true;
	}

	public boolean isStructure() {
		return false;
	}

	public boolean isEmptyList() {
		return size() == 0;
	}

	public String getFunctor() {
		return ".";
	}

	public int getArity() {
		if (size() > 0) {
			return 2;
		}
		return 0;
	}

	public void putAll(Collection<Entry<PrologTerm, PrologTerm>> entries) {
		for (Entry<PrologTerm, PrologTerm> entry : entries) {
			put(entry);
		}
	}

	public boolean contains(Entry<PrologTerm, PrologTerm> entry) {
		PrologTerm value = get(entry.getKey());
		return value != null ? value.equals(entry.getValue()) : false;
	}

	public void remove(Entry<PrologTerm, PrologTerm> entry) {
		remove(entry.getKey());
	}

	public void put(Entry<PrologTerm, PrologTerm> entry) {
		put(entry.getKey(), entry.getValue());
	}

	public final boolean isClause() {
		return false;
	}

	public final boolean isTerm() {
		return true;
	}

	class PrologHashEntry extends HashLinkedEntry<PrologTerm, PrologTerm> implements PrologEntry {

		PrologHashEntry(PrologTerm key, PrologTerm value) {
			super(key, value);
		}

		PrologHashEntry(PrologTerm key, PrologTerm value,
				PrologLinkedMap<PrologTerm, PrologTerm>.HashLinkedEntry<PrologTerm, PrologTerm> prev,
				PrologLinkedMap<PrologTerm, PrologTerm>.HashLinkedEntry<PrologTerm, PrologTerm> next) {
			super(key, value, prev, next);
		}

		public String getIndicator() {
			return getFunctor() + "/" + getArity();
		}

		public boolean hasIndicator(String functor, int arity) {
			return getFunctor().equals(functor) && getArity() == arity;
		}

		public int getType() {
			return MAP_ENTRY_TYPE;
		}

		public boolean isAtom() {
			return false;
		}

		public boolean isNumber() {
			return false;
		}

		public boolean isFloat() {
			return false;
		}

		public boolean isInteger() {
			return false;
		}

		public boolean isDouble() {
			return false;
		}

		public boolean isLong() {
			return false;
		}

		public boolean isVariable() {
			return false;
		}

		public final boolean isVariableBound() {
			return false;
		}

		public final boolean isVariableNotBound() {
			return false;
		}

		public boolean isList() {
			return false;
		}

		public boolean isStructure() {
			return true;
		}

		public boolean isNil() {
			return false;
		}

		public boolean isEmptyList() {
			return false;
		}

		public boolean isAtomic() {
			return false;
		}

		public boolean isCompound() {
			return true;
		}

		public boolean isEvaluable() {
			return false;
		}

		public boolean isTrueType() {
			return false;
		}

		public boolean isFalseType() {
			return false;
		}

		public boolean isNullType() {
			return false;
		}

		public boolean isVoidType() {
			return false;
		}

		public boolean isObjectType() {
			return false;
		}

		public boolean isReference() {
			return false;
		}

		public boolean isEntry() {
			return true;
		}

		public boolean isMap() {
			return false;
		}

		public boolean isField() {
			return false;
		}

		public boolean isMixin() {
			return false;
		}

		public boolean isClass() {
			return false;
		}

		public Object getObject() {
			return this;
		}

		public PrologTerm getTerm() {
			return this;
		}

		public int getArity() {
			return 2;
		}

		public String getFunctor() {
			return "-";
		}

		public PrologTerm[] getArguments() {
			return new PrologTerm[] { getKey(), getValue() };
		}

		public PrologTerm getArgument(int index) {
			checkIndex(index, getArity());
			return index == 0 ? getKey() : getValue();
		}

		public boolean unify(PrologTerm term) {
			PrologTerm thisTerm = this;
			PrologTerm otherTerm = term;
			if (thisTerm == otherTerm) {
				return true;
			} else if (thisTerm.isVariable()) {
				if (thisTerm == thisTerm.getTerm()) {
					return true;
				}
				return thisTerm.getTerm().unify(otherTerm);
			} else if (otherTerm.isVariable()) {
				if (otherTerm == otherTerm.getTerm()) {
					return true;
				}
				return otherTerm.getTerm().unify(thisTerm);
			} else if (otherTerm.isCompound()) {
				int thisArity = thisTerm.getArity();
				int otherArity = otherTerm.getArity();
				String thisFunctor = thisTerm.getFunctor();
				String otherFunctor = otherTerm.getFunctor();
				if (thisFunctor.equals(otherFunctor) && thisArity == otherArity) {
					PrologTerm[] thisArguments = thisTerm.getArguments();
					PrologTerm[] otherArguments = otherTerm.getArguments();
					if (thisArguments.length == otherArguments.length) {
						for (int i = 0; i < thisArguments.length; i++) {
							if (thisArguments[i] != null && otherArguments[i] != null) {
								PrologTerm thisArgument = thisArguments[i];
								PrologTerm otherArgument = otherArguments[i];
								if (!thisArgument.unify(otherArgument)) {
									return false;
								}
							}
						}
						return true;
					}
					return false;
				}
				return false;
			}
			return false;
		}

		public Map<String, PrologTerm> match(PrologTerm term) {
			// TODO Auto-generated method stub
			return null;
		}

		public PrologProvider getProvider() {
			return provider;
		}

		public <T extends PrologTerm> T cast() {
			return (T) this;
		}

		public int compareTo(PrologTerm term) {
			PrologTerm thisCompound = this;
			PrologTerm otherCompound = term;

			if (!otherCompound.isCompound()) {
				if (otherCompound.isEmptyList() && thisCompound.isEmptyList()) {
					return 0;
				}
				return 1;
			}

			if (otherCompound.isEmptyList() && thisCompound.isEmptyList()) {
				return 0;
			}

			// comparison by arity
			if (thisCompound.getArity() < otherCompound.getArity()) {
				return -1;
			} else if (thisCompound.getArity() > otherCompound.getArity()) {
				return 1;
			}

			// alphabetic functor comparison
			int result = thisCompound.getFunctor().compareTo(otherCompound.getFunctor());
			if (result < 0) {
				return -1;
			} else if (result > 0) {
				return 1;
			}

			// arguments comparison
			PrologTerm[] thisArguments = thisCompound.getArguments();
			PrologTerm[] otherArguments = otherCompound.getArguments();

			for (int i = 0; i < thisArguments.length; i++) {
				PrologTerm thisArgument = thisArguments[i];
				PrologTerm otherArgument = otherArguments[i];
				if (thisArgument != null && otherArgument != null) {
					result = thisArgument.compareTo(otherArgument);
					if (result != 0) {
						return result;
					}
				}
			}

			return 0;
		}

		public final boolean isClause() {
			return false;
		}

		public final boolean isTerm() {
			return true;
		}

	}

	private class PrologMapIterator extends AbstractIterator<PrologTerm> implements Iterator<PrologTerm> {

		private final Set<PrologTerm> set;
		private final Iterator<PrologTerm> itr;

		private PrologMapIterator() {
			set = new LinkedHashSet<PrologTerm>(size());
			for (Iterator<Entry<PrologTerm, PrologTerm>> i = entrySet().iterator(); i.hasNext();) {
				Entry<PrologTerm, PrologTerm> e = i.next();
				PrologTerm t = new PrologHashEntry(e.getKey(), e.getValue());
				set.add(t);
			}
			itr = set.iterator();
		}

		public boolean hasNext() {
			return itr.hasNext();
		}

		public PrologTerm next() {
			return itr.next();
		}

	}

}
