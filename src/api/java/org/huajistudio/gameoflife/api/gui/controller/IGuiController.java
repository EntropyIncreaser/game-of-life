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
package org.huajistudio.gameoflife.api.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.api.GameOfLifeAPI;
import org.huajistudio.gameoflife.api.event.ControllerEvent;
import org.huajistudio.gameoflife.api.util.I18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ResourceBundle;

import static org.huajistudio.gameoflife.api.GameOfLifeAPI.EVENT_MANAGER;

public interface IGuiController {
	default void load() {
		FXMLLoader loader = new FXMLLoader(IGuiController.class.getResource("/fxml/" + getClass().getSimpleName().substring(0,
			getClass().getSimpleName().lastIndexOf("Controller")) +".fxml"));
		loader.setResources(ResourceBundle.getBundle("language/language", GameOfLife.locale, new I18n.UTF8Control()));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Nullable
	default <T extends Node> T getFXMLNode(@Nonnull String name) {
		try {
			Field field = getClass().getDeclaredField(name);
			if (field.getAnnotation(FXML.class) != null) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				EVENT_MANAGER.executeEvent(new ControllerEvent.ControllerGetFXMLNodeEvent(this));
				return (T) field.get(this);
			}
		} catch (Exception e) {
			GameOfLifeAPI.LOGGER.error("FXML Node not found in " + getClass().getSimpleName() + ", check your name!", e);
		}
		return null;
	}
}
