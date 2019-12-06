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
    public ArrayList<Ticket> requestOwnTicketList(String username) {
        ArrayList<Ticket> tickets = null;
        try {
            tickets = ticketListDAO.getOwnTicketList(username);
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public ArrayList<Ticket> requestAssignedTicketList(String username) {
        ArrayList<Ticket> tickets = null;
        try {
            tickets = ticketListDAO.getAssignedTicketList(username);
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
