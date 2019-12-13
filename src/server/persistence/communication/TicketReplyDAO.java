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
    private IDatabaseConnection databaseConnection;

    public TicketReplyDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<TicketReply> getReplies(int ticketId) {
        String sql =  "SELECT r.Reply_Id, t.id, tStamp, r.user_Id, r.message from" + databaseConnection.getTicketTableName() + " t, "
                + databaseConnection.getReplyTableName() + " r, " +
         "where t.id = " + ticketId;
        return getRepliesByTicketId(sql);
    }

    @Override
    public String addReply(TicketReply reply) throws DataConnectionException{

        try {
            String sql = "INSERT INTO " + "Reply" + " (replyId, Ticket_Id, user_Id, message)" + " VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

            preparedStatement.setInt(1, reply.getReplyId());
            preparedStatement.setInt(2, reply.getTicketId());
            preparedStatement.setDate(3, reply.getTimeStamp());
            preparedStatement.setString(4, reply.getUsername());
            preparedStatement.setString(5, reply.getMessage());

            databaseConnection.executeUpdate(preparedStatement);
            return "Reply has been added to the Ticket";
        } catch (SQLException e) {
            System.out.println("Creating reply failed: " + e.getMessage());
        }
            return "Reply has failed to add";
    }

    public ArrayList<TicketReply> getRepliesByTicketId(String preparedSql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TicketReply> replies = new ArrayList<>();
        try {
            ps = databaseConnection.executePreparedQuery(preparedSql);
            rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("ReplyId");
                int ticketId = rs.getInt("TicketId");
                Date timestamp = rs.getDate("tStamp");
                String username = rs.getString("user_Id");
                String message = rs.getString("message");


                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String date = dateFormat.format(timestamp);

                replies.add(new TicketReply(id, ticketId, date, username, message));
            }
        } catch (SQLException | DataConnectionException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection(ps, rs);
        }

        return replies;
    }
}
