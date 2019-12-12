package client.network.communication;

import shared.IPropertyChangeSubject;
import shared.TicketReply;

import java.util.ArrayList;

public interface ITicketReplyClient extends IPropertyChangeSubject {
    void getReplies(int ticketId);
    void addReply(TicketReply reply);
}
