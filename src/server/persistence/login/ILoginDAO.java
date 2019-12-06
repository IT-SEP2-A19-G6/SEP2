package server.persistence.login;

import server.exceptions.DataConnectionException;
import shared.clients.Client;
import shared.clients.User;

public interface ILoginDAO {
    Client validateLogin(User user) throws DataConnectionException;
}
