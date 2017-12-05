package ru.geekbrains;

public class Main {

    private static final int size = 10000000;
    private static final int h = size / 2;
    private float[] arr = new float[size];
    {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
    }

    private void method1(){
        System.out.println("method1:");
        Thread t = new Thread(new t(arr), "arr");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void method2(){
        System.out.println("\nmethod2:");
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
            System.arraycopy(arr, h, a2, 0, h);
            System.out.print("Array divide: ");
        System.out.println(System.currentTimeMillis() - a);

        Thread t1  = new Thread(new t(a1), "a1");
        Thread t2 = new Thread(new t(a2), "a2");

        t1.start();t2.start();

        try {
            t1.join();t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        a = System.currentTimeMillis();

        System.out.print("Array concat: ");
            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);
    }

    private static class t implements Runnable{

        private float[] a;

        @Override
        public void run() {
            long time = System.currentTimeMillis();
            for (int i = 0; i < a.length; i++) {
                a[i] = (float)(a[i] *
                        Math.sin(0.2f + i / 5) *
                        Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
            System.out.println("Array " + Thread.currentThread().getName()+ " modification");
            System.out.println(System.currentTimeMillis() - time);

        }

        t (float[] a) {
            this.a = a;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.method1();
        main.method2();
    }
}
