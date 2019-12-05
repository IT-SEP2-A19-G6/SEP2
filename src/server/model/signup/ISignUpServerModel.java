package server.model.signup;

import shared.Response;
import shared.clients.User;

public interface ISignUpServerModel {
    Response requestSignUp(User newUser);
}
