package server.model.login;

import server.persistence.login.ILoginDAO;
import shared.Response;
import shared.clients.User;
import server.exceptions.DataConnectionException;
import server.exceptions.IncorrectCredentialsException;


public class LoginServerModelHandler implements ILoginServerModel {
    private ILoginDAO loginDAO;

    public LoginServerModelHandler(ILoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public Response validateLogin(User user){
        String loginMessage;
        try {
            loginMessage = loginDAO.validateLogin(user);
        } catch (IncorrectCredentialsException | DataConnectionException e) {
            loginMessage = e.getMessage();
        }
        return new Response(user, loginMessage);
    }


}
