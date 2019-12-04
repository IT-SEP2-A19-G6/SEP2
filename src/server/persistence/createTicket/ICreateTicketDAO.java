package server.persistence.createTicket;

import server.exceptions.DataConnectionException;
import shared.Ticket;

import java.sql.SQLException;

public interface ICreateTicketDAO {

    String addTicket (Ticket createTicket) throws DataConnectionException;
}
