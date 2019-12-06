package server.model.ticketlist;

import server.exceptions.DataConnectionException;
import server.persistence.ticketlist.ITicketListDAO;
import shared.Ticket;

import java.util.ArrayList;

public class TicketListServerModelHandler implements ITicketListServerModel {
    private ITicketListDAO ticketListDAO;

    public TicketListServerModelHandler(ITicketListDAO userDAO) {
        this.ticketListDAO = userDAO;
    }

    @Override
    public ArrayList<Ticket> requestClientTickets(String username) {
        ArrayList<Ticket> tickets = null;
        try {
            tickets = ticketListDAO.getClientTickets(username);
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
