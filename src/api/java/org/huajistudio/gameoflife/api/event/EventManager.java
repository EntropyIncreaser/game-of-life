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
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.*;

public class EventManager {
	private final Map<Class<? extends IEvent>, Collection<EventHandler>> bindings;
	private final Set<IEventListener> registeredListeners;
	private boolean debug = false;

	public EventManager() {
		bindings = new HashMap<>();
		registeredListeners = new HashSet<>();
	}

	public List<EventHandler> getListenersForClass(Class<? extends IEvent> clazz) {
		if (!bindings.containsKey(clazz))
			return new ArrayList<>();
		return new ArrayList<>(bindings.get(clazz));
	}

	public <T extends IEvent> T executeEvent(T event) {
		Collection<EventHandler> handlers = this.bindings.get(event.getClass());
		if (handlers == null) {
			if (this.debug)
				GameOfLifeAPI.LOGGER.debug("IEvent " + event.getClass().getSimpleName() + " has no handlers.");
			return event;
		}
		if (this.debug)
			GameOfLifeAPI.LOGGER.debug("IEvent " + event.getClass().getSimpleName() + " has " + handlers.size() + " handlers.");
		for (EventHandler handler : handlers) {
			handler.invoke(event);
		}
		return event;
	}

	public void registerListenerInPackage(String packageName) {
		Reflections reflections = new Reflections(packageName);
		Set<Class<? extends IEventListener>> listeners = reflections.getSubTypesOf(IEventListener.class);
		for (Class<? extends IEventListener> listener : listeners) {
			try {
				IEventListener instance = listener.newInstance();
				registerListener(instance);
			} catch (InstantiationException | IllegalAccessException e) {
				GameOfLifeAPI.LOGGER.error("You should use no-parameter constructor in your EventListener class!", e);
			}
		}
	}

	public void registerListener(final IEventListener listener) {
		GameOfLifeAPI.LOGGER.debug("Register event listener: " + listener);

		if (registeredListeners.contains(listener)) {
			GameOfLifeAPI.LOGGER.warn("Listener already registred: " + listener);
			return;
		}

		Method[] methods = listener.getClass().getDeclaredMethods();
		this.registeredListeners.add(listener);
		for (final Method method : methods) {
			SubscribeEvent annotation = method.getAnnotation(SubscribeEvent.class);
			if (annotation == null)
				continue;

			Class<?>[] parameters = method.getParameterTypes();
			if (parameters.length != 1) // all listener methods should only have one parameter
				continue;

			Class<?> param = parameters[0];

			if (!method.getReturnType().equals(void.class)) {
				GameOfLifeAPI.LOGGER.warn("Ignoring method due to non-void return: " + method.getName());
				continue;
			}

			if (IEvent.class.isAssignableFrom(param)) {
				@SuppressWarnings("unchecked") // Java just doesn't understand that this actually is a safe cast because of the above if-statement
					Class<? extends IEvent> realParam = (Class<? extends IEvent>) param;

				if (!this.bindings.containsKey(realParam)) {
					this.bindings.put(realParam, new TreeSet<>());
				}
				Collection<EventHandler> eventHandlersForEvent = this.bindings.get(realParam);
				GameOfLifeAPI.LOGGER.debug("Add listener method: " + method.getName() + " for event " + realParam.getSimpleName());
				eventHandlersForEvent.add(createEventHandler(listener, method, annotation));
			}
		}
	}

	private EventHandler createEventHandler(IEventListener listener, Method method, SubscribeEvent annotation) {
		return new EventHandler(annotation, listener, method);
	}

	public void clearListeners() {
		this.bindings.clear();
		this.registeredListeners.clear();
	}

	public void removeListener(EventListener listener) {
		for (Map.Entry<Class<? extends IEvent>, Collection<EventHandler>> ee : bindings.entrySet()) {
			Iterator<EventHandler> it = ee.getValue().iterator();
			while (it.hasNext()) {
				EventHandler curr = it.next();
				if (curr.getListener() == listener)
					it.remove();
			}
		}
		this.registeredListeners.remove(listener);
	}
	public Map<Class<? extends IEvent>, Collection<EventHandler>> getBindings() {
		return new HashMap<Class<? extends IEvent>, Collection<EventHandler>>(bindings);
	}
	public Set<IEventListener> getRegisteredListeners() {
		return new HashSet<IEventListener>(registeredListeners);
	}

}
