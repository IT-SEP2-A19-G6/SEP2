package client.model.ticketlist;

import shared.IPropertyChangeSubject;

public interface ITicketListModel extends IPropertyChangeSubject {
    void requestOwnTicketList(String username);
    void requestAssignedTicketList(String username);
    void requestBranchTicketList(String username);

//    void addOwnTicket(); //TODO delete or use
//    void addAssignedTicket(); //TODO delete or use

}
