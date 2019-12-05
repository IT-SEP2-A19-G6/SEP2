package server.persistence.signup;

import shared.Response;
import shared.clients.User;

public interface ISignUpDAO {
    Response requestSignUp(User newUser);
}
