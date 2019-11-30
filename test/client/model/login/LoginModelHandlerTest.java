package client.model.login;

import client.network.login.ILoginClient;
import client.network.login.LoginClientHandler;
import client.network.socket.IClientSocketHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Request;
import shared.Response;
import shared.clients.Client;
import shared.clients.User;
import testdummies.TestSocketHandler;
import java.beans.PropertyChangeEvent;
import static org.junit.jupiter.api.Assertions.*;

class LoginModelHandlerTest {
    private ILoginModel loginModel;
    private Client user;
    private String responseToCheck;
    private String userToCheck;

    @BeforeEach
    void setUp() {
        IClientSocketHandler socketHandler = new TestSocketHandler();
        ILoginClient loginClient = new LoginClientHandler(socketHandler);
        loginModel = new LoginModelHandler(loginClient);
        loginClient.addPropertyChangeListener(Request.TYPE.LOGIN_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Response loginResponse = (Response) propertyChangeEvent.getNewValue();
        userToCheck = loginResponse.getToUser();
        responseToCheck = loginResponse.getMessage();
    }

    @Test
    void validateCorrectCredentials(){
        user = new User("correctUser", "correctPassword");
        loginModel.validateLogin(user.getUsername(), user.getPassword());
        assertEquals(user.getUsername(), userToCheck);
        assertEquals("User login accepted", responseToCheck);
    }

    @Test
    void validateLoginWrongPassword(){
        user = new User("correctUser", "wrongPassword");
        loginModel.validateLogin(user.getUsername(), user.getPassword());
        assertEquals(user.getUsername(), userToCheck);
        assertEquals("Incorrect credentials", responseToCheck);
    }

    @Test
    void validateLoginWrongUsername(){
        user = new User("wrongUser", "correctPassword");
        loginModel.validateLogin(user.getUsername(), user.getPassword());
        assertEquals(user.getUsername(), userToCheck);
        assertEquals("Incorrect credentials", responseToCheck);
    }

}