package client.service;

import client.SocketBuilder;

import java.io.IOException;
import java.net.Socket;

public class Core {

    private Socket socket;

    public Core() {
        try {
            this.socket = SocketBuilder.getSocket();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
