package server.model.createticket;


import shared.Response;
import shared.Ticket;

public interface ICreateTicketServerModel {

    Response sendTicket(Ticket ticket);
}
