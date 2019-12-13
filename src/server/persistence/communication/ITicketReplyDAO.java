package server.persistence.communication;

import server.exceptions.DataConnectionException;
import shared.TicketReply;

import java.util.ArrayList;

public interface ITicketReplyDAO {
    ArrayList<TicketReply> getReplies(int ticketId);
    String addReply(TicketReply reply) throws DataConnectionException;

}
