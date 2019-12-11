package client.viewmodel.communication;

import client.model.communication.ITicketReplyModel;
import client.viewmodel.statemachine.IStateController;

public class TicketReplyViewModel implements IStateController {
    private ITicketReplyModel ticketReplyModel;

    public TicketReplyViewModel(ITicketReplyModel ticketReplyModel) {
        this.ticketReplyModel = ticketReplyModel;
    }


    @Override
    public void setUserOptions() {
        System.out.println("Reply VM entered user state"); //TODO delete sout
    }

    @Override
    public void setBranchOptions() {
        System.out.println("Reply VM entered branch state"); //TODO delete sout
    }

    @Override
    public void clearCurrentOptions() {
        //if anything needs to be cleared on exit...
    }

}
