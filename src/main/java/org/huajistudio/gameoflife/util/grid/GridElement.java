package org.huajistudio.gameoflife.util.grid;

import org.huajistudio.gameoflife.util.IElement;

public class GridElement implements IElement<Boolean> {
	private boolean value;
	private double[] rgba;

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public GridElement setValue(Boolean value) {
		this.value = value;
		return this;
	}

	public GridElement swap() {
		value = !value;
		return this;
	}

	public double[] getRgba() {
		return rgba;
	}

	public GridElement setRgba(double[] rgba) {
		this.rgba = rgba;
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
