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
package org.huajistudio.gameoflife.api.util;

import org.huajistudio.gameoflife.components.grid.EnumGridSimpleRule;
import org.huajistudio.gameoflife.components.grid.GridMultipleRule;

public class GameRule {
	public static EnumGridSimpleRule simpleRule = EnumGridSimpleRule.DEFAULT;
	public static GridMultipleRule multipleRule = new GridMultipleRule(false, true);
	/**
	 * Cycle time (in milliseconds)
	 */
	public static int cycleTime = 300;
}
