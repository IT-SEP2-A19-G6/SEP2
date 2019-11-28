package client.model;

import client.model.login.ILoginModel;
import client.model.login.LoginModelHandler;
import client.network.ClientFactory;

public class ModelFactory {
    private ILoginModel loginModel;
    private ClientFactory clientFactory;

    public ModelFactory(ClientFactory clientFactory){
        this.clientFactory = clientFactory;
    }

    public ILoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelHandler(clientFactory.getLoginClient());
        }
        return loginModel;
    }
}
