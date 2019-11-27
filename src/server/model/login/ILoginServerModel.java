package server.model.login;

import shared.IPropertyChangeSubject;
import shared.Response;
import shared.clients.User;

public interface ILoginServerModel extends IPropertyChangeSubject {
    Response validateLogin(User user);
}
