package server.model.login;

import shared.IPropertyChangeSubject;
import shared.clients.User;
import shared.exceptions.IncorrectCredentialsException;
import shared.exceptions.LoginDisabledException;

public interface LoginServerModel extends IPropertyChangeSubject {
    void validateLogin(User user);
}
