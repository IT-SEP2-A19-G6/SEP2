package client.network.signup;

import shared.IPropertyChangeSubject;
import shared.clients.User;

public interface ISignUpClient extends IPropertyChangeSubject {
    void requestSignUp(User user);
}
