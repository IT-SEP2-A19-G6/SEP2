package client.view.createticket;

import client.viewModel.createticket.CreateTicketViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateTicketViewController {
    @FXML
    public ComboBox actionComboBox;
    @FXML
    public TextField subjectTextField;
    @FXML
    public TextArea descriptionTextArea;
    @FXML
    public TextField locationTextField;

    private CreateTicketViewModel vm;
    private StringProperty ticketResult;

    public void init(CreateTicketViewModel vm) {
        this.vm = vm;
        subjectTextField.textProperty().bindBidirectional(vm.subjectProperty());
        descriptionTextArea.textProperty().bindBidirectional(vm.descriptionProperty());
        locationTextField.textProperty().bindBidirectional(vm.locationProperty());
        ticketResult = new SimpleStringProperty();
        ticketResult.bind(vm.ticketResultProperty());
        ticketResult.addListener((ChangeListener) (observable, oldValue, newValue) -> {
            if (newValue == "OK") {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ticket");
                alert.setHeaderText("Information Alert");
                String s ="The ticket has been successfully created";
                alert.setContentText(s);
                alert.show();
            }
        });
    }

    public void onSubmitButtonClick() {
        vm.submitTicket();
    }

    public void onResetButtonClick() {
        vm.clearFields();
    }

    public void onCancelButtonClick() {
        Platform.exit();
    }
}
