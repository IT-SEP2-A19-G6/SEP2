package shared;

import java.io.Serializable;

public class Request implements Serializable {

    public final TYPE type;
    public final Object object;


    @SuppressWarnings("SpellCheckingInspection")
    public enum TYPE {
        LOGIN_REQ, //object contains User
        LOGIN_RESPONSE, //object equals Response
        CLOSE_CONNECTION, //to close socket connection
        TICKET_LIST_REQ, //Object contains TicketListExchange
        TICKET_LIST_RESPONSE, //Object contains TicketListExchange
        TICKET_REPLIES_REQ, //Object contains TicketId
        TICKET_REPLY_RESPONSE, //Object contains ArrayList<Ticket>
        ADD_TICKET_REPLY, //Object contains TicketReply
        OWN_TICKET_LIST_REQ, // Object contains String, username
        ASSIGNED_TICKET_LIST_REQ, // Object contains String, username
        BRANCH_TICKET_LIST_REQ, // Object contains String, username
        NO_TICKETS_FOUND_RESPONSE, //Object contains String "someMessage"
        SIGNUP_REQ,
        SIGNUP_RESPONSE,
        TICKET_CREATE,
        TICKET_RECEIVE,
        SET_STATE, // Object contains client type
        BRANCH_REQ, // no object
        BRANCH_RESPONSE, // Object contains ArrayList<Branch>
        TICKET_SET_STATUS, // Object contains ticket with updated status
        BRANCH_MEMBERS_BY_BRANCHNAME_REQ, // Object contains branchName
        BRANCH_MEMBERS_BY_BRANCHNAME_REPLY,
        SET_ASSIGNEE, // Object contains integer with assignee id
    }

    public Request(TYPE type, Object object) {
        this.type = type;
        this.object = object;
    }
}
