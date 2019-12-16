package server.model.login;

import server.exceptions.DataConnectionException;
import server.persistence.login.ILoginDAO;
import shared.Response;
import shared.clients.Client;
import shared.clients.User;


public class LoginServerModelHandler implements ILoginServerModel {
    private final ILoginDAO loginDAO;

    public LoginServerModelHandler(ILoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public Response validateLogin(User user){
        try {
            String loginMessage;
            Client client;
            client = loginDAO.validateLogin(user);
            loginMessage = client != null ? "User login accepted" : "Incorrect credentials";
            return new Response(client, loginMessage);
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return null;
    }


}
