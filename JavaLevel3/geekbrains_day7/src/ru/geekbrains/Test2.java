package ru.geekbrains;

public class Test2 {

    public Test2(){
        System.out.println("Class " + getClass().getCanonicalName() + ":");
    }

    @Test (priority = 1)
    public void test1(){
        System.out.println("Test1");
    }

    @Test (priority = 2)
    public void test2(){
        System.out.println("Test2");
    }
}
