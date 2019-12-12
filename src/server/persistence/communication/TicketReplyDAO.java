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
        return null;
    }

    @Override
    public void addReply(TicketReply reply) {

    }
}
