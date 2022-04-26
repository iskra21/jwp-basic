package core.ref;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length != 0) {
        	int i = 0;
        	for (Field field:fields) {
        		System.out.println("Field["+ i++ +"]: " + field.getType().getTypeName() + " " + field.getName());
        	}
        }
        
        Constructor[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length != 0) {
        	int i = 0;
        	for (Constructor cons:constructors) {
        		System.out.println("Constructor["+ i++ +"]: " + cons.getName());
        	}
        }
        
        Method[] meds = clazz.getDeclaredMethods();
        if (meds.length != 0) {
        	int i = 0;
        	for (Method med:meds) {
        		System.out.println("Method["+ i++ +"]: " + med.getReturnType().getTypeName() + " " + med.getName());
        	}        	
        }
        
    }
    
    @Test
    public void newInstanceWithConstructorArgs() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
        Constructor<User>[] constructors = (Constructor<User>[])clazz.getDeclaredConstructors();
        if (constructors.length != 0) {
        	for (int i = 0; i < constructors.length; i++) {
        		User user = constructors[i].newInstance("iskra21", "dddd", "Hyungjoon", "iskra21@gmail.com");
        		logger.debug("Constructor[{}]: {}", i, user.toString());
        	}
        }
    }
    
    @Test
    public void privateFieldAccess() throws IllegalArgumentException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        Student student = new Student();
        logger.debug(clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length != 0) {
        	for (Field field : fields) {
        		int modifier = field.getModifiers();
        		String modStr = Modifier.toString(modifier);
        		logger.debug("Modifier: {}", modStr);
        		if (!modStr.matches("private")) continue;
        		field.setAccessible(true);
        		String fieldName = field.getName();
        		logger.debug("field Name: {}", fieldName);
        		if (fieldName.matches("name")) {
        			field.set(student, "형준");
        		}
        		else if (fieldName.matches("age")) {
        			field.setInt(student, 12);
        		}
        	}
        }
        logger.debug("student class: {}, {}", student.getName(), student.getAge());
    }
}
