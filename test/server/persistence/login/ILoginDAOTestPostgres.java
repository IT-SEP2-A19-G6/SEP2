package server.persistence.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.DataFactory;
import shared.clients.User;
import shared.exceptions.DataConnectionException;
import shared.exceptions.IncorrectCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

class ILoginDAOTestPostgres {
    private ILoginDAO loginDAO;

    @BeforeEach
    void setUp() {
        DataFactory dataFactory = new DataFactory();
        loginDAO = dataFactory.getLoginDOA();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validateLoginCorrectCredentials() {
        User userCorrectCredentials = new User("user1", "user");
        String responseFromDOA = null;
        try {
            responseFromDOA = loginDAO.validateLogin(userCorrectCredentials);
        } catch (IncorrectCredentialsException| DataConnectionException e) {
            fail("Exception thrown: " + e);
        }
        assertEquals("user login accepted", responseFromDOA);
    }

    @Test
    void validateLoginWrongPassword(){
        User userWrongPassword = new User("user1", "wrongPassword");
        String responseFromDOA = null;
        try {
            responseFromDOA = loginDAO.validateLogin(userWrongPassword);
        } catch (IncorrectCredentialsException | DataConnectionException e) {
            assertTrue(e instanceof IncorrectCredentialsException);
            return;
        }
        fail("No exception thrown");
    }

    @Test
    void validateLoginWrongUsername(){
        User userWrongUsername= new User("WrongUsername", "user");
        String responseFromDOA = null;
        try {
            responseFromDOA = loginDAO.validateLogin(userWrongUsername);
        } catch (IncorrectCredentialsException| DataConnectionException e) {
            assertTrue(e instanceof IncorrectCredentialsException);
            return;
        }
        fail("No exception thrown");
    }


}