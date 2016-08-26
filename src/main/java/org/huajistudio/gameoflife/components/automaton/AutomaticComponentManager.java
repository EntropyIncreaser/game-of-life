package org.huajistudio.gameoflife.components.automaton;

import org.huajistudio.gameoflife.components.grid.Grid;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.huajistudio.gameoflife.GameOfLife.LOGGER;

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

	public void cycle(Grid grid) {
		for (AutomaticComponentHandler handler : componentHandlers) {
			handler.invoke(grid);
		}
	}
}
