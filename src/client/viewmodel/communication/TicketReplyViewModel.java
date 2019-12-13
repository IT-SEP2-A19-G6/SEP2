package client.viewmodel.communication;

import client.model.communication.ITicketReplyModel;
import client.viewmodel.statemachine.IStateController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Request;
import shared.Response;
import shared.TicketReply;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class TicketReplyViewModel implements IStateController {
    private ITicketReplyModel ticketReplyModel;
    private StringProperty messageArea;
    private StringProperty replyResult;
    private ObservableList<TicketReply> replies;

    public TicketReplyViewModel(ITicketReplyModel ticketReplyModel) {
        this.ticketReplyModel = ticketReplyModel;
        messageArea = new SimpleStringProperty("");
        replyResult = new SimpleStringProperty("");
        replies = FXCollections.observableArrayList();
        addListeners();
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

    private void addListeners() {
        ticketReplyModel.addPropertyChangeListener(Request.TYPE.TICKET_REPLY_RESPONSE.name(), this ::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<TicketReply> serverReplies = (ArrayList<TicketReply>) propertyChangeEvent.getNewValue();
        replies.setAll(serverReplies);
    }

    public ObservableList<TicketReply> getReplies() {
        return replies;
    }

    public void addReply(int ticketId) {
        ticketReplyModel.addReply(ticketId, messageArea.getValue());
    }

    public StringProperty messageAreaProperty() {
        return messageArea;
    }

    public StringProperty replyResultProperty() {
        return replyResult;
    }

    public void getReplies(int ticketid) {
        ticketReplyModel.getReplies(ticketid);
    }
}
