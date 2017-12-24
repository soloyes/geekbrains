package ru.geekbrains;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static Object lock = new Object();
    private static volatile char current = 'A';

    private static volatile int toClose = 0;

    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    private static void task3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    MFU.print(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    MFU.scan(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static void task2() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter finalWriter = writer;
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (finalWriter) {
                        for (int i = 0; i < 10; i++) {
                            finalWriter.write("A");
                            finalWriter.newLine();
                            finalWriter.flush();
                        }
                        toClose++;
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (finalWriter) {
                        for (int i = 0; i < 10; i++) {
                            finalWriter.write("B");
                            finalWriter.newLine();
                            finalWriter.flush();
                        }
                        toClose++;
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (finalWriter) {
                        for (int i = 0; i < 10; i++) {
                            finalWriter.write("C");
                            finalWriter.newLine();
                            finalWriter.flush();
                        }
                        toClose++;
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        if (toClose == 3) try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task1() {
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock) {
                        while (current != 'A') {
                            lock.wait();
                        }
                        System.out.print("A");
                        current = 'B';
                        lock.notifyAll();
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock) {
                        while (current != 'B') {
                            lock.wait();
                        }
                        System.out.print("B");
                        current = 'C';
                        lock.notifyAll();
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock) {
                        while (current != 'C') {
                            lock.wait();
                        }
                        System.out.print("C");
                        current = 'A';
                        lock.notifyAll();
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
