package client.view.mainview.menu.items.dotcontroller;


import java.util.ArrayList;

public class DotHandler implements IButtonController {
    ArrayList<IDotController> dots;

    public DotHandler(){
        dots = new ArrayList<>();
    }


    @Override
    public void addDotController(IDotController dotController) {
        dots.add(dotController);
    }

    @Override
    public void clientButtonPressed(IDotController dot) {
        for (IDotController dotIn : dots){
            if (dotIn.equals(dot)){
                dotIn.setVisibility(true);
            } else {
                dotIn.setVisibility(false);
            }
        }
    }

    @Override
    public void branchButtonPressed(IDotController dot) {
        for (IDotController dotIn : dots){
            if (dotIn.equals(dot)){
                dotIn.setVisibility(true);
            } else {
                dotIn.setVisibility(false);
            }
        }
    }

    @Override
    public void plusButtonPressed(IDotController dot) {
        for (IDotController dotIn : dots){
            if (dotIn.equals(dot)){
                dotIn.setVisibility(true);
            } else {
                dotIn.setVisibility(false);
            }
        }
    }
}

