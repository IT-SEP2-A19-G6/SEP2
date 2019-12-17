package client.viewmodel.signup;


import client.model.signup.ISignUpModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import shared.Request;
import shared.Response;

import java.beans.PropertyChangeEvent;

public class SignUpViewModel {
    private final ISignUpModel signUpModel;
    private final StringProperty feedbackProperty;

    public SignUpViewModel(ISignUpModel signUpModel){
        this.signUpModel = signUpModel;
        addListeners();
        feedbackProperty = new SimpleStringProperty();
    }

    private void addListeners() {
        signUpModel.addPropertyChangeListener(Request.TYPE.SIGNUP_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Response response = (Response) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> feedbackProperty.setValue(response.getMessage()));
    }

    public ObservableValue<? extends String> feedbackStringProperty() {
        return feedbackProperty;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void requestSignup(String newUsername, String newPassword, String confirmPassword) {
        if (newUsername.equals("")){
            feedbackProperty.setValue("Enter username!");
        } else if (newPassword.equals("") || confirmPassword.equals("")){
            feedbackProperty.setValue("Enter both passwords!");
        } else if (!(newPassword.equals(confirmPassword))) {
                feedbackProperty.setValue("Passwords are not the same");
        } else{
            signUpModel.requestSignUp(newUsername.toLowerCase(), newPassword);
        }
    }

    public ObservableValue<? extends String> responseProperty() {
        return feedbackProperty;
    }

    public void resetFeedBackProperty() {
        feedbackProperty.setValue("");
    }
}

