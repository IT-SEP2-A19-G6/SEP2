package server.persistence.ticketlist;

import server.exceptions.DataConnectionException;
import shared.Ticket;

import java.util.ArrayList;

public interface ITicketListDAO {
    ArrayList<Ticket> getOwnTicketList(String username) throws DataConnectionException;
    ArrayList<Ticket> getAssignedTicketList(String username) throws DataConnectionException;
}
