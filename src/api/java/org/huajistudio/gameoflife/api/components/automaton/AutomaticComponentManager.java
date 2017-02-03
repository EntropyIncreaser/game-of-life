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
package org.huajistudio.gameoflife.api.components.automaton;

import org.huajistudio.gameoflife.api.components.grid.Grid;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.huajistudio.gameoflife.api.GameOfLifeAPI.LOGGER;

public class AutomaticComponentManager {
	private List<AutomaticComponentHandler> componentHandlers = new ArrayList<>();

	public void addAutomaticComponent(Class<? extends IAutomaticComponent> component) {
		LOGGER.info("Adding component " + component.getSimpleName());
		Method[] methods = component.getDeclaredMethods();
		for (final Method method : methods) {
			SubscribeAutomaton annotation = method.getAnnotation(SubscribeAutomaton.class);
			if (annotation == null)
				continue;
			if (!method.getParameterTypes()[0].equals(Grid.class))
				continue;
			LOGGER.info("Add listener method: " + method.getName());
			try {
				componentHandlers.add(new AutomaticComponentHandler(annotation, component.newInstance(), method));
			} catch (InstantiationException | IllegalAccessException e) {
				LOGGER.error(e);
			}
		}
	}

	public void addAutomaticComponentInPackage(String packageName) {
		Reflections reflections = new Reflections(packageName);
		reflections.getSubTypesOf(IAutomaticComponent.class).forEach(this::addAutomaticComponent);
	}

	public Grid cycle(Grid grid) {
		for (AutomaticComponentHandler handler : componentHandlers) {
			grid = handler.invoke(grid);
		}
		return grid;
	}
}
