package client.view.login;

import client.view.ViewHandler;
import client.viewModel.login.LoginViewModel;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label loginResultLabel;

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;


    public void init(ViewHandler viewHandler, LoginViewModel loginViewModel){
    this.loginViewModel = loginViewModel;
    this.viewHandler = viewHandler;

    userNameTextField.textProperty().bindBidirectional(loginViewModel.userNameProperty());
    passwordTextField.textProperty().bindBidirectional(loginViewModel.passwordProperty());

    loginResultLabel.textProperty().bindBidirectional(loginViewModel.loginResultProperty());
    loginResultLabel.textProperty().addListener(this::onLoginResult);
    }
    private void onLoginResult(Observable observable, String old, String newVal) {
        // if the result of modelimpls login attempt is OK, then I change views. Otherwise do nothing
        // the label will contain the login error message, because the VM updates it.
        if("OK".equals(newVal)) {
            viewHandler.openLoginViewSuccessfulAttempt();
        }
    }

    public void onLoginButton(ActionEvent actionEvent) {
        loginViewModel.validateLogin();
    }

    public void onCancelButton(ActionEvent actionEvent) {
        loginViewModel.clearFields();
    }
}
