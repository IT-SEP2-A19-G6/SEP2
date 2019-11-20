package client.network;

import client.model.ModelFactory;
import client.model.login.LoginModel;
import shared.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable {

    private Socket socket;
    private ObjectInputStream inputFromServer;
    private ObjectOutputStream outputToServer;
    ModelFactory modelFactory;

    public ClientSocketHandler(Socket socket, ModelFactory modelFactory) {
        this.socket = socket;
        this.modelFactory = modelFactory;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Request request = (Request) inputFromServer.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ClientSocketHandler EXCEPTION: " + e.getMessage());
        }

    }

    public void sendToServer(Request.TYPE type, Object object) {
        try {
            outputToServer.writeObject(new Request(type, object));
        } catch (IOException e) {
            System.out.println("ClientSocketHandler - sendToServer EXCEPTION: " + e.getMessage());
        }
    }
}
