package server.model.admin;

import shared.Branch;
import shared.Response;

import java.util.ArrayList;

public interface IAdminServerModel {
    Response addBranch(Branch branch);
}
