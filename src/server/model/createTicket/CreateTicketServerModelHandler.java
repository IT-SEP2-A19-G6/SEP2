package server.model.createTicket;

import server.persistence.createTicket.ICreateTicketDAO;
import shared.Ticket;
import shared.Response;

public class CreateTicketServerModelHandler implements ICreateTicketServerModel {

    private ICreateTicketDAO createTicketDAO;

    public CreateTicketServerModelHandler(ICreateTicketDAO createTicketDAO) {
        this.createTicketDAO = createTicketDAO;
    }

    @Override
    public Response addTicket(Ticket createTicket) {
        return null;
    }
}
