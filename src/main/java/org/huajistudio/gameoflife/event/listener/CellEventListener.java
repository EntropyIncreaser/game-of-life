package org.huajistudio.gameoflife.event.listener;

import org.huajistudio.gameoflife.event.CellEvent;
import org.huajistudio.gameoflife.event.IEventListener;
import org.huajistudio.gameoflife.event.SubscribeEvent;

import static org.huajistudio.gameoflife.GameOfLife.LOGGER;

public class CellEventListener implements IEventListener {
	@SubscribeEvent
	public void onCellCreated(CellEvent.CellCreatedEvent event) {
		LOGGER.info("Cell has been created at " + event.getPosition());
	}
}
