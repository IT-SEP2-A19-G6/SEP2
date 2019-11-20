package Server;

import Server.model.ServerModelFactory;
import Server.network.ServerSocketHandler;
import Server.network.SocketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RunTicketServer {

    public static void main(String[] args) {
        ServerModelFactory serverModelFactory = new ServerModelFactory();
        SocketServer socketServer = new SocketServer(serverModelFactory);

        socketServer.start();
    }
}
