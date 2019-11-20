package Server.network;

import Server.model.ServerModelFactory;
import Shared.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {

private ObjectOutputStream outputToClient;
private ObjectInputStream inputFromClient;

Socket socket;
ServerModelFactory serverModelFactory;

    public ServerSocketHandler(Socket socket, ServerModelFactory serverModelFactory) {
        this.serverModelFactory = serverModelFactory;

        try {
            outputToClient = new ObjectOutputStream(socket.getOutputStream());
            inputFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ServerSocketHandler EXCEPTION: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Waiting for request from client...");
                Request request =(Request) inputFromClient.readObject();

                if(request.type == Request.TYPE.ADD_USER) {
                    System.out.println("User have been added " + request.object);
                    //TODO give the destination and what to do if the type is ADD_USER.
                } else if (request.type == Request.TYPE.LOGIN_USER) {
                    System.out.println("User have logged in... " + request.object);
                    //TODO give the destination and what to do if the type is LOGIN_USER.
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
