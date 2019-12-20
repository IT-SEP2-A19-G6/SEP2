package shared.clients;

public class BranchMember extends Client {

    private final int branchId;

    public BranchMember(int id, String username, ClientType type, int branchId) {
        super(id, username, type);
        this.branchId = branchId;
    }

    public int getBranchId() {
        return branchId;
    }

    @Override
    public String toString() {
        return super.getUsername();
    }
}
