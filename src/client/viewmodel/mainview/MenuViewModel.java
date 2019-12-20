package client.viewmodel.mainview;


import client.util.ClientProperties;
import client.viewmodel.statemachine.IStateController;
import javafx.beans.property.*;


public class MenuViewModel implements IStateController {
    private final BooleanProperty clientIcon;
    private final BooleanProperty isUser;
    private final StringProperty clientLabel;
    private final BooleanProperty plusIcon;
    private final StringProperty plusLabel;
    private final BooleanProperty branchIcon;
    private final StringProperty branchLabel;

    public MenuViewModel(){
        clientIcon = new SimpleBooleanProperty();
        isUser = new SimpleBooleanProperty();
        clientLabel = new SimpleStringProperty();
        plusIcon = new SimpleBooleanProperty();
        plusLabel = new SimpleStringProperty();
        branchIcon = new SimpleBooleanProperty();
        branchLabel = new SimpleStringProperty();
        clearMenu();
    }

    private void clearMenu() {
        clientIcon.setValue(false);
        clientLabel.setValue("");
        plusIcon.setValue(false);
        plusLabel.setValue("");
        branchIcon.setValue(false);
        branchLabel.setValue("");
    }

    @Override
    public void setUserOptions(){
        clientIcon.setValue(true);
        clientLabel.setValue(ClientProperties.getInstance().getClient().getUsername());
        this.isUser.setValue(true);
        plusIcon.setValue(true);
        plusLabel.setValue("New Ticket");
    }

    @Override
    public void setBranchOptions(){
        clientIcon.setValue(true);
        clientLabel.setValue(ClientProperties.getInstance().getClient().getUsername());
        this.isUser.setValue(false);
        branchIcon.setValue(true);
        branchLabel.setValue("Branch");
    }

    @Override
    public void clearCurrentOptions() {
        clearMenu();
    }

    public Property<Boolean> showClientIconProperty() {
        return clientIcon;
    }

    public Property<String> clientLabelProperty() {
        return clientLabel;
    }

    public Property<Boolean> showPlusIconProperty() {
        return plusIcon;
    }

    public Property<String> plusLabelProperty() {
        return plusLabel;
    }

    public Property<Boolean> showBranchIconProperty() {
        return branchIcon;
    }

    public Property<String> branchLabelProperty() {
        return branchLabel;
    }


    public Property<Boolean> isUserProperty() {
        return isUser;
    }
}
