package server.model.ticketlist;

import shared.Ticket;
import shared.TicketListExchange;

import java.util.ArrayList;

public interface ITicketListServerModel {
    TicketListExchange requestTicketList(TicketListExchange exchange);
}
