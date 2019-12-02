package server.model.ticket;

import shared.Response;
import shared.Ticket;

public interface ITicketServerModel {
    Response sendTicket(Ticket ticket);
}
