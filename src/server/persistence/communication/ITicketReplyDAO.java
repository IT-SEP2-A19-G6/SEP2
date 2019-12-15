package server.persistence.communication;

import server.exceptions.DataConnectionException;
import shared.TicketReply;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ITicketReplyDAO {
    ArrayList<TicketReply> getReplies(int ticketId) throws DataConnectionException;
    void addReply(TicketReply reply) throws DataConnectionException, SQLException;

}
