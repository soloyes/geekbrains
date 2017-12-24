package ru.geekbrains;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        fullFiles();
        task1();
        task2();
        task3();
    }

    private static void task3() throws IOException {
        System.out.print("ten MB read time: ");
        long t = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = new RandomAccessFile("tenMB", "r");
        File file = new File("tenMB");
        long filesize = file.length();
        System.out.println(System.currentTimeMillis() - t + "ms");
        System.out.println("file size = " + filesize);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int pagesize = 1800;
        long pages = filesize / pagesize;

        System.out.println("Total pages: " + pages + " input page");
        String line;
        for (; !(line = reader.readLine()).equals("q"); ) {
            int page = -1;
            //Check input
            for (; ; ) {
                try {
                    page = Integer.parseInt(line);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Not a page");
                    break;
                }
            }
            if (page > pages || page < 0) {
                System.out.println("Not a page");
                continue;
            }
            byte[] b = new byte[pagesize];
            randomAccessFile.seek(page * pagesize);
            int read = randomAccessFile.read(b, 0, pagesize);
            for (int i = 0; i < read; i++) {
                System.out.print(b[i]);
                if (i % 50 == 0) System.out.println();
            }
        }
        randomAccessFile.close();
    }


    private static void task2() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("fileOut");
        for (int i = 1; i <= 5; i++) {
            FileInputStream fileInputStream = new FileInputStream("file" + i);
            for (; fileInputStream.available() > 0; ) {
                fileOutputStream.write(fileInputStream.read());
            }
            fileInputStream.close();
        }
        fileOutputStream.close();
    }

    private static void task1() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("file0");
        byte[] b = new byte[500];
        int read = fileInputStream.read(b);
        for (int i = 0; i < read; i++) {
            System.out.print(b[i]);
        }
        System.out.println();
        fileInputStream.close();
    }

    private static void fullFiles() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("file0");
        byte[] b = new byte[50];
        for (int i = 0; i < b.length; i++) {
            b[i] = 65;
        }

        fileOutputStream.write(b);

        byte[] b1 = new byte[100];
        for (int i = 0; i < b1.length; i++) {
            b1[i] = 65;
        }

        for (int i = 1; i <= 5; i++) {
            String f = "file" + i;
            fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(b1);
        }

        byte[] b2 = new byte[1024];
        for (int i = 0; i < b2.length; i++) {
            b2[i] = 65;
        }

        fileOutputStream = new FileOutputStream("tenMB");

        int MB = 10;
        for (int i = 0; i <= 1024 * MB; i++) {
            fileOutputStream.write(b2);
        }

        fileOutputStream.close();
    }
}
