package client.view.createticket;

import client.view.mainview.menu.MenuViewController;
import client.view.mainview.menu.items.IVirtualButton;
import client.viewmodel.createticket.CreateTicketViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class CreateTicketViewController {
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField locationTextField;
    @FXML
    private Label labelSubject;
    @FXML
    private Label labelComment;
    @FXML
    private Label infoLabel;

    private CreateTicketViewModel createTicketViewModel;
    private IVirtualButton clientButton;


    public void init(CreateTicketViewModel createTicketViewModel, MenuViewController menuViewController) {
        this.createTicketViewModel = createTicketViewModel;
        this.clientButton = menuViewController.getClientButtonController();
        subjectTextField.textProperty().bindBidirectional(createTicketViewModel.subjectProperty());
        descriptionTextArea.textProperty().bindBidirectional(createTicketViewModel.descriptionProperty());
        locationTextField.textProperty().bindBidirectional(createTicketViewModel.locationProperty());
        infoLabel.textProperty().bindBidirectional(createTicketViewModel.ticketResultProperty());
        categoryComboBox.valueProperty().bindBidirectional(this.createTicketViewModel.currentCategory());
        categoryComboBox.setItems(this.createTicketViewModel.getCategories());
        createTicketViewModel.ticketResultProperty().addListener((observableValue, s, t1) -> {
            if (t1.equals("OK")) {
                clientButton.pressButton();
                createTicketViewModel.clearFields();
            }
        });
    }

    public void onSubmitButtonClick() {
        labelComment.setTextFill(descriptionTextArea.getText().isEmpty() ? Color.RED : Color.BLACK);
        labelSubject.setTextFill(subjectTextField.getText().isEmpty() ? Color.RED : Color.BLACK);
        if (subjectTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()) return;
        createTicketViewModel.submitTicket();
    }


    public void onResetButtonClick() {
        createTicketViewModel.clearFields();
        categoryComboBox.getSelectionModel().select(0);
    }
}
