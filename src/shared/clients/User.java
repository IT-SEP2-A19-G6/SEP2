package shared.clients;

public class User extends Client{

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
