package client.viewmodel.createticket;

import client.model.createticket.ICreateTicketModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Request;
import shared.Response;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class CreateTicketViewModel {


    private final ICreateTicketModel createTicketModel;
    private final StringProperty subject;
    private final StringProperty description;
    private final StringProperty location;
    private final StringProperty ticketResult;
    private final ObservableList<String> categories;
    private final StringProperty currentCategory;
    private final String stdCategoryMessage = "Choose a category";

    public CreateTicketViewModel(ICreateTicketModel createTicketModel) {
        this.createTicketModel = createTicketModel;
        subject = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        location = new SimpleStringProperty("");
        ticketResult = new SimpleStringProperty();
        categories = FXCollections.observableArrayList();
        currentCategory = new SimpleStringProperty();
        addListeners();
        createTicketModel.getBranches();
    }



    private void addListeners() {
        createTicketModel.addPropertyChangeListener(Request.TYPE.TICKET_RECEIVE.name(), this::handleTicketResponse);
        createTicketModel.addPropertyChangeListener(Request.TYPE.BRANCH_RESPONSE.name(), this::createCategories);

    }

    private void createCategories(PropertyChangeEvent propertyChangeEvent) {
        categories.clear();
        categories.add(stdCategoryMessage);
        Platform.runLater(()-> currentCategory.setValue(stdCategoryMessage));
        //noinspection unchecked
        ArrayList<String> branchNames = (ArrayList<String>) propertyChangeEvent.getNewValue();
        categories.addAll(branchNames);
    }

    private void handleTicketResponse(PropertyChangeEvent propertyChangeEvent) {
        Response result = (Response) propertyChangeEvent.getNewValue();
        if (result.getMessage().contains("OK")){
            Platform.runLater(()-> ticketResult.setValue(result.getMessage()));
        }
    }


    public void clearFields() {
        subject.setValue("");
        description.setValue("");
        location.setValue("");
        currentCategory.setValue(stdCategoryMessage);
        ticketResult.setValue("");
    }

    public void submitTicket() {
        createTicketModel.submitTicket(subject.getValue(), currentCategory.getValue(), description.getValue(), location.getValue());
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

    public ObservableList<String> getCategories() {
        return categories;
    }

    public StringProperty currentCategory() {
        return currentCategory;
    }

}
