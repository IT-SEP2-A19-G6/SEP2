package client.model.login;

import shared.IPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface ILoginModel extends IPropertyChangeSubject {
    void validateLogin(String username, String password);
}