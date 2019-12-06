package server.model.ticketList;

import shared.Ticket;

import java.util.ArrayList;

public interface ITicketListServerModel {
    ArrayList<Ticket> requestClientTickets(String username);
}
