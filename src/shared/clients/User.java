package shared.clients;

public class User extends Client{

    public User(String username, String password) {
        super(username, password);
    }

    @Override
    public String access() {
        return "User";
    }

}
