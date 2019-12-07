package client.viewmodel.client.uielements;

import client.viewmodel.client.ClientViewModel;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;

public class TicketListItem {
    private ClientViewModel clientViewModel;
    private String id, username, subject, category, location, description, ticketStatus, branch, assignee, createdTime;
    private HBox ticketListItem;

    public TicketListItem(ClientViewModel clientViewModel, int id, String createdTime, String username, String subject, String description, String category, String location, String ticketStatus, String branch, String assignee) {
        this.clientViewModel = clientViewModel;
        this.id = String.valueOf(id);
        this.createdTime = createdTime;
        this.username = username;
        this.subject = subject;
        this.description = description;
        this.category = category;
        this.location = location;
        this.ticketStatus = ticketStatus;
        this.branch = branch;
        this.assignee = assignee;
        this.createdTime = createdTime;

        this.ticketListItem = createTicketItem();
    }

    private HBox createTicketItem(){

        GridPane topRow = topRow4Col("ID: ", id, "Created: ", createdTime, "Created by: ", this.username, "Location: ", location);
        topRow.setPrefWidth(580);
        topRow.setPadding(new Insets(5));

        GridPane midRow = midRow2Col(description, ticketStatus);
        midRow.setPrefWidth(580);
        midRow.setPadding(new Insets(5));

        GridPane bottomRow = bottomRow3col("Branch: ", branch, "Assigned to: ", assignee, "#", subject);
        bottomRow.setPrefWidth(580);
        bottomRow.setPadding(new Insets(5));

        VBox vBox = new VBox(topRow, midRow, bottomRow);
        vBox.setPrefWidth(580);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));


        HBox hBox = new HBox(vBox);
        hBox.setPrefWidth(580);
        hBox.setMaxWidth(hBox.getPrefWidth());
        hBox.setPadding(new Insets(5));
        hBox.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                clientViewModel.getTicket(getId());
            }
        });

        return hBox;
    }

    private HBox labelContentHBox(String label, String content){
        Label nameLabel = new Label(label);
        Label contentLabel = new Label(content);
        return new HBox(nameLabel, contentLabel);
    }

    private GridPane topRow4Col(String leftLabelName, String leftLabelContent, String midLeftLabelName, String midLeftLabelContent,String midRightLabelName, String midRightLabelContent, String rightLabelName, String rightLabelContent){
        HBox left = labelContentHBox(leftLabelName, leftLabelContent);
        left.setAlignment(CENTER_LEFT);
        left.setPrefWidth(70);

        HBox midLeft = labelContentHBox(midLeftLabelName, midLeftLabelContent);
        midLeft.setAlignment(CENTER_LEFT);
        midLeft.setPrefWidth(170);

        HBox midRight = labelContentHBox(midRightLabelName, midRightLabelContent);
        midRight.setAlignment(CENTER_LEFT);
        midRight.setPrefWidth(170);

        HBox right = labelContentHBox(rightLabelName, rightLabelContent);
        right.setAlignment(CENTER_LEFT);
        right.setPrefWidth(170);

        GridPane gridPane = new GridPane();
        gridPane.addRow(4, left, midLeft, midRight, right);

        return gridPane;
    }

    private  GridPane midRow2Col(String description, String status){
        Label desc = new Label(description);
        desc.setFont(Font.font("System", 24));
        desc.setTextFill(Color.DARKCYAN);
        HBox leftBox = new HBox(desc);
        leftBox.setAlignment(CENTER_LEFT);
        leftBox.setPrefWidth(375);

        Label stat = new Label(status);
        stat.setFont(Font.font("System", FontWeight.BOLD, 20));
        HBox rightBox = new HBox(stat);
        rightBox.setAlignment(CENTER);
        rightBox.setPrefWidth(200);

        GridPane gridPane = new GridPane();
        gridPane.addRow(2, leftBox, rightBox);

        return gridPane;
    }

    private GridPane bottomRow3col(String leftLabelName, String leftLabelContent, String midLabelName, String midLabelContent, String rightLabelName, String rightLabelContent){
        HBox left = labelContentHBox(leftLabelName, leftLabelContent);
        left.setAlignment(CENTER_LEFT);
        left.setPrefWidth(200);

        HBox mid = labelContentHBox(midLabelName, midLabelContent);
        mid.setAlignment(CENTER_LEFT);
        mid.setPrefWidth(200);

        HBox right = labelContentHBox(rightLabelName, rightLabelContent);
        right.setAlignment(CENTER_LEFT);
        right.setPrefWidth(140);

        GridPane gridPane = new GridPane();
        gridPane.addRow(3, left, mid, right);

        return gridPane;
    }


    public String getId() {
        return id;
    }

    public HBox getTicketListItem() {
        return ticketListItem;
    }
}
