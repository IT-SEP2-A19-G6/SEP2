package client.view.admin;

import client.viewmodel.admin.AdminViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class AdminViewController {
    @FXML
    VBox branchBox, empBox;

    @FXML
    TextField branchField, usernameField;

    @FXML
    PasswordField pwField, cPwField;

    @FXML
    Label bInfo, empInfo;

    private AdminViewModel adminViewModel;

    public void init(AdminViewModel adminViewModel){
        this.adminViewModel = adminViewModel;
        branchField.textProperty().bindBidirectional(adminViewModel.branchFieldProperty());
        usernameField.textProperty().bindBidirectional(adminViewModel.usernameFieldProperty());
        pwField.textProperty().bindBidirectional(adminViewModel.pwFieldProperty());
        cPwField.textProperty().bindBidirectional(adminViewModel.cPwFieldProperty());
        bInfo.textProperty().bindBidirectional(adminViewModel.bInfoProperty());
        empInfo.textProperty().bindBidirectional(adminViewModel.empInfoProperty());
    }

    public void addBranch(ActionEvent actionEvent) {
        adminViewModel.addBranch();
    }

    public void addBranchMember(ActionEvent actionEvent) {
        adminViewModel.addBranchMember();
    }
}
