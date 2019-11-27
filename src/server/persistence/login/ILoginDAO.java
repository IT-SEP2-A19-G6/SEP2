package server.persistence.login;

import shared.clients.User;
import shared.exceptions.DataConnectionException;
import shared.exceptions.IncorrectCredentialsException;

public interface ILoginDAO {
    String validateLogin(User user) throws IncorrectCredentialsException, DataConnectionException; //TODO maybe edit arguments
}
