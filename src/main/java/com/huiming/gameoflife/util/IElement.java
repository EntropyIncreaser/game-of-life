package com.huiming.gameoflife.util;

public interface IElement<T> extends Comparable<IElement<T>> {
	T getValue();
	IElement setValue(T value);
}
