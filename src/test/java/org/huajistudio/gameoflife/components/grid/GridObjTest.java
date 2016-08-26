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

import com.google.gson.Gson;
import junit.framework.Assert;
import org.junit.Test;

public class GridObjTest {
	@Test
	public void test() {
		Gson gson = new Gson();
		GridObj gridObj = gson.fromJson("{\"width\":10,\"height\":10,\"data\":[{\"position\":{\"x\":1,\"y\":1},\"element\":{\"value\":true}},{\"position\":{\"x\":1,\"y\":2},\"element\":{\"value\":true}}]}",
			GridObj.class);
		Assert.assertEquals(gridObj.toString(), "GridObj{data=[GridPosElement{element=Cell{value=true}, position=GridPos{x=1, y=1}}, GridPosElement{element=Cell{value=true}, position=GridPos{x=1, y=2}}], width=10, height=10}");
	}
}
