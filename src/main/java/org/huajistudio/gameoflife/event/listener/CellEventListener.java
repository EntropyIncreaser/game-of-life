package org.huajistudio.gameoflife.event.listener;

import javafx.scene.paint.Color;
import org.huajistudio.gameoflife.components.cell.Cell;
import org.huajistudio.gameoflife.event.CellEvent;
import org.huajistudio.gameoflife.event.IEventListener;
import org.huajistudio.gameoflife.event.SubscribeEvent;

import static org.huajistudio.gameoflife.GameOfLife.LOGGER;

public class CellEventListener implements IEventListener {
	@SubscribeEvent
	public void onCellCreated(CellEvent.CellCreatedEvent event) {
		LOGGER.debug("Cell has been created at " + event.getPosition());
	}

	@SubscribeEvent
	public void onCellKilled(CellEvent.CellKilledEvent event) {
		LOGGER.debug("Cell has been killed at " + event.getPosition());
		event.getGrid().setElement(event.getPosition(), new Cell().setValue(false).setRgba(new double[]{Color.WHITE.getRed(),Color.WHITE.getGreen(),Color.WHITE.getBlue(),Color.WHITE.getOpacity()}));
	}
}
