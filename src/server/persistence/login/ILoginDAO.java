package server.persistence.login;

import shared.clients.User;
import server.persistence.exceptions.DataConnectionException;
import server.persistence.exceptions.IncorrectCredentialsException;

public interface ILoginDAO {
    String validateLogin(User user) throws IncorrectCredentialsException, DataConnectionException; //TODO maybe edit arguments
}
