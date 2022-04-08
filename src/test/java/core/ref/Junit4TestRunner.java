package core.ref;

import java.lang.reflect.Method;

import org.junit.Test;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;
        Method[] methods = clazz.getDeclaredMethods();
        Junit4Test temp;
        for (Method method:methods) {
        	temp = method.getAnnotation(clazz);
        	System.out.println(temp.getName());
        	method.invoke(clazz.newInstance());
        }
    }
}
