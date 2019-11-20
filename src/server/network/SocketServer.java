package server.network;

import server.model.ServerModelFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerModelFactory serverModelFactory;

    public SocketServer(ServerModelFactory serverModelFactory) {
        this.serverModelFactory = serverModelFactory;
    }

    public void start() {
        System.out.println("Server starting...");

        try {
        ServerSocket serverSocket = new ServerSocket(2920);

        while (true) {
            System.out.println("Waiting for clients...");

            Socket socket = serverSocket.accept();

            ServerSocketHandler serverSocketHandler = new ServerSocketHandler(socket, serverModelFactory);
            System.out.println("Client connected...");

            Thread t = new Thread(serverSocketHandler, "socketThread");
            t.start();
        }

    } catch (IOException e) {
        System.out.println("RunTicketServer EXCEPTION " + e.getMessage());
    }
    }

}
