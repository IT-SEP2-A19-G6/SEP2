package server.persistence.ticketlist;

import server.exceptions.DataConnectionException;
import shared.Ticket;

import java.util.ArrayList;

public interface ITicketListDAO {
    ArrayList<Ticket> getOwnTicketList(String userToFind) throws DataConnectionException;
    ArrayList<Ticket> getAssignedTicketList(String userToFind) throws DataConnectionException;
}
