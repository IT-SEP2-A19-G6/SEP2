package Server.persistence.login;

import Shared.exceptions.IncorrectCredentialsException;

public interface ILoginDAO {
    Client loginReq(String username, String pw) throws IncorrectCredentialsException; //TODO add return type and maybe edit arguments
}
