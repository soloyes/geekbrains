package server.service;

import server.ServerSocketBuilder;

import java.io.IOException;
import java.net.ServerSocket;

public class Core {
    private ServerSocket serverSocket;

    public Core(){
        try {
            serverSocket = new ServerSocketBuilder().getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
