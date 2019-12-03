package client.viewmodel.user.uielements;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class TicketList {
    private ScrollPane ticketList;
    private VBox vBox;

    public TicketList(){
        vBox = new VBox();
        ticketList = new ScrollPane(vBox);
        ticketList.setPrefSize(600, 600);
    }

    public void addTicketToList(HBox ticketItem){
        vBox.getChildren().add(ticketItem);
    }

    public ScrollPane getTicketList(){
        return ticketList;
    }
}
