package shared.clients;

public class User extends Client{

    public User(String username, String password, ClientType type) {
        super(username, password, type);
    }

    public User(String username, String password) {
        super(username, password);
    }


    @Override
    public void setType(ClientType type) {

    }
}
