package server.persistence.admin;

import server.exceptions.DataConnectionException;
import shared.Response;

public interface IAdminDAO {
    Response addBranch(String branchName) throws DataConnectionException;
}
