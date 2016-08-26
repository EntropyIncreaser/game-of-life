/*
 * A game inspired from Conway's Game Of Life.
 * Copyright (C) 2016 Huaji Studio.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.huajistudio.gameoflife.api.components.cell;

import org.huajistudio.gameoflife.api.util.IElement;

public class Cell implements IElement<Boolean> {
	private boolean value;
	private double[] rgba;

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public Cell setValue(Boolean value) {
		this.value = value;
		return this;
	}

	public Cell swap() {
		value = !value;
		return this;
	}

	public double[] getRgba() {
		return rgba;
	}

	public Cell setRgba(double[] rgba) {
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
		return "Cell{" +
			"value=" + value +
			'}';
	}
}
