package server.model.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.login.ILoginDAO;
import shared.Request;
import shared.Response;
import shared.clients.Client;
import shared.clients.ClientType;
import shared.clients.User;
import testdummies.TestLoginDAO;

import static org.junit.jupiter.api.Assertions.*;

class LoginServerModelHandlerTest {

    private ILoginDAO loginDAO;
    private LoginServerModelHandler loginServerModelHandler;
    private Client client;

    @BeforeEach
    void setUp() {
        loginDAO = new TestLoginDAO();
        loginServerModelHandler = new LoginServerModelHandler(loginDAO);
    }

    @Test
    void validateLoginUserOK() {
        //arrange
        client = new User("correctUser", "correctPassword");
        //act
        Response response = loginServerModelHandler.validateLogin((User) client);
        String clientToCheck =  response.getMessage();
        //assert
        assertEquals("User login accepted", response.getMessage());
    }

    @Test
    void validateLoginUserWrongUsername() {
        //arrange
        client = new User("wrongUsername", "correctPassword");
        //act
        Response response = loginServerModelHandler.validateLogin((User) client);
        String clientToCheck =  response.getMessage();
        //assert
        assertEquals("Incorrect credentials", response.getMessage());
    }

    @Test
    void validateLoginUserWrongPassword() {
        //arrange
        client = new User("correctUser", "incorrectPassword");
        //act
        Response response = loginServerModelHandler.validateLogin((User) client);
        String clientToCheck =  response.getMessage();
        //assert
        assertEquals("Incorrect credentials", response.getMessage());
    }

    @Test
    void validateLoginBranchMemberOK() {
        //arrange
        client = new User("correctBranchMember", "correctPassword");
        //act
        Response response = loginServerModelHandler.validateLogin((User) client);
        String clientToCheck =  response.getMessage();
        //assert
        assertEquals("User login accepted", response.getMessage());
    }

    @Test
    void validateLoginBranchMemberWrongName() {
        //arrange
        client = new User("incorrectBranchMember", "correctPassword");
        //act
        Response response = loginServerModelHandler.validateLogin((User) client);
        String clientToCheck =  response.getMessage();
        //assert
        assertEquals("Incorrect credentials", response.getMessage());
    }

    @Test
    void validateLoginBranchMemberWrongPassword() {
        //arrange
        client = new User("correctBranchMember", "incorrectPassword");
        //act
        Response response = loginServerModelHandler.validateLogin((User) client);
        String clientToCheck =  response.getMessage();
        //assert
        assertEquals("Incorrect credentials", response.getMessage());
    }

    @Test
    void validateLoginUserClient() {
        //arrange
        client = new User("correctUser", "correctPassword");
        //act
        Response response = loginServerModelHandler.validateLogin((User) client);
        Client clientToCheck =  response.getReceiver();
        //assert
        assertEquals(ClientType.USER, clientToCheck.getType());
    }

    @Test
    void validateLoginBranchMemberClient() {
        //arrange
        client = new User("correctBranchMember", "correctPassword");
        //act
        Response response = loginServerModelHandler.validateLogin((User) client);
        Client clientToCheck =  response.getReceiver();
        //assert
        assertEquals(ClientType.BRANCH_MEMBER, clientToCheck.getType());
    }
}