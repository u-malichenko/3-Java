package Lesson_7.DZ_My;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainTestClass {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        processing(TestClass.class);
    }

    public  static void processing(Class c) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = c.getDeclaredMethods();
        List<Method> list = new ArrayList<Method>();
        for (Method o: methods) {
            if(o.isAnnotationPresent(Test.class)) {
                int priority = o.getAnnotation(Test.class).priority();
                if(priority < 1 || priority > 100) throw  new RuntimeException("Priority exception!");
                list.add(o);
            }
        }
        list.sort((Method o1, Method o2) -> o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority());
        for (Method o: methods) {
            if(o.isAnnotationPresent(Before.class)) {
                if(list.get(0).isAnnotationPresent(Before.class))
                    throw new RuntimeException("Before exception");
                list.add(0, o);
            }
            if(o.isAnnotationPresent(After.class)) {
                if(list.get(list.size() - 1).isAnnotationPresent(After.class))
                    throw new RuntimeException("After exception");
                list.add(o);
            }
        }
        for (Method o: list) {
            o.invoke(null);
        }
    }
}