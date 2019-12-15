package server.model.updateticket;

import server.exceptions.DataConnectionException;
import server.persistence.updateticket.IUpdateTicketDAO;
import shared.Ticket;

public class UpdateTicketServerModel implements IUpdateTicketServerModel {


    private IUpdateTicketDAO updateTicketDAO;

    public UpdateTicketServerModel(IUpdateTicketDAO updateTicketDAO) {
        this.updateTicketDAO = updateTicketDAO;
    }



    @Override
    public void updateTicket(Ticket ticket) {
        try {
            updateTicketDAO.updateTicket(ticket);
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
    }
}
