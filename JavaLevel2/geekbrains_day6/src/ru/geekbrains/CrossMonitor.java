package ru.geekbrains;

import java.io.DataInputStream;
import java.io.IOException;

public class CrossMonitor extends Thread {

    private DataInputStream in;

    public CrossMonitor(DataInputStream in) {
        this.in = in;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        for (; ; ) {
            String s = null;
            try {
                s = this.in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!s.isEmpty())
                System.out.println("\n" + s);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
