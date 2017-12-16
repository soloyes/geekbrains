package ru.geekbrains;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import java.io.IOException;

class ClientHandler {

    ClientHandler(String name, String password){

        Thread t = new Thread(() -> {
            try {
                String msg;
                Connect.out.writeUTF("/login" + " " +  name + " " +  password);
                for(;;) {
                    msg = Connect.in.readUTF();
                    if (msg.startsWith("/clientlist")) {
                        msg = msg.replace("/clientlist ", "");
                        String[] users = msg.split(" ");
                        Platform.runLater(() -> {
                            ClientController.observableList.clear();
                            ClientController.observableList.addAll(users);
                        });
                        continue;
                    }

                    //For future auth flag
                    if (msg.startsWith("/loginok")) {
                        continue;
                    }

                    TextArea outArea = (TextArea) RootsController.mainScene.lookup("#outArea");
                    outArea.appendText(msg);
                    outArea.appendText("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                //Something to do need to be here
                try {
                    Connect.socket.close();
                } catch (IOException e) {
                    System.out.println("Socket close in trouble");
                    e.printStackTrace();
                }
                try {
                    Connect.in.close();
                } catch (IOException e) {
                    System.out.println("in close in trouble");
                    e.printStackTrace();
                }
                try {
                    Connect.out.close();
                } catch (IOException e) {
                    System.out.println("out close in trouble");
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    static boolean isInList(String login){
        try {
            Connect.out.writeUTF("/inlist " + login);
            if (Connect.in.readUTF().equals("/e_ok")) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("isInList in trouble");
            e.printStackTrace();
        }
        return false;
    }
}
