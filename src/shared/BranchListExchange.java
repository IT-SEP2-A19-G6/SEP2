package shared;

import java.util.ArrayList;

public class BranchListExchange {
    private Request.TYPE action;
    private ArrayList<Branch> branches;

    public BranchListExchange(Request.TYPE action, Branch branch){
        this.action = action;
        getBranchList().add(branch);
    }

    public void setAction(Request.TYPE action) {
        this.action = action;
    }

    public Request.TYPE getAction() {
        return action;
    }

    public void addBranch(Branch branch){
        getBranchList().add(branch);
    }

    public void setBranches(ArrayList<Branch> branches){
        getBranchList().clear();
        getBranchList().addAll(branches);
    }

    public ArrayList<Branch> getBranches() {
        return getBranchList();
    }

    private ArrayList<Branch> getBranchList(){
        if (branches == null){
            branches = new ArrayList<>();
        }
        return branches;
    }
}
