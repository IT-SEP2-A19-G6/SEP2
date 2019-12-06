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

    public TicketList(){
        vBox = new VBox();
        ticketList = new ScrollPane(vBox);
        ticketList.setPrefSize(600, 600);
    }

    public void addTicketToList(HBox ticketItem){
        Platform.runLater(() ->{
            vBox.getChildren().add(ticketItem);
        });
    }

    public ScrollPane getTicketList(){
        return ticketList;
    }

    public HBox getEmptyList(String message){
        Label messageLabel = new Label(message);
        messageLabel.setFont(Font.font("System", 24));
        messageLabel.setTextFill(Color.DARKCYAN);
        HBox hBox = new HBox(messageLabel);
        hBox.setPrefWidth(600);
        hBox.setPrefHeight(600);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }
}
