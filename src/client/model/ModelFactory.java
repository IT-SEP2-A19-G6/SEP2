package client.model;

import client.model.login.ILoginModel;
import client.model.login.LoginModelHandler;
import client.model.user.IUserModel;
import client.model.user.UserModelHandler;
import client.network.ClientFactory;

public class ModelFactory {
    private ClientFactory clientFactory;
    private ILoginModel loginModel;
    private IUserModel userModel;

    public ModelFactory(ClientFactory clientFactory){
        this.clientFactory = clientFactory;
    }

    public ILoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelHandler(clientFactory.getLoginClient());
        }
        return loginModel;
    }

    public IUserModel getUserModel(){
        if (userModel == null) {
            userModel = new UserModelHandler(clientFactory.getUserClient());
        }
        return userModel;
    }
}
