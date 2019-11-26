package client.viewModel.login;

import client.model.login.ILoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.clients.Client;
import shared.clients.User;

import java.beans.PropertyChangeEvent;

public class LoginViewModel {
    private ILoginModel loginModel;
    private StringProperty loginResult;
    private StringProperty username;
    private StringProperty password;

    public LoginViewModel(ILoginModel loginModel) {
        this.loginModel = loginModel;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        loginResult =  new SimpleStringProperty();
        loginModel.addPropertyChangeListener("LoginResult", this::onLoginResult);
    }


    private void onLoginResult(PropertyChangeEvent propertyChangeEvent) {
        String result = (String)propertyChangeEvent.getNewValue();
        if("OK".equals(result)) {
            clearFields();
        }
        loginResult.setValue(result);
    }

    public StringProperty userNameProperty() {
        return this.username;
    }

    public StringProperty passwordProperty() {
        return this.password;
    }

    public StringProperty loginResultProperty() {
        return loginResult;
    }

    public void validateLogin() {

        if(username.getValue()==null){
            loginResult.setValue("Login require username...");
        }else if(password.getValue()==null) {
            loginResult.setValue("Login require password...");
        }else if(loginModel.validateLogin(username.getValue(), password.getValue()) ==
                new Client(username.getValue().toString(), password.getValue().toString())){

        }
            loginModel.validateLogin(username.getValue(), password.getValue());
    }

    public void clearFields() {
        username.setValue("");
        password.setValue("");
        loginResult.setValue("");
    }


}
