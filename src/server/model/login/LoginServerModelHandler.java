package server.model.login;

import server.exceptions.DataConnectionException;
import server.persistence.login.ILoginDAO;
import shared.Response;
import shared.clients.Client;
import shared.clients.User;


public class LoginServerModelHandler implements ILoginServerModel {
    private ILoginDAO loginDAO;

    public LoginServerModelHandler(ILoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public Response validateLogin(User user){
        try {
            String loginMessage;
            Client client = null;
            client = loginDAO.validateLogin(user);
            loginMessage = client != null ? "User login accepted" : "Incorrect credentials";
            return new Response(client != null ? client : null, loginMessage);
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return null;
    }


}
