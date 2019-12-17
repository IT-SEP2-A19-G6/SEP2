package server.model.communication;


import server.exceptions.DataConnectionException;
import server.persistence.communication.ITicketReplyDAO;
import shared.TicketReply;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketReplyServerModelHandler implements ITicketReplyServerModel {
    private final ITicketReplyDAO ticketReplyDAO;

    public TicketReplyServerModelHandler(ITicketReplyDAO ticketReplyDAO) {
        this.ticketReplyDAO = ticketReplyDAO;
    }

    @Override
    public ArrayList<TicketReply> getReplies(int ticketId) {
        ArrayList<TicketReply> replies = new ArrayList<>();
        try {
            replies.addAll(ticketReplyDAO.getReplies(ticketId));
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return replies;
    }

    @Override
    public void addReply(TicketReply reply) {
        try {
            ticketReplyDAO.addReply(reply);
        } catch (DataConnectionException | SQLException e) {
            e.printStackTrace();
        }
    }
}
