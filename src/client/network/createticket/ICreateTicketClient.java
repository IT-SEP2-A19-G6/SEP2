package client.network.createticket;

import shared.IPropertyChangeSubject;
import shared.Ticket;

public interface ICreateTicketClient extends IPropertyChangeSubject {
    void submitTicket(Ticket ticket);

    void getBranches();
}
