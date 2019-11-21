package server.model.login;

import shared.IPropertyChangeSubject;
import shared.Response;
import shared.clients.User;

public interface LoginServerModel extends IPropertyChangeSubject {
    Response validateLogin(User user);
}
