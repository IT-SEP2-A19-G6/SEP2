package server.model;

import server.model.login.LoginServerModel;
import server.model.login.LoginServerModelHandler;
import server.persistence.DataFactory;

public class ServerModelFactory {
    private DataFactory dataFactory;
    private LoginServerModel loginServerModel;

    public ServerModelFactory(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }


}
