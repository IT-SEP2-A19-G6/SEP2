package server.model.ticketlist;

import shared.TicketListExchange;

public interface ITicketListServerModel {
    TicketListExchange requestTicketList(TicketListExchange exchange);
}
