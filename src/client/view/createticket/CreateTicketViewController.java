package client.view.createticket;

import client.viewModel.createticket.CreateTicketViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
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

    public void init(CreateTicketViewModel vm) {
        this.vm = vm;
        subjectTextField.textProperty().bindBidirectional(vm.subjectProperty());
        descriptionTextArea.textProperty().bindBidirectional(vm.descriptionProperty());
        locationTextField.textProperty().bindBidirectional(vm.locationProperty());
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
