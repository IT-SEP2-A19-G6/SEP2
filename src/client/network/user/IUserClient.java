package client.network.user;

import shared.IPropertyChangeSubject;

public interface IUserClient extends IPropertyChangeSubject {
    void requestTicketList(String username);
}
