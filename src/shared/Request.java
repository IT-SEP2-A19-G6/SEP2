package shared;

import java.io.Serializable;

public class Request implements Serializable {

    public TYPE type;
    public Object object;


    public enum TYPE {
        LOGIN_REQ, //object contains User
        LOGIN_RESPONSE, //object contains Response
        CLOSE_CONNECTION, //to close socket connection Object contains ""
        TICKET_LIST_REQ, // Object contains String, username
        TICKET_LIST_RESPONSE, //Object contains ArrayList<Ticket>
        NO_TICKETS_FOUND_RESPONSE //Object contains String "someMessage"
    }

    public Request(TYPE type, Object object) {
        this.type = type;
        this.object = object;
    }
}
