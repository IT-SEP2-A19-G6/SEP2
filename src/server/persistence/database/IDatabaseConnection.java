package server.persistence.database;

import server.exceptions.DataConnectionException;

import java.util.ArrayList;

public interface IDatabaseConnection {
    ArrayList<Object[]> executePreparedQuery(String preparedSql) throws DataConnectionException;
    String getSchemaName();
    String getClientTableName();
    String getTicketTableName();
}
