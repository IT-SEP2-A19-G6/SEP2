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

    @FXML Label infoLabel;

    @FXML Label categoryLabel;

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
        categoryLabel.setTextFill(categoryComboBox.getValue().contains("Choose a category") ? Color.RED : Color.BLACK);
        if (subjectTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || categoryComboBox.getValue().contains("Choose a category")){
            infoLabel.setText("Please fill required fields!");
            return;
        }

        createTicketViewModel.submitTicket();
    }


    public void onResetButtonClick(ActionEvent actionEvent) {
        createTicketViewModel.clearFields();
        categoryComboBox.getSelectionModel().select(0);
        labelComment.setTextFill(Color.BLACK);
        labelSubject.setTextFill(Color.BLACK);
        categoryLabel.setTextFill(Color.BLACK);
    }
}
