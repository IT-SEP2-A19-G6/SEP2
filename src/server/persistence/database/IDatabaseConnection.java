package server.persistence.database;

import server.exceptions.DataConnectionException;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public interface IDatabaseConnection {
    ArrayList<Object[]> executePreparedQuery(String preparedSql) throws DataConnectionException;
    PreparedStatement createPreparedStatement(String preparedSql) throws DataConnectionException;
    void executeUpdate(PreparedStatement preparedStatement);
    String getSchemaName();
    String getClientTableName();
    String getTicketTableName();
    String getUserTableName();
}