package client.viewmodel.client.uielements;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TicketList {
    private ScrollPane ticketList;
    private VBox vBox;
    private final int listSize = 600;
    private int size;

    public TicketList(){
        vBox = new VBox();
        size = 0;
        ticketList = new ScrollPane(vBox);
        ticketList.setPrefSize(listSize, listSize);
    }

    public void addTicketToList(HBox ticketItem){
        Platform.runLater(() ->{
            vBox.getChildren().add(ticketItem);
        });
        size++;
    }

    public ScrollPane getTicketList(){
        return ticketList;
    }

    public HBox getEmptyList(String message){
        Label messageLabel = new Label(message);
        messageLabel.setFont(Font.font("System", 24));
        messageLabel.setTextFill(Color.DARKCYAN);
        HBox hBox = new HBox(messageLabel);
        hBox.setPrefWidth(listSize);
        hBox.setPrefHeight(listSize);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    public int getListSize(){
        return size;
    }
}
