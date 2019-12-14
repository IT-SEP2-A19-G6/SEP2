package client.model.ticketlist;

import shared.IPropertyChangeSubject;
import shared.Ticket;
import shared.TicketListExchange;

public interface ITicketListModel extends IPropertyChangeSubject {
    void requestTicketList(TicketListExchange exchange);

    void setTicketStatus(Ticket ticket);

}
