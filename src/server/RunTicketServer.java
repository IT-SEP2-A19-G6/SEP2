package server;

import server.model.ServerModelFactory;
import server.network.SocketServer;

public class RunTicketServer {

    public static void main(String[] args) {
        ServerModelFactory serverModelFactory = new ServerModelFactory();
        SocketServer socketServer = new SocketServer(serverModelFactory);

        socketServer.start();
    }
}
