package server.network;

import server.model.ServerModelFactory;
import shared.util.ApplicationProperties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private final ServerModelFactory serverModelFactory;

    public SocketServer(ServerModelFactory serverModelFactory) {
        this.serverModelFactory = serverModelFactory;
    }

    public void start() {

        try {
        ServerSocket serverSocket = new ServerSocket(ApplicationProperties.INSTANCE.getConnectionPort());

        System.out.println("Server is running...");
        //noinspection InfiniteLoopStatement
        while (true) {

            Socket socket = serverSocket.accept();
            ServerSocketHandler serverSocketHandler = new ServerSocketHandler(socket, serverModelFactory);
            Thread t = new Thread(serverSocketHandler);
            t.start();
        }

    } catch (IOException e) {
        System.out.println("RunTicketServer EXCEPTION " + e.getMessage());
    }
    }

}
