package shared;

import java.io.Serializable;

public class Request implements Serializable {

    public TYPE type;
    public Object object;


    public enum TYPE {
        LOGIN_REQ, //object contains User
        LOGIN_RESPONSE, //object equals Response
        CLOSE_CONNECTION, //to close socket connection
        TICKET // object contains new ticket
    }

    public Request(TYPE type, Object object) {
        this.type = type;
        this.object = object;
    }
}
