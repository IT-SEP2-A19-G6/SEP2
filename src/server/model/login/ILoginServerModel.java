package server.model.login;

import shared.Response;
import shared.clients.User;

public interface ILoginServerModel{
    Response validateLogin(User user);
}
