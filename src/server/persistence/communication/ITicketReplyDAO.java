package server.persistence.communication;

import shared.TicketReply;

import java.util.ArrayList;

public interface ITicketReplyDAO {
    ArrayList<TicketReply> getReplies(int ticketId);
    void addReply(TicketReply reply);

}
