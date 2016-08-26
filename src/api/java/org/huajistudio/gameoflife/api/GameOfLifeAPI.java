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
package org.huajistudio.gameoflife.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.huajistudio.gameoflife.api.event.EventManager;
import org.huajistudio.gameoflife.api.util.II18n;

public class GameOfLifeAPI {
	public static final Logger LOGGER = LogManager.getLogger("GameOfLife");
	public static final EventManager EVENT_MANAGER = new EventManager();
	public static II18n I18N;
}
