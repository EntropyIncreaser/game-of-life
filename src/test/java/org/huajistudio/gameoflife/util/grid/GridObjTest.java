package org.huajistudio.gameoflife.util.grid;

import com.google.gson.Gson;
import junit.framework.Assert;
import org.junit.Test;

public class GridObjTest {
	@Test
	public void test() {
		Gson gson = new Gson();
		GridObj gridObj = gson.fromJson("{\"width\":10,\"height\":10,\"data\":[{\"position\":{\"x\":1,\"y\":1},\"element\":{\"value\":true}},{\"position\":{\"x\":1,\"y\":2},\"element\":{\"value\":true}}]}",
			GridObj.class);
		Assert.assertEquals(gridObj.toString(), "GridObj{data=[GridPosElement{element=GridElement{value=true}, position=GridPos{x=1, y=1}}, GridPosElement{element=GridElement{value=true}, position=GridPos{x=1, y=2}}], width=10, height=10}");
	}
}
