package org.logicware.platform;

import java.util.Iterator;

public abstract class AbstractIterator<E> extends AbstractWrapper implements Iterator<E> {

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
