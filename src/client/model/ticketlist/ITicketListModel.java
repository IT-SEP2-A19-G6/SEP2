package client.model.ticketlist;

import shared.IPropertyChangeSubject;

public interface ITicketListModel extends IPropertyChangeSubject {
    void requestTicketList(String username);

//    void addTicket(); //TODO delete or use
}
