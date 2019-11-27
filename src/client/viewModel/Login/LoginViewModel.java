package client.viewModel.login;

import client.model.login.ILoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.Request;

import java.beans.PropertyChangeEvent;

public class LoginViewModel {
    private ILoginModel loginModel;
    private StringProperty loginResult;
    private StringProperty username;
    private StringProperty password;

    public LoginViewModel(ILoginModel loginModel) {
        this.loginModel = loginModel;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        loginResult =  new SimpleStringProperty();
        loginModel.addPropertyChangeListener(Request.TYPE.LOGIN_RESPONSE.name(), this::onLoginResult);
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

//    public void loginResultListener() {
//        loginModel.addPropertyChangeListener(Request.TYPE.LOGIN_RESPONSE.name(), this::onLoginResult);
//    }

    private void onLoginResult(PropertyChangeEvent propertyChangeEvent) {
        String result = (String)propertyChangeEvent.getNewValue();
        if(loginResult!=null || loginResult.equals("")) {
            clearFields();
        }
        loginResult.setValue(result);
        System.out.println(result);
    }

    public void clearFields() {
        username.setValue("");
        password.setValue("");
        loginResult.setValue("");
    }


}
