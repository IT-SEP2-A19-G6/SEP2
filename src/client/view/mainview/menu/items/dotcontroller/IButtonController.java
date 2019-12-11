package client.view.mainview.menu.items.dotcontroller;

public interface IButtonController {
    void addDotController(IDotController dotController);
    void clientButtonPressed(IDotController dot);
    void branchButtonPressed(IDotController dot);
    void plusButtonPressed(IDotController dot);
}
