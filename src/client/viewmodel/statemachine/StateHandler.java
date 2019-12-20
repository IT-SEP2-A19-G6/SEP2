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
    private final IStateController menuItemController;


    public StateHandler(ViewModelFactory viewModelFactory, ILoginModel loginModel){
        menuItemController = viewModelFactory.getMenuViewModel();
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
    }

    public void setBranchOptions(){
        menuItemController.setBranchOptions();
    }

    public void clearCurrentOptions(){
        menuItemController.clearCurrentOptions();
    }





}
