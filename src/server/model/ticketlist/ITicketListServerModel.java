package server.model.ticketlist;

import shared.Ticket;

import java.util.ArrayList;

public interface ITicketListServerModel {
    ArrayList<Ticket> requestClientTickets(String username);
}
