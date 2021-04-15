package gb.j3l7;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.Annotation;
import java.util.*;

public class TestingAll {
    public static void start(Class<?> className) {
        final int MIN_PRIORITY = 1;
        final int MAX_PRIORITY = 10;
        Map<Integer, ArrayList<Method>> map = new TreeMap<>();

        for (Method method : className.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (!map.containsKey(MIN_PRIORITY - 1)){
                    ArrayList<Method> g = new ArrayList<>();
                    g.add(method);
                    map.put(MIN_PRIORITY - 1, g);
                }
                else {
                    throw new RuntimeException("Метод с аннотацией BeforeSuite уже существует!");
                }

            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                if (!map.containsKey(MAX_PRIORITY + 1)){
                    ArrayList<Method> g = new ArrayList<>();
                    g.add(method);
                    map.put(MAX_PRIORITY + 1, g);
                }
                else {
                    throw new RuntimeException("Метод с аннотацией AfterSuite уже существует!");
                }
            }
            if (method.getAnnotation(Test.class) != null) {
                Test test = method.getAnnotation(Test.class);
                if (map.containsKey(test.priority())) {
                    ArrayList<Method> a = new ArrayList<>();
                    a = map.get(test.priority());
                    a.add(method);
                   // map.get(test.priority()).add(method);
                    map.put(test.priority(),a);
                }
                else {
                    ArrayList<Method> m = new ArrayList<>();
                    m.add(method);
                    map.put(test.priority(),m); // сортировка автоматом
                }

            }
        }
/*
        System.out.println("Map:");
        for (Integer key : map.keySet()) {
            System.out.println("priority:" + key + " " + map.get(key));
        }
*/
        try {
            Object testCase = className.newInstance(); // объект для Reflections
            for (Integer key : map.keySet()) {
                for (Method m: map.get(key)) {
                    m.invoke(testCase);
                }
            //    map.get(key).invoke(testCase);
            }
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
