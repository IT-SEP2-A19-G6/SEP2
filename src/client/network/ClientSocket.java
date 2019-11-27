package client.network;

import client.model.ModelFactory;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket {

    private ModelFactory modelFactory;
    private ClientSocketHandler clientSocketHandler;

    public ClientSocket(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public void start() {
        System.out.println("ClientSocket starting...");

        try {
            Socket socket = new Socket("localhost", 2920);
            clientSocketHandler = new ClientSocketHandler(socket, modelFactory);

            Thread t = new Thread(clientSocketHandler);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        clientSocketHandler.closeConnection();
    }
}

