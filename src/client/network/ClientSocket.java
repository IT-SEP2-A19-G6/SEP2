package client.network;

import client.model.ModelFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {

    ModelFactory modelFactory;
    ClientSocketHandler clientSocketHandler;

    public ClientSocket(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public void start() {
        System.out.println("ClientSocket starting...");

        try {
            Socket socket = new Socket("localhost", 0000);
            ObjectOutputStream outputToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputFromServer = new ObjectInputStream((socket.getInputStream()));

            Thread t = new Thread(clientSocketHandler);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
