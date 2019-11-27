package client.model;

import client.model.login.ILoginModel;
import client.model.login.LoginModelHandler;

public class ModelFactory {
    private ILoginModel loginModel;

    public ILoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelHandler(this);
        }
        return loginModel;
    }
}
