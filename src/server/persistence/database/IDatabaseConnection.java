package server.persistence.database;

import server.exceptions.DataConnectionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IDatabaseConnection {



    PreparedStatement executePreparedQuery(String preparedSql) throws DataConnectionException;
    PreparedStatement createPreparedStatement(String preparedSql) throws DataConnectionException;
    void executeUpdate(PreparedStatement preparedStatement);
    String getSchemaName();
    String getClientTableName();
    String getTicketTableName();
    String getUserTableName();
    String getReplyTableName();

    void closeConnection(PreparedStatement ps, ResultSet rs);
}