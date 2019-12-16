package server.model.updateticket;

import shared.Ticket;
import shared.clients.BranchMember;

import java.util.ArrayList;

public interface IUpdateTicketServerModel {

    void updateTicket(Ticket ticket);

    ArrayList<BranchMember> getBranchMembersByName(String branchName);

    void setAssignee(Ticket ticket);
}
