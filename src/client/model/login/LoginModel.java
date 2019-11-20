package client.model.login;

public interface LoginModel {
    void validateLogin(String username, String password);
    void createUser(String username, String pw, String pwAgain)
}