package ru.geekbrains;

public class MFU {
    public static synchronized void print(int i){
        System.out.println("print " + i + " page");
    }

    public static synchronized void scan(int i){
        System.out.println("scan " + i + " page");
    }
}
