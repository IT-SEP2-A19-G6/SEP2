package server.persistence.database;

import server.exceptions.DataConnectionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDatabaseConnection {



    PreparedStatement executePreparedQuery(String preparedSql) throws DataConnectionException;
    PreparedStatement createPreparedStatement(String preparedSql) throws DataConnectionException;
    void executeUpdate(PreparedStatement preparedStatement) throws SQLException;
    String getSchemaName();
    String getClientTableName();
    String getTicketTableName();
    String getReplyTableName();
    String getBranchTableName();


    void closeConnection(PreparedStatement ps, ResultSet rs);

}