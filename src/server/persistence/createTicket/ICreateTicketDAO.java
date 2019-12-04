package server.persistence.createTicket;

import server.exceptions.DataConnectionException;
import shared.CreateTicket;

public interface ICreateTicketDAO {

    void writeToTable (CreateTicket createTicket) throws DataConnectionException;
}
