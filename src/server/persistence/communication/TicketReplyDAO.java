package server.persistence.communication;

import server.persistence.database.IDatabaseConnection;
import shared.TicketReply;

import java.util.ArrayList;

public class TicketReplyDAO implements ITicketReplyDAO {
    private IDatabaseConnection databaseConnection;

    public TicketReplyDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //TODO handle thrown exceptions in server model

    @Override
    public ArrayList<TicketReply> getReplies(int ticketId) {
        ArrayList<TicketReply> replies = new ArrayList<>();
        try {
            replies.add(new TicketReply(1, "test23", 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return replies;
    }

    @Override
    public void addReply(TicketReply reply) {
        System.out.println(reply.getMessage());

    }
}
