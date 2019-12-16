package server;

import server.model.ServerModelFactory;
import server.network.SocketServer;
import server.persistence.DataFactory;

class RunTicketServer {

    public static void main(String[] args) {
        DataFactory dataFactory = new DataFactory();
        ServerModelFactory serverModelFactory = new ServerModelFactory(dataFactory);
        SocketServer socketServer = new SocketServer(serverModelFactory);

        socketServer.start();

    }
}
