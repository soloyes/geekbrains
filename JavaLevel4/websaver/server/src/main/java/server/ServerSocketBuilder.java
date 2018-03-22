package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketBuilder {
    private static final int port = 9999;

    private ServerSocket instance;

    public ServerSocketBuilder(){

    }

    public ServerSocket getInstance() throws IOException{
        if (instance == null)
            return new ServerSocket(port);
        else return instance;
    }
}
