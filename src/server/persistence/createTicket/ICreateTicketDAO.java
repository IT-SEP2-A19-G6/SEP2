package server.persistence.createTicket;

import server.exceptions.DataConnectionException;
import shared.Ticket;

public interface ICreateTicketDAO {

    String addTicket (Ticket createTicket) throws DataConnectionException;
}
