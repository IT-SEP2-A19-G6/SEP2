package server.persistence.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.DataFactory;
import shared.clients.User;
import shared.exceptions.IncorrectCredentialsException;
import shared.exceptions.LoginDisabledException;

import static org.junit.jupiter.api.Assertions.*;

class ILoginDAOTest {
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
        } catch (IncorrectCredentialsException | LoginDisabledException e) {
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
        } catch (IncorrectCredentialsException | LoginDisabledException e) {
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
        } catch (IncorrectCredentialsException | LoginDisabledException e) {
            assertTrue(e instanceof IncorrectCredentialsException);
            return;
        }
        fail("No exception thrown");
    }

    @Test
    void validateLogingDisabledUser(){
        User userDisabled= new User("user4", "user");
        String responseFromDOA = null;
        try {
            responseFromDOA = loginDAO.validateLogin(userDisabled);
        } catch (IncorrectCredentialsException | LoginDisabledException e) {
            assertTrue(e instanceof LoginDisabledException);
            return;
        }
        fail("No exception thrown");
    }

}