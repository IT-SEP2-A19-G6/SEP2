package client.view.createticket;

import client.util.ClientProperties;
import client.view.ViewHandler;
import client.view.mainview.menu.MenuViewController;
import client.view.mainview.menu.items.IVirtualButton;
import client.view.mainview.menu.items.dotcontroller.IButtonController;
import client.viewmodel.createticket.CreateTicketViewModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import shared.Request;
import shared.TicketListExchange;

public class CreateTicketViewController {
    @FXML
    public ComboBox<String> categoryComboBox;
    @FXML
    public TextField subjectTextField;
    @FXML
    public TextArea descriptionTextArea;
    @FXML
    public TextField locationTextField;
    @FXML
    public Label labelSubject;
    @FXML
    public Label labelComment;

    private CreateTicketViewModel createTicketViewModel;
    private IVirtualButton clientButton;


    public void init(CreateTicketViewModel createTicketViewModel, MenuViewController menuViewController) {
        this.createTicketViewModel = createTicketViewModel;
        this.clientButton = menuViewController.getClientButtonController();
        subjectTextField.textProperty().bindBidirectional(createTicketViewModel.subjectProperty());
        descriptionTextArea.textProperty().bindBidirectional(createTicketViewModel.descriptionProperty());
        locationTextField.textProperty().bindBidirectional(createTicketViewModel.locationProperty());
        StringProperty ticketResult = new SimpleStringProperty();
        ticketResult.bind(createTicketViewModel.ticketResultProperty());
        categoryComboBox.valueProperty().bindBidirectional(this.createTicketViewModel.currentCategory());
        categoryComboBox.setItems(this.createTicketViewModel.getCategories());
        ticketResult.addListener((observableValue, s, t1) -> {
            if (t1.equals("OK")) {
                clientButton.pressButton();
            }
        });
    }

    public void onSubmitButtonClick() {
        labelComment.setTextFill(descriptionTextArea.getText().isEmpty() ? Color.RED : Color.BLACK);
        labelSubject.setTextFill(subjectTextField.getText().isEmpty() ? Color.RED : Color.BLACK);
        if (subjectTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()) return;

        createTicketViewModel.submitTicket();
    }


    public void onResetButtonClick(ActionEvent actionEvent) {
        createTicketViewModel.clearFields();
        categoryComboBox.getSelectionModel().select(0);
    }
}
