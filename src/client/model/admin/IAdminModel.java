package client.model.admin;

import shared.BranchListExchange;
import shared.IPropertyChangeSubject;

public interface IAdminModel extends IPropertyChangeSubject {
    void addBranch(String branchName);
}
