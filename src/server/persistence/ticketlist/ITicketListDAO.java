package server.persistence.ticketlist;

import server.exceptions.DataConnectionException;
import shared.Ticket;

import java.util.ArrayList;

public interface ITicketListDAO {
    ArrayList<Ticket> getClientTickets(String username) throws DataConnectionException;
}
