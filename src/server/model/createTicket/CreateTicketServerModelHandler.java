package server.model.createTicket;

import server.persistence.createTicket.ICreateTicketDAO;
import shared.CreateTicket;
import shared.Response;

public class CreateTicketServerModelHandler implements ICreateTicketServerModel {

    private ICreateTicketDAO createTicketDAO;

    public CreateTicketServerModelHandler(ICreateTicketDAO createTicketDAO) {
        this.createTicketDAO = createTicketDAO;
    }

    @Override
    public Response writeToTable(CreateTicket createTicket) {
        return null;
    }
}
