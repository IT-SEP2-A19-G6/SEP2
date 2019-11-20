package Server.persistence.login;

import Shared.exceptions.ClientDisabledException;
import Shared.exceptions.IncorrectCredentialsException;

public interface ILoginDAO {
    Client loginReq(String username, String pw) throws IncorrectCredentialsException, ClientDisabledException; //TODO add return type and maybe edit arguments
}
