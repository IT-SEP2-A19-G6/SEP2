package server.persistence.admin;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Response;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO implements IAdminDAO {
    private IDatabaseConnection databaseConnection;

    public AdminDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Response addBranch(String branchName) throws DataConnectionException {
        Response response = new Response();
        try {
            String sql = "insert into " + databaseConnection.getBranchTableName() + " (branchname) VALUES (?);";

            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

            preparedStatement.setString(1, branchName);

            databaseConnection.executeUpdate(preparedStatement);
            response.setMessage("Success");
            return response;
        } catch (SQLException e) {
            System.out.println("Creating branch failed, no ID obtained" + e.getMessage());
        }
        response.setMessage("Operation failed");
        return response;
    }
}
