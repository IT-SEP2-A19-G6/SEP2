package server.model.communication;

import shared.TicketReply;

import java.util.ArrayList;

public interface ITicketReplyServerModel {
    ArrayList<TicketReply> getReplies(int ticketId);
    void addReply(TicketReply reply);
}
