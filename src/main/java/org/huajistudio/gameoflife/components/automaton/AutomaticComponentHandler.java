package org.huajistudio.gameoflife.components.automaton;

import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.components.grid.Grid;

import java.lang.reflect.InvocationTargetException;
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

	public void invoke(Grid grid) {
		try {
			method.invoke(component, grid);
		} catch (InvocationTargetException | IllegalAccessException e) {
			GameOfLife.LOGGER.error("Exception when invoking AutomaticComponentHandler " + this.component + " for grid " + grid.toString(), e);
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
