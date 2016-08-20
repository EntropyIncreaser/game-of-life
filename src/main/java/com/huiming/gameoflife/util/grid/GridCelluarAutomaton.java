package com.huiming.gameoflife.util.grid;

/**
 * The cellular automaton of the grid.
 * @author Entropy_Increaser
 */
public enum GridCellularAutomaton {
/**
 * The default settings of the cellular automaton.
 */
	DEFAUT({ 3 }, { 2, 3 }),
	HIGHLIFE({ 3, 6 }, { 2, 3 }),
	SEED({ 2 }, {}),
	LIFEWITHOUTDEATH({ 3 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8 }),
	THREEFOUR({ 3, 4 }, { 3, 4 }),
	DIAMOEBA({ 3, 5, 6, 7, 8 }, { 5, 6, 7, 8 }),
	TWOXTWO({ 3, 6 }, { 1, 2, 5 }),
	DAYANDNIGHT({ 3, 6, 7, 8 }, { 3, 4, 6, 7, 8 }),
	MORLEY({ 3, 6, 8 }, { 2, 4, 5 }),
	ANNEAL({ 4, 6, 7, 8 }, { 3, 5, 6, 7, 8 });

	private boolean born[9], stay[9];
	private boolean isOpenSpace;

	public GridCellularAutomaton(int born[], int stay[]) {
		this(born, stay, true);
	}

	public GridCellularAutomaton(int born[], int stay[], boolean isOpenSpace) {
		born = new boolean[9];
		stay = new boolean[9];
		int x;
		for (x : born) {
			this.born[x] = true;
		}
		for (x : stay) {
			this.stay[x] = true;
		}
		this.isOpenSpace = isOpenSpace;
	}

	public boolean getSpaceType() {
		return isOpenSpace;
	}

	public boolean setSpaceType(boolean type) {
		isOpenSpace = type;
	}

	public boolean couldBorn(int count) {
		return born[count];
	}

	public boolean couldStay(int count) {
		return stay[count];
	}
}
