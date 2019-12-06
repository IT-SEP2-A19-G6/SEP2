package server.model.ticket;

import server.exceptions.DataConnectionException;
import server.persistence.createticket.ICreateTicketDAO;
import shared.Response;
import shared.Ticket;
import shared.clients.User;

public class CreateTicketServerModelHandler implements ICreateTicketServerModel {
    private ICreateTicketDAO createTicketDAO;

    public CreateTicketServerModelHandler(ICreateTicketDAO createTicketDAO) {
        this.createTicketDAO = createTicketDAO;
    }

    @Override
    public Response sendTicket(Ticket ticket) {

            String createTicketMessage;
            try {
                createTicketMessage = createTicketDAO.addTicket(ticket);//make method in loginDAO in JDBC
            } catch (DataConnectionException e) {
                createTicketMessage = e.getMessage();
            }

            return new Response(new User(ticket.getUsername(), ""), createTicketMessage);//create method to get username in Ticket
        }
    }