package server.persistence.login;

import shared.clients.User;
import server.exceptions.DataConnectionException;
import server.exceptions.IncorrectCredentialsException;

public interface ILoginDAO {
    String validateLogin(User user) throws IncorrectCredentialsException, DataConnectionException; //TODO maybe edit arguments
}
