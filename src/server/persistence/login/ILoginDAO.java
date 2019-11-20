package server.persistence.login;

import shared.clients.Client;
import shared.exceptions.LoginDisabledException;
import shared.exceptions.IncorrectCredentialsException;

public interface ILoginDAO {
    Client loginReq(String username, String pw) throws IncorrectCredentialsException, LoginDisabledException; //TODO maybe edit arguments
}
