package client.network.login;

import shared.IPropertyChangeSubject;
import shared.clients.Client;

public interface ILoginClient extends IPropertyChangeSubject {
    void validateLogin(Client client);
}
