package client.model.communication;

import client.network.communication.ITicketReplyClient;
import shared.TicketReply;

public class TicketReplyModelHandler implements ITicketReplyModel {
    ITicketReplyClient ticketReplyClient;


    public TicketReplyModelHandler(ITicketReplyClient ticketReplyClient) {
        this.ticketReplyClient = ticketReplyClient;
    }

    @Override
    public void getReplies(int ticketId) {

    }

    @Override
    public void addReply(TicketReply reply) {

    }
}
