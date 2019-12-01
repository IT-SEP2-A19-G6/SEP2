package client.model.login;

import shared.IPropertyChangeSubject;


public interface ILoginModel extends IPropertyChangeSubject {
    void validateLogin(String username, String password);

}