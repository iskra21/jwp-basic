package core.ref;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;

import org.junit.Test;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;
        for (Method method : clazz.getDeclaredMethods()) {
        	MyTest test = method.getDeclaredAnnotation(MyTest.class);
        	if (test != null) {
        		method.invoke(clazz.newInstance());
        	}
        }
    }
}
