package server.model.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.database.IDatabaseConnection;
import server.persistence.login.ILoginDAO;
import server.persistence.login.LoginDOA;
import server.testdummies.TestDatabaseConnection;
import shared.Response;
import shared.clients.User;
import static org.junit.jupiter.api.Assertions.*;


class LoginServerModelTest {
    private ILoginServerModel loginServerModel;
    private User user;

    @BeforeEach
    void setUp() {
        IDatabaseConnection dbConn = new TestDatabaseConnection();
        ILoginDAO loginDAO = new LoginDOA(dbConn);
        loginServerModel = new LoginServerModelHandler(loginDAO);
    }


    @Test
    void validateCorrectCredentials(){
        user = new User("correctUser", "correctPassword");
        Response response = loginServerModel.validateLogin(user);
        assertEquals(user.getUsername(), response.getToUser());
        assertEquals("user login accepted", response.getMessage());
    }


    @Test
    void validateLoginWrongPassword(){
        user = new User("correctUser", "wrongPassword");
        Response response = loginServerModel.validateLogin(user);
        assertEquals(user.getUsername(), response.getToUser());
        assertEquals("Incorrect credentials", response.getMessage());
    }

    @Test
    void validateLoginWrongUsername(){
        user = new User("wrongUser", "correctPassword");
        Response response = loginServerModel.validateLogin(user);
        assertEquals(user.getUsername(), response.getToUser());
        assertEquals("Incorrect credentials", response.getMessage());
    }



}