package server.model.createticket;


import shared.Branch;
import shared.Response;
import shared.Ticket;

import java.util.ArrayList;

public interface ICreateTicketServerModel {

    Response sendTicket(Ticket ticket);

    ArrayList<Branch> getBranches();

    void updateTicket(Ticket ticket);
}
