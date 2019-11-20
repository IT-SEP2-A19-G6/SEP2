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
    void loginReqUserExists() {
        String username = "user1";
        String password = "user";
        User dbUser = null;
        try {
            dbUser = (User) loginDAO.loginReq(username, password);
        } catch (IncorrectCredentialsException | LoginDisabledException e) {
            fail("Exception thrown: " + e);
        }
        assert dbUser != null;
        assertEquals(username, dbUser.getUsername());
        assertEquals(password, dbUser.getPassword());
    }

    @Test
    void loginReqWrongPass(){
        String username = "user1";
        String password = "wrongPassword";
        User dbUser = null;
        try {
            dbUser = (User) loginDAO.loginReq(username, password);
        } catch (IncorrectCredentialsException | LoginDisabledException e) {
            assertTrue(e instanceof IncorrectCredentialsException);
            return;
        }
        fail("No exception thrown");
    }

    @Test
    void loginReqWrongUsername(){
        String username = "wrongUsername";
        String password = "user";
        User dbUser = null;
        try {
            dbUser = (User) loginDAO.loginReq(username, password);
        } catch (IncorrectCredentialsException | LoginDisabledException e) {
            assertTrue(e instanceof IncorrectCredentialsException);
            return;
        }
        fail("No exception thrown");
    }

    @Test
    void loginReqUserDisabled(){
        String username = "user4";
        String password = "user";
        User dbUser = null;
        try {
            dbUser = (User) loginDAO.loginReq(username, password);
        } catch (IncorrectCredentialsException | LoginDisabledException e) {
            assertTrue(e instanceof LoginDisabledException);
            return;
        }
        fail("No exception thrown");
    }




}