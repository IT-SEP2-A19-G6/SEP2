package server.persistence.user;

import server.exceptions.DataConnectionException;
import shared.Ticket;

import java.util.ArrayList;

public interface IUserDAO {
    ArrayList<Ticket> getClientTickets(String username) throws DataConnectionException;
}
