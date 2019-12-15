package shared;

import shared.clients.BranchMember;

import java.io.Serializable;
import java.util.ArrayList;

public class Branch implements Serializable {

    private int id;
    private String branchName;
    private ArrayList<BranchMember> branchMembers;

    public Branch(int id, String branchName) {
        this.id = id;
        this.branchName = branchName;
    }

    public Branch(String branchName){
        this.branchName = branchName;
    }

    public int getId() {
        return id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void addBranchMember(BranchMember branchMember){
        if (!getMemberList().contains(branchMember)){
            getMemberList().add(branchMember);
        }
    }

    public void setBranchMembers(ArrayList<BranchMember> branchMembers){
        getMemberList().clear();
        getMemberList().addAll(branchMembers);
    }

    public ArrayList<BranchMember> getBranchMembers(){
        return getMemberList();
    }

    private ArrayList<BranchMember> getMemberList(){
        if (branchMembers == null){
            branchMembers = new ArrayList<>();
        }
        return branchMembers;
    }
}
