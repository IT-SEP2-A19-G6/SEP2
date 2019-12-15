package client.viewmodel.communication;

import client.model.communication.ITicketReplyModel;
import client.viewmodel.statemachine.IStateController;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.IPropertyChangeSubject;
import shared.Request;
import shared.TicketReply;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TicketReplyViewModel implements IStateController, IPropertyChangeSubject {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
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
        ticketReplyModel.addPropertyChangeListener(Request.TYPE.TICKET_REPLY_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<TicketReply> serverReplies = (ArrayList<TicketReply>) propertyChangeEvent.getNewValue();
        if (serverReplies.size() > 0){
            support.firePropertyChange(Request.TYPE.TICKET_REPLY_RESPONSE.name() + serverReplies.get(0).getTicketId(),"", serverReplies);
        }
      //  replies.setAll(serverReplies);
    }

    public ObservableList<TicketReply> getTicketReplies() {
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

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null){
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null){
            support.removePropertyChangeListener(listener);
        } else {
            support.removePropertyChangeListener(name, listener);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }


}


