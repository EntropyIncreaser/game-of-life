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
package org.huajistudio.gameoflife.api.event;

import org.huajistudio.gameoflife.api.GameOfLifeAPI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventHandler implements Comparable<EventHandler> {
	private final IEventListener listener;
	private final Method method;
	private final SubscribeEvent annotation;

	public EventHandler(SubscribeEvent annotation, IEventListener listener, Method method) {
		this.annotation = annotation;
		this.listener = listener;
		this.method = method;
	}

	public SubscribeEvent getAnnotation() {
		return annotation;
	}

	public IEventListener getListener() {
		return listener;
	}

	public Method getMethod() {
		return method;
	}

	public int getPriority() {
		return annotation.priority().getPriority();
	}

	public void invoke(IEvent event) {
		try {
			method.invoke(listener, event);
		} catch (InvocationTargetException | IllegalAccessException e) {
			GameOfLifeAPI.LOGGER.error("Exception when invoking EventHandler " + this.listener + " for event " + event.toString(), e);
		}
	}


	@Override
	public int compareTo(EventHandler o) {
		int annotation = this.annotation.priority().getPriority() - o.annotation.priority().getPriority();
		if (annotation == 0)
			annotation = this.listener.hashCode() - o.listener.hashCode();
		return annotation == 0 ? this.hashCode() - o.hashCode() : annotation;
	}
}
