package shared.clients;

public class User extends Client{

    public User(int id, String username, String password, String type, boolean active) {
        super(id, username, password, type, active);
    }

    public User(String username, String password, String type) {
        super(username, password, type);
    }

    public User(String username, String password) {
        super(username, password);
    }


    @Override
    public void setType(String type) {

    }
}
