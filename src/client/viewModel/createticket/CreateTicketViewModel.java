package client.viewModel.createticket;

import client.model.createticket.ICreateTicketModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.Request;
import shared.Response;

import java.beans.PropertyChangeEvent;

public class CreateTicketViewModel {


    private ICreateTicketModel createTicketModel;
    private StringProperty subject;
    private StringProperty description;
    private StringProperty location;
    private StringProperty ticketResult;

    public CreateTicketViewModel(ICreateTicketModel createTicketModel) {
        this.createTicketModel = createTicketModel;
        subject = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        location = new SimpleStringProperty("");
        ticketResult = new SimpleStringProperty("");

        addListeners();
    }

    private void addListeners() {
        createTicketModel.addPropertyChangeListener(Request.TYPE.TICKET_RECEIVE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Response result = (Response) propertyChangeEvent.getNewValue();
        if (result.getMessage().contains("OK")){
            Platform.runLater(()->{
                clearFields();
                ticketResult.setValue(result.getMessage());
                ticketResult.setValue("");
            });
        }
    }


    public void clearFields() {
        subject.setValue("");
        description.setValue("");
        location.setValue("");
    }

    public void submitTicket() {
        createTicketModel.submitTicket(subject.getValue(), description.getValue(), location.getValue());
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty ticketResultProperty() {
        return ticketResult;
    }

}
