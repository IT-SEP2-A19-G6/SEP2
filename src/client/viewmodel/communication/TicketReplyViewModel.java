package client.viewmodel.communication;

import client.model.communication.ITicketReplyModel;
import client.viewmodel.statemachine.IStateController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TicketReplyViewModel implements IStateController {
    private ITicketReplyModel ticketReplyModel;
    private StringProperty messageArea;

    public TicketReplyViewModel(ITicketReplyModel ticketReplyModel) {
        this.ticketReplyModel = ticketReplyModel;
        messageArea = new SimpleStringProperty("");
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
        messageArea.setValue("");
    }

    public void submitReply() {
        ticketReplyModel.submitReply(messageArea.getValue());
    }

    public StringProperty messageAreProperty() {
        return messageArea;
    }

}
