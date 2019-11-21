package server.persistence.login;

import shared.clients.Client;
import shared.clients.User;
import shared.exceptions.LoginDisabledException;
import shared.exceptions.IncorrectCredentialsException;

public interface ILoginDAO {
    Client validateLogin(User user) throws IncorrectCredentialsException, LoginDisabledException; //TODO maybe edit arguments
}
