package server.persistence.ticketlist;

import server.exceptions.DataConnectionException;
import shared.Ticket;

import java.util.ArrayList;

public interface ITicketListDAO {
    ArrayList<Ticket> getOwnTicketList(int searchId) throws DataConnectionException;
    ArrayList<Ticket> getAssignedTicketList(int searchId) throws DataConnectionException;
    ArrayList<Ticket> getBranchTicketList(int searchId) throws DataConnectionException;
}
