package shared;

import java.io.Serializable;

public class Request implements Serializable {

    public TYPE type;
    public Object object;

    public enum TYPE {
        ADD_USER,
        LOGIN_USER
    }

    public Request(TYPE type, Object object) {
        this.type = type;
        this.object = object;
    }
}
