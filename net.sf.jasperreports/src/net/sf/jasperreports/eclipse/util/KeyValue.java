/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package net.sf.jasperreports.eclipse.util;

public class KeyValue<T, V> {
	public T key;
	public V value;

	public KeyValue(T key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		boolean b = obj instanceof KeyValue;
		if (b) {
			@SuppressWarnings("unchecked")
			KeyValue<T, V> kobj = (KeyValue<T, V>) obj;
			return key.equals(kobj.key) && value.equals(kobj.value);
		}

		return b;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + key.hashCode();
		hash = hash * 31 + value.hashCode();
		return hash;
	}
}
