package client.viewModel.login;

import client.model.login.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class LoginViewModel {
    private LoginModel loginModel;
    private StringProperty loginResult;
    private StringProperty username;
    private StringProperty password;

    public LoginViewModel(LoginModel loginModel) {
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
        //TODO password restriction check
        //TODO both fields should be filled before calling loginModel
       loginModel.validateLogin(username.getValue(), password.getValue());
    }

    public void clearFields() {
        username.setValue("");
        password.setValue("");
    }


}
