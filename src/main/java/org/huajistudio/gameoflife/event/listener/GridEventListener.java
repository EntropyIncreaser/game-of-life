package org.huajistudio.gameoflife.event.listener;

import org.huajistudio.gameoflife.event.GridEvent;
import org.huajistudio.gameoflife.event.IEventListener;
import org.huajistudio.gameoflife.event.SubscribeEvent;

public class GridEventListener implements IEventListener {
	@SubscribeEvent
	public void onGridCreated(GridEvent.GridCreatedEvent event) {
	}
}
