package client.viewmodel.statemachine.states;

import client.viewmodel.statemachine.StateHandler;

public class BranchMemberState implements IClientState {
    private StateHandler stateHandler;

    @Override
    public void entry(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
        stateHandler.setBranchOptions();
    }

    @Override
    public void exit() {
        stateHandler.clearCurrentOptions();
    }

}
