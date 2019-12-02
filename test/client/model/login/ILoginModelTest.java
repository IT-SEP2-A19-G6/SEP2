package client.model.login;

import client.viewModel.login.LoginViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ILoginModelTest {

    private LoginViewModel loginViewModel;


    @BeforeEach
    void setUp() {
        LoginModelHandler model = new LoginModelHandler();
        loginViewModel = new LoginViewModel(model);
    }

    @Test
    void validateLogin() {
        StringProperty resultProperty = new SimpleStringProperty();
        StringProperty username = new SimpleStringProperty();
        StringProperty password = new SimpleStringProperty();

        resultProperty.bind(loginViewModel.loginResultProperty());
        username.bindBidirectional(loginViewModel.userNameProperty());
        password.bindBidirectional(loginViewModel.passwordProperty());

        username.set("user1");
        password.set("user");
        loginViewModel.validateLogin();


    }

    @Test
    void loginResult() {

    }
}