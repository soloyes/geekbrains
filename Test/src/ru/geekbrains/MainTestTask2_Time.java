package ru.geekbrains;

import org.junit.Test;

public class MainTestTask2_Time {
    //9855 Знаков
    @Test(timeout = 15000)
    public void method1() {
        System.out.println(Main.method2(100000,6000).toString().length());
    }
}