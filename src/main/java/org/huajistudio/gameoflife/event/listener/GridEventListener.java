package org.huajistudio.gameoflife.event.listener;

import org.huajistudio.gameoflife.components.automaton.AutomatonThread;
import org.huajistudio.gameoflife.event.GridEvent;
import org.huajistudio.gameoflife.event.IEventListener;
import org.huajistudio.gameoflife.event.SubscribeEvent;

import static org.huajistudio.gameoflife.GameOfLife.LOGGER;

public class GridEventListener implements IEventListener {
	AutomatonThread automatonThread;
	@SubscribeEvent
	public void onGridCreated(GridEvent.GridCreatedEvent event) {
		automatonThread = new AutomatonThread(event.getGrid());
		Thread thread = new Thread(automatonThread);
		thread.start();
		LOGGER.info("Automaton Thread Started");
	}
}
