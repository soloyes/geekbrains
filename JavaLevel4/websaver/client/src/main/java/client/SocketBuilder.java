package client;

import java.io.IOException;
import java.net.Socket;

public class SocketBuilder {
    private static final String host = "localhost";
    private static final Integer port = 9999;

    public static Socket getSocket() throws IOException {
        return new Socket(host, port);
    }
}
