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
package org.huajistudio.gameoflife.components.automaton;

import org.huajistudio.gameoflife.api.components.automaton.AutomaticComponentManager;
import org.huajistudio.gameoflife.api.components.automaton.IAutomatonThread;
import org.huajistudio.gameoflife.api.components.grid.Grid;
import org.huajistudio.gameoflife.api.util.GameRule;

/**
 * Default implementation for IAutomatonThread.
 * @see org.huajistudio.gameoflife.api.components.automaton.IAutomatonThread
 */
public class AutomatonThread implements IAutomatonThread {
	private Grid grid;
	private final AutomaticComponentManager manager = new AutomaticComponentManager();
	private volatile boolean running = true;
	private volatile boolean paused = false;
	private final Object pauseLock = new Object();

	public AutomatonThread(Grid grid) {
		this.grid = grid;
		manager.addAutomaticComponentInPackage("org.huajistudio.gameoflife.components.automaton");
	}

	@Override
	public void run() {
		while (running) {
			synchronized (pauseLock) {
				if (!running) {
					break;
				}
				if (paused) {
					try {
						pauseLock.wait();
					} catch (InterruptedException ex) {
						break;
					}
					if (!running) {
						break;
					}
				}
			}

			manager.cycle(grid);

			try {
				Thread.sleep(GameRule.cycleTime);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void stop() {
		running = false;
	}

	public void pause() {
		paused = true;
	}

	public void resume() {
		synchronized (pauseLock) {
			paused = false;
			running = true;
			pauseLock.notifyAll();
		}
	}
}
