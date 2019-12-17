package server.model.updateticket;

import server.exceptions.DataConnectionException;
import server.persistence.updateticket.IUpdateTicketDAO;
import shared.Ticket;
import shared.clients.BranchMember;

import java.util.ArrayList;

public class UpdateTicketServerModel implements IUpdateTicketServerModel {


    private final IUpdateTicketDAO updateTicketDAO;

    public UpdateTicketServerModel(IUpdateTicketDAO updateTicketDAO) {
        this.updateTicketDAO = updateTicketDAO;
    }

    @Override
    public void updateTicket(Ticket ticket) {
        updateTicketDAO.updateTicket(ticket);
    }

    @Override
    public ArrayList<BranchMember> getBranchMembersByName(String branchName) {
        ArrayList<BranchMember> branchMembers = new ArrayList<>();
        try {
            branchMembers = updateTicketDAO.getBranchMembersByBranchName(branchName);
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return branchMembers;
    }

    @Override
    public void setAssignee(Ticket ticket) {
        try {
            updateTicketDAO.setAssignee(ticket);
        } catch (DataConnectionException e) {
        e.printStackTrace();
    }

    }
}
