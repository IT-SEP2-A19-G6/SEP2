package client.view.createticket;

import client.view.mainview.menu.MenuViewController;
import client.view.mainview.menu.items.IVirtualButton;
import client.viewmodel.createticket.CreateTicketViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;


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


    public void onResetButtonClick(ActionEvent actionEvent) {
        createTicketViewModel.clearFields();
        categoryComboBox.getSelectionModel().select(0);
    }
}
