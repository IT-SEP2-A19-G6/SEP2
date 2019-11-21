package server.model.login;

import shared.IPropertyChangeSubject;
import shared.clients.User;

public interface LoginServerModel extends IPropertyChangeSubject {
    void validateLogin(User user);
}
