package server.model;

import server.persistence.DataFactory;

public class ServerModelFactory {
    DataFactory dataFactory;

    public ServerModelFactory(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }
}
