package client.network.ticketlist;

import shared.IPropertyChangeSubject;
import shared.TicketListExchange;

public interface ITicketListClient extends IPropertyChangeSubject {
    void requestTicketList(TicketListExchange exchange);
}
