package server;

import server.model.ServerModelFactory;
import server.network.SocketServer;
import server.persistence.DataFactory;
import shared.exceptions.LoginDisabledException;
import shared.exceptions.IncorrectCredentialsException;

public class RunTicketServer {

    public static void main(String[] args) {
        DataFactory dataFactory = new DataFactory();
        ServerModelFactory serverModelFactory = new ServerModelFactory(dataFactory);
        SocketServer socketServer = new SocketServer(serverModelFactory);

        socketServer.start();

    }
}
