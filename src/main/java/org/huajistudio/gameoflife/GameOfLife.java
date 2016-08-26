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
package org.huajistudio.gameoflife;

import javafx.application.Application;
import javafx.stage.Stage;
import org.huajistudio.gameoflife.api.GameOfLifeAPI;
import org.huajistudio.gameoflife.api.util.I18n;
import org.huajistudio.gameoflife.gui.Editor;
import org.huajistudio.gameoflife.gui.Options;
import org.huajistudio.gameoflife.gui.Welcome;

import java.util.Locale;

public class GameOfLife extends Application {
	public static Locale locale = Locale.getDefault();

	public static final Welcome WELCOME = new Welcome();
	public static final Editor EDITOR = new Editor(10, 10);
	public static final Options OPTIONS = new Options();

	public static void main(String[] args) {
		GameOfLifeAPI.I18N = new I18n();
		// Register default listeners
		GameOfLifeAPI.EVENT_MANAGER.registerListenerInPackage("org.huajistudio.gameoflife.event.listener");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		WELCOME.start(primaryStage);
	}
}
