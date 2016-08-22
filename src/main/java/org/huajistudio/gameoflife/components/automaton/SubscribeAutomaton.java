package org.huajistudio.gameoflife.components.automaton;

import org.huajistudio.gameoflife.event.EnumEventPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * Set this annotation on a method will make the method execute when it cycles.
 */
public @interface SubscribeAutomaton {
	EnumEventPriority priority() default EnumEventPriority.NORMAL;
}
