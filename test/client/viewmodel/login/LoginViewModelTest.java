package client.viewmodel.login;

import client.model.login.ILoginModel;
import client.model.login.LoginModelHandler;
import client.network.login.ILoginClient;
import client.network.login.LoginClientHandler;
import client.network.socket.IClientSocketHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Request;
import shared.Response;
import testdummies.TestSocketHandler;
import java.beans.PropertyChangeEvent;
import static org.junit.jupiter.api.Assertions.*;

class LoginViewModelTest {
    private LoginViewModel loginViewModel;
    private StringProperty loginResult;


    @BeforeEach
    void setUp() {
        IClientSocketHandler socketHandler = new TestSocketHandler();
        ILoginClient loginClient = new LoginClientHandler(socketHandler);
        ILoginModel loginModel = new LoginModelHandler(loginClient);
        loginViewModel = new LoginViewModel(loginModel);
        loginModel.addPropertyChangeListener(Request.TYPE.LOGIN_RESPONSE.name(), this::handleResponse);
        new JFXPanel();
    }

    @Test
    void loginAcceptedLowercaseUsername(){
        validateLogin("correctuser", "correctPassword", "User login accepted");
    }

    @Test
    void loginAcceptedUppercaseUsername(){
        validateLogin("CORRECTUSER", "correctPassword", "User login accepted");
    }

    @Test
    void loginAcceptedMixedcaseUsername(){
        validateLogin("CoRrEctUsEr", "correctPassword", "User login accepted");
    }

    @Test
    void failedLoginWrongusername(){
        validateLogin("wronguser", "correctPassword", "Incorrect credentials");
    }

    @Test
    void failedLoginWrongPassword(){
        validateLogin("correctuser", "wrongPassword", "Incorrect credentials");
    }

    @Test
    void failedLoginLowercasePassword(){
        validateLogin("correctuser", "correctpassword", "Incorrect credentials");
    }

    @Test
    void failedLoginUppercasePassword(){
        validateLogin("correctuser", "CORRECTPASSWORD", "Incorrect credentials");
    }

    @Test
    void failedLoginCaseErrorPassword(){
        validateLogin("correctuser", "CorrectPassword", "Incorrect credentials");
    }

    @Test
    void failedLoginUsernamePasswordSwap(){
        validateLogin("correctPassword", "correctuser", "Incorrect credentials");
    }

    @Test
    void failedLoginPasswordContained(){
        validateLogin("correctuser", "correctPasswords", "Incorrect credentials");
    }

    @Test
    void failedLoginPasswordOneShort(){
        validateLogin("correctuser", "correctPasswor", "Incorrect credentials");
    }

    @Test
    void failedLoginUsernameContained(){
        validateLogin("correctusers", "correctPassword", "Incorrect credentials");
    }

    @Test
    void failedLoginUsernameOneShort(){
        validateLogin("correctuse", "correctPassword", "Incorrect credentials");
    }




    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Response result = (Response) propertyChangeEvent.getNewValue();
        loginResult.setValue(result.getMessage());
    }


    private void validateLogin(String username, String pw, String expectedResult) {
        //arrange
        this.loginResult = new SimpleStringProperty();
        StringProperty username1 = new SimpleStringProperty();
        StringProperty password = new SimpleStringProperty();


        username1.bindBidirectional(loginViewModel.userNameProperty());
        password.bindBidirectional(loginViewModel.passwordProperty());

        //act
        username1.setValue(username);
        password.setValue(pw);
        loginViewModel.validateLogin();

        // assert
        assertEquals(expectedResult, loginResult.getValue());
    }


}