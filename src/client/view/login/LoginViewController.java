package client.view.login;

import client.view.ViewHandler;
import client.viewModel.login.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class LoginViewController {

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label loginResultLabel;

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;


    public void init(ViewHandler viewHandler, LoginViewModel loginViewModel){
        this.viewHandler = viewHandler;
        this.loginViewModel = loginViewModel;
        userNameTextField.textProperty().bindBidirectional(loginViewModel.userNameProperty());
        passwordTextField.textProperty().bindBidirectional(loginViewModel.passwordProperty());
        loginResultLabel.textProperty().bindBidirectional(loginViewModel.loginResultProperty());
    }

    //TODO create action onEnter

    public void onLoginButton(ActionEvent actionEvent) {
        loginViewModel.validateLogin();
    }

    public void onCancelButton(ActionEvent actionEvent) {
        loginViewModel.clearFields();
    }
}
