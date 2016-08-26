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
package org.huajistudio.gameoflife.event;

import junit.framework.Assert;
import org.huajistudio.gameoflife.api.event.EventManager;
import org.huajistudio.gameoflife.api.event.IEventListener;
import org.huajistudio.gameoflife.api.event.SubscribeEvent;
import org.junit.Before;
import org.junit.Test;

public class EventManagerTest implements IEventListener {
	private EventManager eventManager;
	private int messages;

	@Before
	public void before() {
		this.eventManager = new EventManager();
		this.eventManager.registerListener(this);
	}

	@Test
	public void test() {
		Assert.assertEquals(0, messages);
		this.eventManager.executeEvent(new SimpleEvent());
		Assert.assertEquals(1, messages);
	}

	@SubscribeEvent
	public void onSimpleEvent(SimpleEvent event) {
		messages++;
	}
}