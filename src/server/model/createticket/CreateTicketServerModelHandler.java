package server.model.createticket;

import server.exceptions.DataConnectionException;
import server.persistence.createticket.ICreateTicketDAO;
import shared.Branch;
import shared.Response;
import shared.Ticket;

import java.util.ArrayList;

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

        return new Response(createTicketMessage);//create method to get username in Ticket
    }

    @Override
    public ArrayList<Branch> getBranches() {
        ArrayList<Branch> branches;

        try {
            branches = createTicketDAO.getBranches();
        } catch (DataConnectionException e) {
            branches = new ArrayList<>();
            branches.add(new Branch(0, "No categories found"));
        }
        return branches;
    }

}