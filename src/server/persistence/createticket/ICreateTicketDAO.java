package server.persistence.createticket;

import server.exceptions.DataConnectionException;
import shared.Branch;
import shared.Ticket;

import java.util.ArrayList;

public interface ICreateTicketDAO {

    String addTicket (Ticket createTicket) throws DataConnectionException;

    ArrayList<Branch> getBranches() throws DataConnectionException;
}
