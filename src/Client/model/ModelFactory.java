package Client.model;

import Client.model.login.LoginModel;
import Client.model.login.LoginModelHandler;

public class ModelFactory {
    private LoginModel model;

    public LoginModel getModel() {
        if (model == null) {
            model = new LoginModelHandler();
        }
        return model;
    }
}
