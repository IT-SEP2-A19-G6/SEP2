package server.network;

import server.model.ServerModelFactory;
import shared.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {

private ObjectOutputStream outputToClient;
private ObjectInputStream inputFromClient;
private ServerModelFactory serverModelFactory;


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

                //TODO remember to change the method depending on what kind of object needs to be casted.
                Request request =(Request) inputFromClient.readObject();

                //TODO create methods to take care of the newly received objects.

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
