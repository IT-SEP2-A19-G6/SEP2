package server.persistence.updateticket;

import server.exceptions.DataConnectionException;
import shared.Ticket;

public interface IUpdateTicketDAO {
    void updateTicket(Ticket ticket) throws DataConnectionException;
}
