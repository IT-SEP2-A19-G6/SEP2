package client.view.login;

import client.view.ViewHandler;
import client.viewmodel.login.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class LoginViewController {

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label loginResultLabel;
    @FXML
    private Button loginButton, cancelButton;

    private LoginViewModel loginViewModel;




    public void init(LoginViewModel loginViewModel){
        this.loginViewModel = loginViewModel;
        userNameTextField.textProperty().bindBidirectional(loginViewModel.userNameProperty());
        passwordTextField.textProperty().bindBidirectional(loginViewModel.passwordProperty());
        loginResultLabel.textProperty().bindBidirectional(loginViewModel.loginResultProperty());
        userNameTextField.focusTraversableProperty().bindBidirectional(loginViewModel.getIsInFocus());
        passwordTextField.focusTraversableProperty().bindBidirectional(loginViewModel.getIsInFocus());
        loginButton.focusTraversableProperty().bindBidirectional(loginViewModel.getIsInFocus());
        cancelButton.focusTraversableProperty().bindBidirectional(loginViewModel.getIsInFocus());
        loginViewModel.setIsInFocus(false);
        loginViewModel.loginResponseProperty().addListener((observableValue, s, t1) -> {
            if(t1.contains("login accepted"))
                if (t1.contains("User")){
                    ViewHandler.getInstance().openMainView();
                }
        });
    }


    public void onLoginButton(ActionEvent actionEvent) {
        loginViewModel.validateLogin();
    }

    public void onCancelButton(ActionEvent actionEvent) {
        loginViewModel.clearFields();
    }

    public void OnEnter(ActionEvent actionEvent) {
        loginViewModel.validateLogin();
    }

    public void setInFocus(MouseEvent mouseEvent) {
        loginViewModel.setIsInFocus(true);
    }

    public void openSignUp(MouseEvent mouseEvent) {
        ViewHandler.getInstance().openSignUpView();
    }
}
