package client.view.login;

import client.view.ViewHandler;
import client.viewModel.login.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;


public class LoginViewController {

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;


    public void init(ViewHandler viewHandler, LoginViewModel loginViewModel){
    this.loginViewModel = loginViewModel;
    this.viewHandler = viewHandler;

    userNameTextField.textProperty().bindBidirectional(loginViewModel.userNameProperty());
    passwordTextField.textProperty().bindBidirectional(loginViewModel.passwordProperty());
}

    public void onLoginButton(ActionEvent actionEvent) {
        loginViewModel.clearFields();
        loginViewModel.loginAttempt();
    }

    public void onCancelButton(ActionEvent actionEvent) {
        loginViewModel.clearFields();
    }
}
