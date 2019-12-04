package server.model.ticket;

import server.exceptions.DataConnectionException;
import server.exceptions.IncorrectCredentialsException;
import server.persistence.login.ILoginDAO;
import shared.Response;
import shared.Ticket;
import shared.clients.User;

public class CreateTicketServerModelHandler implements ICreateTicketServerModel {
    //private Database database;
    // private ILoginDAO loginDAO;
    public CreateTicketServerModelHandler(/*Database database*/) {
        // this.database = database
    }
    @Override
    public Response sendTicket(Ticket ticket) {

            String createTicketMessage;
            try {
                createTicketMessage = loginDAO.createTicket(ticket);//make method in loginDAO in JDBC
            } catch (IncorrectCredentialsException | DataConnectionException e) {
                createTicketMessage = e.getMessage();
            }
            return new Response(ticket.getUser(), createTicketMessage);//create method to get username in Ticket
        }
    }
}
