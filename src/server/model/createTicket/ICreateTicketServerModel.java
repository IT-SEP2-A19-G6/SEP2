package server.model.createTicket;

import shared.Ticket;
import shared.Response;

public interface ICreateTicketServerModel {
    Response addTicket(Ticket createTicket);
}
