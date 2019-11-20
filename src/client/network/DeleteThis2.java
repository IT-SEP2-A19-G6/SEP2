package client.network;

import client.model.ModelFactory;

public class DeleteThis2 {

    public static void main(String[] args) {
        ModelFactory modelFactory = new ModelFactory();
        ClientSocket clientSocket = new ClientSocket(modelFactory);

        clientSocket.start();
    }
}
