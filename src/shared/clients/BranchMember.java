package shared.clients;

public class BranchMember extends Client {

    public BranchMember(String username, String password, ClientType type) {
        super(username, password, type);
    }

    @Override
    public void setType(ClientType type) {

    }
}
