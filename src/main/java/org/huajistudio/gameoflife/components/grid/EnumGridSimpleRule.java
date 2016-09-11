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
package org.huajistudio.gameoflife.components.grid;

import java.util.Arrays;

/**
 * The enumeration of the rule for a simple civilization in the grid.
 * @author Entropy_Increaser
 */
public enum EnumGridSimpleRule {
	/**
	 * The default settings of the cellular automaton.
	 */
	DEFAULT(new int[]{ 3 }, new int[]{ 2, 3 }),
	HIGH_LIFE(new int[]{ 3, 6 }, new int[]{ 2, 3 }),
	SEED(new int[]{ 2 }, new int[]{}),
	LIFE_WITHOUT_DEATH(new int[]{ 3 }, new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8 }),
	THREE_FOUR(new int[]{ 3, 4 }, new int[]{ 3, 4 }),
	DIAMOEBA(new int[]{ 3, 5, 6, 7, 8 }, new int[]{ 5, 6, 7, 8 }),
	TWO_X_TWO(new int[]{ 3, 6 }, new int[]{ 1, 2, 5 }),
	DAY_AND_NIGHT(new int[]{ 3, 6, 7, 8 }, new int[]{ 3, 4, 6, 7, 8 }),
	MORLEY(new int[]{ 3, 6, 8 }, new int[]{ 2, 4, 5 }),
	ANNEAL(new int[]{ 4, 6, 7, 8 }, new int[]{ 3, 5, 6, 7, 8 });

	private boolean born[], stay[];
	private boolean isOpenSpace;

	EnumGridSimpleRule(int born[], int stay[]) {
		this(born, stay, true);
	}

	EnumGridSimpleRule(int born[], int stay[], boolean isOpenSpace) {
		this.born = new boolean[9];
		this.stay = new boolean[9];

		Arrays.fill(this.born, 0, born.length, true);
		Arrays.fill(this.stay, 0, born.length, true);

		this.isOpenSpace = isOpenSpace;
	}

	public boolean getSpaceType() {
		return isOpenSpace;
	}

	public void setSpaceType(boolean type) {
		isOpenSpace = type;
	}

	public boolean couldBorn(int count) {
		return born[count];
	}

	public boolean couldStay(int count) {
		return stay[count];
	}
}
