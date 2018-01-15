package ru.geekbrains;

public class Test1 {

    public Test1(){
        System.out.println("Class " + getClass().getCanonicalName() + ":");
    }

    @BeforeSuite
    public void bofore(){
        System.out.println("Before");
    }

    @AfterSuite
    public void after(){
        System.out.println("After");
    }

    @Test (priority = 2)
    public void test1(){
        System.out.println("Test1");
    }

    @Test (priority = 1)
    public void test2(){
        System.out.println("Test2");
    }
}
