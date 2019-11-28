package client.viewModel.login;

import client.model.login.ILoginModel;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.Request;
import shared.Response;
import java.beans.PropertyChangeEvent;

public class LoginViewModel {
    private ILoginModel loginModel;
    private StringProperty loginResult;
    private StringProperty username;
    private StringProperty password;
    private BooleanProperty isInFocus;

    public LoginViewModel(ILoginModel loginModel) {
        this.loginModel = loginModel;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        loginResult =  new SimpleStringProperty();
        isInFocus = new SimpleBooleanProperty();
        addListeners();
    }

    private void addListeners(){
        loginModel.addPropertyChangeListener(Request.TYPE.LOGIN_RESPONSE.name(), this::handleResponse);
    }

    public BooleanProperty getIsInFocus(){
        return this.isInFocus;
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
        if(username.getValue()==null || username.getValue().equals("")){
            loginResult.setValue("Enter username");
        }else if(password.getValue()==null || password.getValue().equals("")) {
            loginResult.setValue("Enter password");
        }else {
            loginResult.setValue("");
            loginModel.validateLogin(username.getValue(), password.getValue());
        }
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Response result = (Response) propertyChangeEvent.getNewValue();
        if(loginResult!=null /*|| loginResult.equals("")*/) {
            Platform.runLater(()->{
                loginResult.setValue(result.getMessage());
                    });
            if (result.getMessage().contains("login accepted")){
                Platform.runLater(()->{
                    clearFields();
                    //Change view here
                });
            }
        }
    }

    public void clearFields() {
        username.setValue("");
        password.setValue("");
    }

    public void setIsInFocus(boolean bool){
        isInFocus.set(bool);
    }


}
