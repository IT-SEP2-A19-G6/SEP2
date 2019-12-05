package shared;

import shared.clients.BranchMember;

import java.io.Serializable;
import java.util.ArrayList;

public class Branch implements Serializable {

    private String department;

    public Branch(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
