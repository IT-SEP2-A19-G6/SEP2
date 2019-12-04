package server.persistence.createTicket;

import server.exceptions.DataConnectionException;
import shared.Ticket;

public interface ICreateTicketDAO {

    void addTicket (Ticket createTicket) throws DataConnectionException;
}
