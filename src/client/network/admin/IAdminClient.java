package client.network.admin;

import shared.Branch;
import shared.BranchListExchange;
import shared.IPropertyChangeSubject;

public interface IAdminClient extends IPropertyChangeSubject {
    void addBranch(Branch branch);

    void getBranches();
}
