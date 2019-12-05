package server.model.user;

import shared.Ticket;

import java.util.ArrayList;

public interface IUserServerModel {
    ArrayList<Ticket> requestClientTickets(String username);
}
