package client.network.communication;

import shared.TicketReply;

import java.util.ArrayList;

public interface ITicketReplyClient {
    void getReplies(int ticketId);
    void addReply(TicketReply reply);
}
