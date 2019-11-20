package server.model.login;

import server.persistence.DataFactory;

public class LoginServerModelHandler implements LoginServerModel {
    DataFactory dataFactory;

    public LoginServerModelHandler(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

}
