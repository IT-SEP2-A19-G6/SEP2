package server.persistence.login;

import shared.exceptions.ClientDisabledException;
import shared.exceptions.IncorrectCredentialsException;

public interface ILoginDAO {
    Client loginReq(String username, String pw) throws IncorrectCredentialsException, ClientDisabledException; //TODO add return type and maybe edit arguments
}
