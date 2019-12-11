package client.model.communication;

import shared.TicketReply;

public interface ITicketReplyModel {
    void getReplies(int ticketId);
    void addReply(TicketReply reply);
}
