package server.persistence.createticket;

import server.exceptions.DataConnectionException;
import shared.Ticket;

public interface ICreateTicketDAO {

    String addTicket (Ticket createTicket) throws DataConnectionException;
}
