package client.model.login;

import shared.IPropertyChangeSubject;
import shared.Response;

public interface ILoginModel extends IPropertyChangeSubject {
    void validateLogin(String username, String password);
    void loginResult(Response loginResponse);
}