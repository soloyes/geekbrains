package ru.geekbrains.homework;

public class Main {
    public static void main(String[] args) {
        byte a = 0;
        short b = 1;
        int c = 2;
        long d = 3L;
        float e = 0.4f;
        double f = 0.5;
        boolean g = false;
        char h = '.';
        System.out.println(method1(a, b, c ,d));
        System.out.println(method2(a, b));
        method3(a);
        System.out.println(method4(a));
        method5("Александра");
        method6(2300);
    }

    private static long method1(byte a, short b, int c, long d){
        return a * (b + (c / d));
    }

    private static boolean method2(int a, int b){
        if (a + b > 10 && a + b <= 20) return true;
        return false;
    }

    private static void method3(int a){
        if (a < 0) System.out.println("negative");
        else if (a >= 0) System.out.println("positive");
    }

    private static boolean method4(int a){
        if (a < 0) return true;
        return false;
    }

    private static void method5(String name){
        System.out.println("Привет," + name + "!");
    }

    private static void method6(int year){
        if (((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) {
            System.out.println("366");
        } else {
            System.out.println("365");
        }
    }
}
