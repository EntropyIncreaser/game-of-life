package org.huajistudio.gameoflife.util.grid;

import java.util.Arrays;

/**
 * The enumeration of cellular automaton in the grid.
 * @author Entropy_Increaser
 */
public enum EnumGridCelluloidAutomation {
	/**
	 * The default settings of the cellular automaton.
	 */
	DEFAULT(new int[]{ 3 }, new int[]{ 2, 3 }),
	HIGHLIFE(new int[]{ 3, 6 }, new int[]{ 2, 3 }),
	SEED(new int[]{ 2 }, new int[]{}),
	LIFEWITHOUTDEATH(new int[]{ 3 }, new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8 }),
	THREEFOUR(new int[]{ 3, 4 }, new int[]{ 3, 4 }),
	DIAMOEBA(new int[]{ 3, 5, 6, 7, 8 }, new int[]{ 5, 6, 7, 8 }),
	TWOXTWO(new int[]{ 3, 6 }, new int[]{ 1, 2, 5 }),
	DAYANDNIGHT(new int[]{ 3, 6, 7, 8 }, new int[]{ 3, 4, 6, 7, 8 }),
	MORLEY(new int[]{ 3, 6, 8 }, new int[]{ 2, 4, 5 }),
	ANNEAL(new int[]{ 4, 6, 7, 8 }, new int[]{ 3, 5, 6, 7, 8 });

	private boolean born[], stay[];
	private boolean isOpenSpace;

	EnumGridCelluloidAutomation(int born[], int stay[]) {
		this(born, stay, true);
	}

	EnumGridCelluloidAutomation(int born[], int stay[], boolean isOpenSpace) {
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
