package shared.clients;

public class User extends Client{

    public User(String username, String password) {
        super(username, password);
    }

    public User(int id, String username, ClientType type) {
        super(id, username, type);
    }


}
