package ru.geekbrains.server;

import ru.geekbrains.CrossMonitor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket;

    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("Server started!");
            socket = serverSocket.accept();
            System.out.println("Incoming connection! From " + socket.getInetAddress());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream in = new DataInputStream(socket.getInputStream());

            new CrossMonitor(in).start();

            String s = "";
            for (;!s.equals("exit");) {
                System.out.print("Server: ");
                s = reader.readLine();
                out.writeUTF("Message from Server: " + s);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
