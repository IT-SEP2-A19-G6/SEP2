package client.network.communication;

import shared.IPropertyChangeSubject;
import shared.TicketReply;

public interface ITicketReplyClient extends IPropertyChangeSubject {
    void getReplies(int ticketId);
    void addReply(TicketReply reply);
}
