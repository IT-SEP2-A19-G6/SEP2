package client.viewModel.login;

import client.model.login.LoginModel;
import client.view.ViewHandler;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class LoginViewModel {
    private LoginModel loginModel;
    private ViewHandler viewHandler;
    private StringProperty loginResult;
    private StringProperty username;
    private StringProperty password;

    public LoginViewModel(LoginModel loginModel, ViewHandler viewHandler) {
        this.loginModel = loginModel;
        this.viewHandler = viewHandler;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        loginResult =  new SimpleStringProperty();
        loginModel.addListener("LoginResult", this::onLoginResult);
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
        loginModel.validateLogin(username.getValue(), password.getValue());
    }

    public void clearFields() {
        username.setValue("");
        password.setValue("");
    }


}
