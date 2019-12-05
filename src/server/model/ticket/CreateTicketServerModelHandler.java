package server.model.ticket;

import client.network.ticket.ICreateTicketClient;
import server.exceptions.DataConnectionException;
import server.exceptions.IncorrectCredentialsException;
import server.persistence.createTicket.ICreateTicketDAO;
import server.persistence.login.ILoginDAO;
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
        System.out.println("TICKET HERE");

            String createTicketMessage;
            try {
                createTicketMessage = createTicketDAO.addTicket(ticket);//make method in loginDAO in JDBC
            } catch (DataConnectionException e) {
                createTicketMessage = e.getMessage();
            }

            return new Response(ticket.getUsername(), createTicketMessage);//create method to get username in Ticket
        }
    }