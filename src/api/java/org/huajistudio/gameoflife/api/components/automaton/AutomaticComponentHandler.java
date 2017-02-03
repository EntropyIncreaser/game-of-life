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

import org.huajistudio.gameoflife.api.GameOfLifeAPI;
import org.huajistudio.gameoflife.api.components.grid.Grid;

import java.lang.reflect.Method;

public class AutomaticComponentHandler implements Comparable<AutomaticComponentHandler> {
	private final IAutomaticComponent component;
	private final Method method;
	private final SubscribeAutomaton annotation;

	public AutomaticComponentHandler(SubscribeAutomaton annotation, IAutomaticComponent component, Method method) {
		this.annotation = annotation;
		this.component = component;
		this.method = method;
	}

	public SubscribeAutomaton getAnnotation() {
		return annotation;
	}

	public IAutomaticComponent getComponent() {
		return component;
	}

	public Method getMethod() {
		return method;
	}

	public int getPriority() {
		return annotation.priority().getPriority();
	}

	public Grid invoke(Grid grid) {
		try {
			return (Grid) method.invoke(component, grid);
		} catch (Exception e) {
			GameOfLifeAPI.LOGGER.error("Exception when invoking AutomaticComponentHandler " + this.component + " for grid " + grid.toString(), e);
			return new Grid(1,1);
		}
	}


	@Override
	public int compareTo(AutomaticComponentHandler o) {
		int annotation = this.annotation.priority().getPriority() - o.annotation.priority().getPriority();
		if (annotation == 0)
			annotation = this.component.hashCode() - o.component.hashCode();
		return annotation == 0 ? this.hashCode() - o.hashCode() : annotation;
	}
}
