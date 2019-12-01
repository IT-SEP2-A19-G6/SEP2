package server.persistence.login;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.database.IDatabaseConnection;
import testdummies.TestDatabaseConnection;
import shared.clients.User;
import server.exceptions.DataConnectionException;
import server.exceptions.IncorrectCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

class ILoginDAOTest {
    IDatabaseConnection dbConn;
    private ILoginDAO loginDAO;

    @BeforeEach
    void setUp() {
        dbConn = new TestDatabaseConnection();
        loginDAO = new LoginDOA(dbConn);
    }

    @Test
    void getSchemaName(){
        assertEquals("sep2", dbConn.getSchemaName());
    }

    @Test
    void getClientTableName(){
        assertEquals("account_client", dbConn.getClientTableName());
    }

    @Test
    void validateLoginCorrectCredentials() {
        User userCorrectCredentials = new User("correctUser", "correctPassword");
        String responseFromDOA = null;
        try {
            responseFromDOA = loginDAO.validateLogin(userCorrectCredentials);
        } catch (IncorrectCredentialsException | DataConnectionException e) {
            fail("Exception thrown: " + e);
        }
        assertEquals("User login accepted", responseFromDOA);
    }

    @Test
    void validateLoginWrongPassword(){
        User userWrongPassword = new User("correctUser", "wrongPassword");
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
        User userWrongUsername= new User("WrongUsername", "correctPassword");
        String responseFromDOA = null;
        try {
            responseFromDOA = loginDAO.validateLogin(userWrongUsername);
        } catch (IncorrectCredentialsException | DataConnectionException e) {
            assertTrue(e instanceof IncorrectCredentialsException);
            return;
        }
        fail("No exception thrown");
    }



}