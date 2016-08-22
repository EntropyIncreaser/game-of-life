package org.huajistudio.gameoflife.event;

import org.huajistudio.gameoflife.GameOfLife;

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
			GameOfLife.LOGGER.error("Exception when invoking EventHandler " + this.listener + " for event " + event.toString(), e);
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
