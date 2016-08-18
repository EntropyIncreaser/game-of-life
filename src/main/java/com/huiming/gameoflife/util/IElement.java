package com.huiming.gameoflife.util;

import java.io.Serializable;

public interface IElement<T> extends Comparable<IElement<T>>, Serializable {
	T getValue();
	IElement setValue(T value);
}
