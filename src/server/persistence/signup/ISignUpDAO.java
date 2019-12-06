package server.persistence.signup;

import server.exceptions.DataConnectionException;
import shared.Response;
import shared.clients.User;

public interface ISignUpDAO {
    String requestSignUp(User newUser) throws DataConnectionException;
}
