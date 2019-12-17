package client.viewmodel.statemachine.states;

import client.viewmodel.statemachine.StateHandler;

public class UserState implements IClientState {
    private StateHandler stateHandler;

    @Override
    public void entry(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
        stateHandler.setUserOptions();
    }

    @Override
    public void exit() {
        stateHandler.clearCurrentOptions();
    }


}
