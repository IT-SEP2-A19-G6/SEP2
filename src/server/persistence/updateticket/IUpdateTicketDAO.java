package server.persistence.updateticket;

import server.exceptions.DataConnectionException;
import shared.Ticket;
import shared.clients.BranchMember;

import java.util.ArrayList;

public interface IUpdateTicketDAO {
    void updateTicket(Ticket ticket) throws DataConnectionException;

    ArrayList<BranchMember> getBranchMembersByBranchName(String branchName) throws DataConnectionException;
    ArrayList<BranchMember> getAllBranchMembers() throws DataConnectionException;

    void setAssignee(Ticket ticket) throws DataConnectionException;
}
