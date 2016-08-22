package org.huajistudio.gameoflife.event;

public enum EnumEventPriority {
	LOWEST(0),
	LOW(25),
	NORMAL(50),
	HIGH(75),
	HIGHEST(100);

	private int priority;

	EnumEventPriority(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
}
