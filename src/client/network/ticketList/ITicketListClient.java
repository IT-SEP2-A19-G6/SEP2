package client.network.ticketList;

import shared.IPropertyChangeSubject;

public interface ITicketListClient extends IPropertyChangeSubject {
    void requestOwnTicketList(String username);
    void requestAssignedTicketList(String username);
    void requestBranchTicketList(String username);
}
