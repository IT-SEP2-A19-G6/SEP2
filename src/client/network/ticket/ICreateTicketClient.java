package client.network.ticket;

import shared.IPropertyChangeSubject;
import shared.Ticket;

public interface ICreateTicketClient extends IPropertyChangeSubject {
    Ticket createTicket(Ticket ticket);
}
