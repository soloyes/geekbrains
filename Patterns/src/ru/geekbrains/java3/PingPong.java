package ru.geekbrains.java3;

public class PingPong implements Runnable {
    private static volatile boolean ping;
    private static final Object lock = new Object();

    @Override
    public void run() {
        while (true) {
            System.out.println(ping ? "ping" : "pong");
            change();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void change() {
        synchronized (lock) {
            ping = !ping;
        }
    }

    public static void main(String[] args) {
        new PingPong().run();
        new PingPong().run();
    }
}
