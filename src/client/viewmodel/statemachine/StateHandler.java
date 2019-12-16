package client.viewmodel.statemachine;

import client.model.login.ILoginModel;
import client.viewmodel.ViewModelFactory;
import client.viewmodel.statemachine.states.BranchMemberState;
import client.viewmodel.statemachine.states.IClientState;
import client.viewmodel.statemachine.states.UserState;
import shared.Request;
import shared.clients.ClientType;

import java.beans.PropertyChangeEvent;

public class StateHandler {
    private IClientState currentState;
    private IStateController menuItemController;
    private IStateController replyItemController;


    public StateHandler(ViewModelFactory viewModelFactory, ILoginModel loginModel){
        menuItemController = viewModelFactory.getMenuViewModel();
        replyItemController = viewModelFactory.getTicketReplyViewModel();

        loginModel.addPropertyChangeListener(Request.TYPE.SET_STATE.name(), this::handleStateReq);
    }

    private void handleStateReq(PropertyChangeEvent propertyChangeEvent) {
        ClientType type = (ClientType) propertyChangeEvent.getNewValue();
        if (type.equals(ClientType.USER)){
            setCurrentState(new UserState());
        } else if (type.equals(ClientType.BRANCH_MEMBER)) {
            setCurrentState(new BranchMemberState());
        }
    }

    private void setCurrentState(IClientState newState){
        if (currentState != null){
            currentState.exit();
        }
        currentState = newState;
        currentState.entry(this);
    }

    public void setUserOptions(){
        menuItemController.setUserOptions();
        replyItemController.setUserOptions();
    }

    public void setBranchOptions(){
        menuItemController.setBranchOptions();
        replyItemController.setBranchOptions();

    }

    public void clearCurrentOptions(){
        menuItemController.clearCurrentOptions();
        replyItemController.clearCurrentOptions();
    }





}
