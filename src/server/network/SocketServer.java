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

        try {
        ServerSocket serverSocket = new ServerSocket(2920);

        System.out.println("Server is running...");
        while (true) {

            Socket socket = serverSocket.accept();
            ServerSocketHandler serverSocketHandler = new ServerSocketHandler(socket, serverModelFactory);
            Thread t = new Thread(serverSocketHandler, "socketThread");
            t.start();
        }

    } catch (IOException e) {
        System.out.println("RunTicketServer EXCEPTION " + e.getMessage());
    }
    }

}
