package server.model.ticketlist;

import server.exceptions.DataConnectionException;
import server.persistence.ticketlist.ITicketListDAO;
import shared.Request;
import shared.TicketListExchange;
import shared.clients.BranchMember;
import shared.clients.Client;
import shared.clients.User;


public class TicketListServerModelHandler implements ITicketListServerModel {
    private ITicketListDAO ticketListDAO;

    public TicketListServerModelHandler(ITicketListDAO userDAO) {
        this.ticketListDAO = userDAO;
    }

    @Override
    public TicketListExchange requestTicketList(TicketListExchange exchange) {
        if (exchange.getAction().equals(Request.TYPE.OWN_TICKET_LIST_REQ)){
            User user = (User) exchange.getClient();
            try {
                exchange.setTicketList(ticketListDAO.getOwnTicketList(user.getClientId()));
                if (exchange.getTickets().size() == 0){
                    exchange.setAction(Request.TYPE.NO_TICKETS_FOUND_RESPONSE);
                    exchange.setMessage("No tickets yet - try adding one...");
                    return exchange;
                }
            } catch (DataConnectionException e) {
                e.printStackTrace();
            }
        } else if (exchange.getAction().equals(Request.TYPE.ASSIGNED_TICKET_LIST_REQ)){
            BranchMember member = (BranchMember) exchange.getClient();
            try {
                exchange.setTicketList(ticketListDAO.getAssignedTicketList(member.getClientId()));
                if (exchange.getTickets().size() == 0){
                    exchange.setAction(Request.TYPE.NO_TICKETS_FOUND_RESPONSE);
                    exchange.setMessage("Good job!! go fetch more @branch.");
                    return exchange;
                }
            } catch (DataConnectionException e) {
                e.printStackTrace();
            }
        } else if (exchange.getAction().equals(Request.TYPE.BRANCH_TICKET_LIST_REQ)){
            BranchMember member = (BranchMember) exchange.getClient();
            try {
                exchange.setTicketList(ticketListDAO.getBranchTicketList(member.getBranchId()));
                if (exchange.getTickets().size() == 0){
                    exchange.setAction(Request.TYPE.NO_TICKETS_FOUND_RESPONSE);
                    exchange.setMessage("No tickets!! relax until more arrives...");
                    return exchange;
                }
            } catch (DataConnectionException e) {
                e.printStackTrace();
            }
        }
        exchange.setAction(Request.TYPE.TICKET_LIST_RESPONSE);
        return exchange;
    }
}
