package ru.geekbrains.client;

import ru.geekbrains.CrossMonitor;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;

    Client() {
        try {
            socket = new Socket("localhost", 8888);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            new CrossMonitor(in).start();

            String s = "";
            for (;!s.equals("exit");) {
                System.out.print("Client: ");
                s = reader.readLine();
                out.writeUTF("Message from Client: " + s);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
