package client.viewmodel.communication;

import client.model.communication.ITicketReplyModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.IPropertyChangeSubject;
import shared.Request;
import shared.TicketReply;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TicketReplyViewModel implements IPropertyChangeSubject {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final ITicketReplyModel ticketReplyModel;
    private final StringProperty messageArea;


    public TicketReplyViewModel(ITicketReplyModel ticketReplyModel) {

        this.ticketReplyModel = ticketReplyModel;
        messageArea = new SimpleStringProperty("");
        addListeners();
    }

    private void addListeners() {
        ticketReplyModel.addPropertyChangeListener(Request.TYPE.TICKET_REPLY_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        //noinspection unchecked
        ArrayList<TicketReply> serverReplies = (ArrayList<TicketReply>) propertyChangeEvent.getNewValue();
        if (serverReplies.size() > 0){
            support.firePropertyChange(Request.TYPE.TICKET_REPLY_RESPONSE.name() + serverReplies.get(0).getTicketId(),"", serverReplies);
        }
    }

    public void addReply(int ticketId) {
        ticketReplyModel.addReply(ticketId, messageArea.getValue());
    }

    public StringProperty messageAreaProperty() {
        return messageArea;
    }

    @SuppressWarnings("SpellCheckingInspection")
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


}


