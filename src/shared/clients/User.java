package shared.clients;

public class User extends Client{

    public User(String username, String password, String type) {
        super(username, password, type);
    }

    @Override
    public void setType(String type) {

    }
}
