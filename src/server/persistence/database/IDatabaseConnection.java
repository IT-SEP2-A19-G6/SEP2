package server.persistence.database;

import shared.exceptions.DataConnectionException;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabaseConnection {
    ArrayList<Object[]> executePreparedQuery(String preparedSql) throws DataConnectionException;
    String getSchemaName();
    String getClientTableName();
}
