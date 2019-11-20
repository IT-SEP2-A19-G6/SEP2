package client.model;

import client.model.login.LoginModel;
import client.model.login.LoginModelHandler;

public class ModelFactory {
    private LoginModel loginModel;

    public LoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelHandler();
        }
        return loginModel;
    }
}
