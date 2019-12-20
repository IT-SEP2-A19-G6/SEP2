package server.persistence.communication;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Ticket;
import shared.TicketReply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TicketReplyDAO implements ITicketReplyDAO {
    private final IDatabaseConnection databaseConnection;

    public TicketReplyDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    public ArrayList<TicketReply> getReplies(int ticketId) throws DataConnectionException {
        String sql = "SELECT Ticket_Id AS ticketId, tStamp AS timestamp, c.Username AS replier, message FROM " + databaseConnection.getReplyTableName() + " r " +
                "INNER JOIN Ticket T on r.Ticket_Id = T.Id " +
                "INNER JOIN Account_Client c on r.client_Id = c.Id " +
                "where t.id = " + ticketId +
                "ORDER BY timestamp DESC;";

        return getRepliesFromDb(sql);
    }

    @Override
    public void addReply(TicketReply reply) throws DataConnectionException, SQLException {
        String sql = "insert into " + databaseConnection.getReplyTableName() + " (Ticket_Id, client_Id, message) VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

        preparedStatement.setInt(1, reply.getTicketId());
        preparedStatement.setInt(2, reply.getClientId());
        preparedStatement.setString(3, reply.getMessage());

        databaseConnection.executeUpdate(preparedStatement);

    }

    private ArrayList<TicketReply> getRepliesFromDb(String preparedSql) throws DataConnectionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TicketReply> replies = new ArrayList<>();
        try {
            ps = databaseConnection.executePreparedQuery(preparedSql);
            rs = ps.executeQuery();
            while(rs.next()) {
                //noinspection SpellCheckingInspection
                int ticketId = rs.getInt("ticketid");
                Date createdDate = rs.getTimestamp("timestamp");
                String replier = rs.getString("replier");
                String message = rs.getString("message");

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String date = dateFormat.format(createdDate);

                replies.add(new TicketReply(message, date, replier, ticketId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection(ps, rs);
        }

        return replies;
    }
}
