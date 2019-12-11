package client.viewmodel.statemachine.states;

import client.viewmodel.statemachine.StateHandler;

public interface IClientState {
    void entry(StateHandler stateHandler);
    void exit();
}
