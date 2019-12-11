package client.viewmodel.mainview;

import client.util.ClientProperties;
import client.viewmodel.mainview.states.BranchMemberState;
import client.viewmodel.mainview.states.IClientState;
import client.viewmodel.mainview.states.UserState;
import javafx.beans.property.*;
import shared.clients.ClientType;

public class MenuViewModel {
    private IClientState currentState;
    private BooleanProperty clientIcon;
    private BooleanProperty isUser;
    private StringProperty clientLabel;
    private BooleanProperty plusIcon;
    private StringProperty plusLabel;
    private BooleanProperty branchIcon;
    private StringProperty branchLabel;

    public MenuViewModel(){
        clientIcon = new SimpleBooleanProperty();
        isUser = new SimpleBooleanProperty();
        clientLabel = new SimpleStringProperty();
        plusIcon = new SimpleBooleanProperty();
        plusLabel = new SimpleStringProperty();
        branchIcon = new SimpleBooleanProperty();
        branchLabel = new SimpleStringProperty();
        clearMenu();
        setInitState();
    }

    public void clearMenu() {
        clientIcon.setValue(false);
        clientLabel.setValue("");
        plusIcon.setValue(false);
        plusLabel.setValue("");
        branchIcon.setValue(false);
        branchLabel.setValue("");
    }

    private void setInitState(){
        if (ClientProperties.getInstance().getClient().getType().equals(ClientType.USER)){
            setState(new UserState());
        } else if (ClientProperties.getInstance().getClient().getType().equals(ClientType.BRANCH_MEMBER)) {
            setState(new BranchMemberState());
        }
    }

    private void setState(IClientState newState){
        if (currentState != null){
            currentState.exit();
        }
        currentState = newState;
        currentState.entry(this);
    }


    public void addClientIcon(boolean show, boolean isUser, String username){
        clientIcon.setValue(show);
        clientLabel.setValue(username);
        this.isUser.setValue(isUser);
    }

    public void addPlusIcon(boolean show, String iconText){
        plusIcon.setValue(show);
        plusLabel.setValue(iconText);
    }

    public void addBranchIcon(boolean show, String branchName){
        branchIcon.setValue(show);
        branchLabel.setValue(branchName);
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
