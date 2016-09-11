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
package org.huajistudio.gameoflife.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.huajistudio.gameoflife.api.GameOfLifeAPI;
import org.huajistudio.gameoflife.api.gui.IGuiHandler;
import org.huajistudio.gameoflife.gui.controller.OptionsController;

public class Options extends Application implements IGuiHandler {
	public OptionsController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new OptionsController();
		primaryStage.setScene(new Scene(controller));
		primaryStage.setTitle(GameOfLifeAPI.I18N.parse("window.welcome.button.opt.label"));
		primaryStage.show();
	}

}
