package org.huajistudio.gameoflife.event;

import junit.framework.Assert;
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