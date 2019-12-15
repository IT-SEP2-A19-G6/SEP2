package client.viewmodel.admin;

import client.model.admin.IAdminModel;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.Request;
import shared.Response;

import java.beans.PropertyChangeEvent;

public class AdminViewModel {
    IAdminModel adminModel;
    private StringProperty branchField, usernameField, pwField, cPwField, bInfo, empInfo;

    public AdminViewModel(IAdminModel adminModel) {
        this.adminModel = adminModel;
        this.branchField = new SimpleStringProperty();
        this.usernameField = new SimpleStringProperty();
        this.pwField = new SimpleStringProperty();
        this.cPwField = new SimpleStringProperty();
        this.bInfo = new SimpleStringProperty();
        empInfo = new SimpleStringProperty();
        addListeners();
        clearView();
        setBranchBox();
    }

    private void setBranchBox() {
    }

    private void clearView() {
        bInfo.setValue("");
        empInfo.setValue("");
        branchField.setValue("");
        usernameField.setValue("");
        pwField.setValue("");
        cPwField.setValue("");
    }

    private void addListeners() {
        adminModel.addPropertyChangeListener(Request.TYPE.ADD_BRANCH_RESPONSE.name(), this::handleResponse);
        adminModel.addPropertyChangeListener(Request.TYPE.BRANCH_RESPONSE.name(), this::handleBranches);

    }

    private void handleBranches(PropertyChangeEvent propertyChangeEvent) {
        System.out.println("new Branches received");
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Response response = (Response) propertyChangeEvent.getNewValue();
        bInfo.setValue(response.getMessage());
    }

    public void addBranch() {
        if (branchField.getValue().isEmpty()){
            bInfo.setValue("Enter a branch name");
        } else {
            adminModel.addBranch(branchField.getValue());
        }
    }

    public void addBranchMember() {
    }

    public Property<String> branchFieldProperty() {
        return branchField;
    }

    public Property<String> usernameFieldProperty() {
        return usernameField;
    }

    public Property<String> pwFieldProperty() {
        return pwField;
    }

    public Property<String> cPwFieldProperty() {
        return cPwField;
    }

    public Property<String> bInfoProperty() {
        return bInfo;
    }

    public Property<String> empInfoProperty() {
        return empInfo;
    }


}
