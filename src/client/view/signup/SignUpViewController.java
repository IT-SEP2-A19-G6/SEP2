package client.view.signup;

import client.view.ViewHandler;
import client.viewmodel.signup.SignUpViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignUpViewController {

    @FXML
    TextField newUserField;

    @FXML
    PasswordField newPasswordField, confirmPasswordField;

    @FXML
    Label feedbackLabel;

    @FXML
    Button submitButton, cancelButton;

    private SignUpViewModel signUpViewModel;



    public void init(SignUpViewModel signupViewModel){

        this.signUpViewModel = signupViewModel;
        feedbackLabel.textProperty().bind(signupViewModel.feedbackStringProperty());
        setFocus(false);
        signupViewModel.responseProperty().addListener((observableValue, s, t1) -> {
            if(t1.equals("Success")){
                signupViewModel.resetFeedBackProperty();
                ViewHandler.getInstance().openLoginView();
            }
        });
    }


    public void onEnterKey() {
        signUpViewModel.requestSignup(newUserField.getText().toLowerCase(), newPasswordField.getText(), confirmPasswordField.getText());
    }


    public void onMouseClick() {
        setFocus(true);
    }



    private void setFocus(Boolean bool) {
        newUserField.setFocusTraversable(bool);
        newPasswordField.setFocusTraversable(bool);
        confirmPasswordField.setFocusTraversable(bool);
    }


    public void onCancelButton() {
        ViewHandler.getInstance().openLoginView();
    }
}
