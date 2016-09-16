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
package org.huajistudio.gameoflife.event.listener;

import javafx.scene.paint.Color;
import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.api.components.cell.Cell;
import org.huajistudio.gameoflife.api.event.CellEvent;
import org.huajistudio.gameoflife.api.event.IEventListener;
import org.huajistudio.gameoflife.api.event.SubscribeEvent;

import static org.huajistudio.gameoflife.api.GameOfLifeAPI.LOGGER;

@SuppressWarnings("unused")
public class CellEventListener implements IEventListener {
	@SubscribeEvent
	public void onCellCreated(CellEvent.CellCreatedEvent event) {
		LOGGER.debug("Cell has been created at " + event.getPosition());
		event.getGrid().setElement(event.getPosition(), Cell.NORMAL_CELL);
		GameOfLife.EDITOR.drawGrid(GameOfLife.EDITOR.worldCanvas, GameOfLife.EDITOR.getWorldGrid(), Color.BLACK);
	}

	@SubscribeEvent
	public void onCellKilled(CellEvent.CellKilledEvent event) {
		LOGGER.debug("Cell has been killed at " + event.getPosition());
		event.getGrid().setElement(event.getPosition(), Cell.DEAD_CELL);
		GameOfLife.EDITOR.drawGrid(GameOfLife.EDITOR.worldCanvas, GameOfLife.EDITOR.getWorldGrid(), Color.BLACK);
	}
}
