package server.model.communication;


import server.persistence.communication.ITicketReplyDAO;
import shared.TicketReply;

import java.util.ArrayList;

public class TicketReplyServerModelHandler implements ITicketReplyServerModel {
    private ITicketReplyDAO ticketReplyDAO;

    public TicketReplyServerModelHandler(ITicketReplyDAO ticketReplyDAO) {
        this.ticketReplyDAO = ticketReplyDAO;
    }

    @Override
    public ArrayList<TicketReply> getReplies(int ticketId) {
        return ticketReplyDAO.getReplies(ticketId);
    }

    @Override
    public void addReply(TicketReply reply) {
        ticketReplyDAO.addReply(reply);
    }
}
