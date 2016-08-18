package com.huiming.gameoflife.util.grid;

import com.huiming.gameoflife.util.IElement;

public class GridElement implements IElement<Boolean> {
	private boolean value;

	public Boolean getValue() {
		return value;
	}

	public IElement setValue(Boolean value) {
		this.value = value;
		return this;
	}

	public GridElement swap() {
		value = !value;
		return this;
	}

	@Override
	public int compareTo(IElement<Boolean> o) {
		return (value == o.getValue()) ? 0 : (value ? 1 : -1);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Boolean && value == (Boolean) obj;
	}

	@Override
	public int hashCode() {
		return (value ? 1 : 0);
	}

	@Override
	public String toString() {
		return "GridElement{" +
			"value=" + value +
			'}';
	}
}
