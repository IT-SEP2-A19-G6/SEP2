package client.network.ticketList;

import shared.IPropertyChangeSubject;

public interface ITicketListClient extends IPropertyChangeSubject {
    void requestTicketList(String username);
}
