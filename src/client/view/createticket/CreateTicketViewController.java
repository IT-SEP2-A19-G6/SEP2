package client.view.createticket;

import client.viewModel.createticket.CreateTicketViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class CreateTicketViewController {
    @FXML
    public ComboBox actionComboBox;
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
        labelComment.setTextFill(descriptionTextArea.getText().isEmpty() ? Color.RED : Color.BLACK);
        labelSubject.setTextFill(subjectTextField.getText().isEmpty() ? Color.RED : Color.BLACK);
        if (subjectTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()) return;

        vm.submitTicket();
    }

    public void onResetButtonClick() {
        vm.clearFields();
    }

    public void onCancelButtonClick() {
        Platform.exit();
    }
}
