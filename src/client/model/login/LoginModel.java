package client.model.login;

import java.beans.PropertyChangeListener;

public interface LoginModel {
    void validateLogin(String username, String password);
    void addListener(String name, PropertyChangeListener listener);

    //    void createUser(String username, String pw, String pwAgain);
}