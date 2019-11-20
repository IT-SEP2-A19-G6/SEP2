package client.viewModel.login;

import client.model.login.LoginModel;
import client.view.ViewHandler;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    private StringProperty userName;
    private StringProperty password;
    private LoginModel loginModel;
    private ViewHandler viewHandler;

    public LoginViewModel(LoginModel loginModel, ViewHandler viewHandler) {
        this.loginModel = loginModel;
        this.viewHandler = viewHandler;
        userName = new SimpleStringProperty();

    }


    public Property<String> userNameProperty() {
        return this.userName;
    }

    public Property<String> passwordProperty() {
        return this.password;
    }

    public void loginAttempt() {
        //TODO
    }

    public void cancelLogin() {
        userName.setValue("");
        password.setValue("");
    }
}
