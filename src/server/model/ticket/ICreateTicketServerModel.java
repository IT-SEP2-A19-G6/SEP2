package server.model.ticket;


import shared.Response;
import shared.Ticket;

public interface ICreateTicketServerModel {
    Response sendTicket(Ticket ticket);
}
