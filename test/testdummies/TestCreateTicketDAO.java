package testdummies;

import server.exceptions.DataConnectionException;
import server.persistence.createticket.ICreateTicketDAO;
import shared.Branch;
import shared.Ticket;

import java.util.ArrayList;

public class TestCreateTicketDAO implements ICreateTicketDAO {
    private ArrayList<Ticket> ticketsToCheckFrom;
    private ArrayList<Branch> branchesToCheckFrom;
    private int[] clientId = {1, 2};
    private int ticketId;

    public TestCreateTicketDAO(){
        ticketsToCheckFrom = new ArrayList<>();
        ticketId = 1;
        branchesToCheckFrom = new ArrayList<>();
        branchesToCheckFrom.add(new Branch(1, "IT"));
        branchesToCheckFrom.add(new Branch(2, "Facility Management"));
    }

    @Override
    public String addTicket(Ticket createTicket) throws DataConnectionException {
        Ticket ticketToAdd = new Ticket(ticketId, "createdDate " + ticketId, "username " + ticketId, createTicket.getSubject(), createTicket.getDescription(), createTicket.getLocation(), "OPEN", "branch " + ticketId, "assignee " + ticketId);
        for (int id : clientId){
            if (createTicket.getClientId() == id){
                for (Branch branch : branchesToCheckFrom){
                    if (createTicket.getBranchId() == branch.getId()) {
                        ticketsToCheckFrom.add(ticketToAdd);
                        if (ticketsToCheckFrom.get(ticketsToCheckFrom.size() - 1).getId() == ticketId){
                            ticketId++;
                            return "OK";
                        } else {
                            ticketsToCheckFrom.remove(ticketToAdd);
                        }
                    }
                }
            }
        }
        return "Error ticket not saved.";
    }

    @Override
    public ArrayList<Branch> getBranches() throws DataConnectionException {
        return branchesToCheckFrom;
    }
}
