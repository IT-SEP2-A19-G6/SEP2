package server.model.user;

import server.exceptions.DataConnectionException;
import server.persistence.user.IUserDAO;
import shared.Ticket;

import java.util.ArrayList;

public class UserServerModelHandler implements IUserServerModel {
    private IUserDAO userDAO;

    public UserServerModelHandler(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ArrayList<Ticket> requestClientTickets(String username) {
        ArrayList<Ticket> tickets = null;
        try {
            tickets = userDAO.getClientTickets(username);
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
