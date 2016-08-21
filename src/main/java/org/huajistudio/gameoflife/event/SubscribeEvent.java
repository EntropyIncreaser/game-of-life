package org.huajistudio.gameoflife.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * Set the annotation on a method will make the method listen a selected event.
 */
public @interface SubscribeEvent {
	EnumEventPriority priority() default EnumEventPriority.NORMAL;
	boolean receiveCanceled() default false;
}
