package server.model.ticketlist;

import shared.Ticket;

import java.util.ArrayList;

public interface ITicketListServerModel {
    ArrayList<Ticket> requestOwnTicketList(String username);
    ArrayList<Ticket> requestAssignedTicketList(String username);
    ArrayList<Ticket> requestBranchTicketList(String username);
}
