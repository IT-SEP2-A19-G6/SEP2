package server.model.admin;

import server.exceptions.DataConnectionException;
import server.persistence.admin.IAdminDAO;
import shared.Branch;
import shared.Response;

import java.util.ArrayList;

public class AdminServerModelHandler implements IAdminServerModel {
    private IAdminDAO adminDAO;

    public AdminServerModelHandler(IAdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public Response addBranch(Branch branch) {

        try {
            String response = adminDAO.addBranch(branch.getBranchName());
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
