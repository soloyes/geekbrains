package ru.geekbrains;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestClass {

    static void start(Class clazz){
        //All methods:
        Method[] methods = clazz.getMethods();
        //Store all @Test methods here (with priority):
        Map<Method, Integer> tests = new HashMap<>();
        //Mark @BeforeSuite, @AfterSuite method here:
        Method beforeSuitMethod = null;
        Method afterSuitMethod = null;

        //@BeforeSuite, @AfterSuite counter:
        int before = 0;
        int after = 0;

        for (Method m: methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)){
                before++;
                beforeSuitMethod = m;
            }
            if (m.isAnnotationPresent(AfterSuite.class)){
                after++;
                afterSuitMethod = m;
            }
            if (before > 1 || after > 1) {
                throw new RuntimeException("Check before/after suite");
            }
            else if (m.isAnnotationPresent(Test.class)){
                Test test = m.getAnnotation(Test.class);
                tests.put(m, test.priority());
            }
        }

        //Instance of "test-method" which should be invoked:
        Object o = null;

        //Sort @Test methods by priority:
        List<Map.Entry<Method,Integer>> list = new LinkedList<>(tests.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Method, Integer>>() {
            @Override
            public int compare(Map.Entry<Method, Integer> o1, Map.Entry<Method, Integer> o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        });

        try {
            o = clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (before > 0){
            try {
                beforeSuitMethod.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<Method, Integer> m: list) {
            try {
                m.getKey().invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        if (after > 0) {
            try {
                afterSuitMethod.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static void start(String className){
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        start(clazz);
    }
}
