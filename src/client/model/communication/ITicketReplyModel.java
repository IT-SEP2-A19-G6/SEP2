package client.model.communication;

import shared.IPropertyChangeSubject;
import shared.TicketReply;

public interface ITicketReplyModel extends IPropertyChangeSubject {
    void getReplies(int ticketId);
    void addReply(TicketReply reply);
    void submitReply(String messageArea);
}
