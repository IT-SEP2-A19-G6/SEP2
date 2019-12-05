package client.model;

import client.model.login.ILoginModel;
import client.model.login.LoginModelHandler;
import client.model.signup.ISignUpModel;
import client.model.signup.SignUpModelHandler;
import client.model.user.IUserModel;
import client.model.user.UserModelHandler;
import client.network.ClientFactory;

public class ModelFactory {
    private ClientFactory clientFactory;
    private ILoginModel loginModel;
    private IUserModel userModel;
    private ISignUpModel signUpModel;

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

    public ISignUpModel getSignUpModel() {
        if (signUpModel == null){
            signUpModel = new SignUpModelHandler(clientFactory.getSignUpClient());
        }
        return signUpModel;
    }
}
