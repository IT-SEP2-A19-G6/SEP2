package client.network.communication;

import client.network.socket.IClientSocketHandler;
import shared.TicketReply;

public class TicketReplyClient implements ITicketReplyClient {
    private IClientSocketHandler clientSocketHandler;

    public TicketReplyClient(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
    }

    @Override
    public void getReplies(int ticketId) {
        
    }

    @Override
    public void addReply(TicketReply reply) {

    }
}
