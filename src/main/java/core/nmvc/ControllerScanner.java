package core.nmvc;

import java.util.Set;

import org.reflections.Reflections;

import core.annotation.Controller;

public class ControllerScanner {
	public void scanController () throws InstantiationException, IllegalAccessException {
		Reflections reflections = new Reflections("next.controller");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
		
		for (Class<?> clazz : annotated) {
			clazz.newInstance();
		}
	}
}
