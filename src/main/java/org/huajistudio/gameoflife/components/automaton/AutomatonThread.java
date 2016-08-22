package org.huajistudio.gameoflife.components.automaton;

import org.huajistudio.gameoflife.components.grid.Grid;
import org.huajistudio.gameoflife.util.GameRule;

public class AutomatonThread implements Runnable {
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
			pauseLock.notifyAll();
		}
	}
}
