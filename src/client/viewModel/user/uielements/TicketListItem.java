package client.viewmodel.user.uielements;

import client.viewmodel.user.UserViewModel;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;

public class TicketListItem {
    private UserViewModel userViewModel;
    private String id, description, status, branch, assignee, tag, createdTime, lastUpdateTime;
    private HBox ticketListItem;

    public TicketListItem(UserViewModel userViewModel, String id, String description, String status, String branch, String assignee, String tag, String createdTime, String updatedTime) {
        this.userViewModel = userViewModel;
        this.id = id;
        this.description = description;
        this.status = status;
        this.branch = branch;
        this.assignee = assignee;
        this.tag = tag;
        this.createdTime = createdTime;
        this.lastUpdateTime = updatedTime;
        this.ticketListItem = createTicketItem();
    }

    private HBox createTicketItem(){

        GridPane topRow = oneRow3ColGridPane("ID: ", id, "Created: ", createdTime, "Last Update: ", lastUpdateTime);
        topRow.setPrefWidth(585);
        topRow.setPadding(new Insets(5));

        GridPane midRow = oneRow2ColGridPane(description, status);
        midRow.setPrefWidth(585);
        midRow.setPadding(new Insets(5));

        GridPane bottomRow = oneRow3ColGridPane("Branch: ", branch, "Assigned to: ", assignee, "#", tag);
        bottomRow.setPrefWidth(585);
        bottomRow.setPadding(new Insets(5));

        VBox vBox = new VBox(topRow, midRow, bottomRow);
        vBox.setPrefWidth(585);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));


        HBox hBox = new HBox(vBox);
        hBox.setPrefWidth(585);
        hBox.setMaxWidth(hBox.getPrefWidth());
        hBox.setPadding(new Insets(5));
        hBox.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                userViewModel.getTicket(getId());
            }
        });

        return hBox;
    }

    private HBox labelContentHBox(String label, String content){
        Label nameLabel = new Label(label);
        Label contentLabel = new Label(content);
        return new HBox(nameLabel, contentLabel);
    }

    private GridPane oneRow3ColGridPane(String leftLabelName, String leftLabelContent, String midLabelName, String midLabelContent, String rightLabelName, String rightLabelContent){
        HBox left = labelContentHBox(leftLabelName, leftLabelContent);
        left.setAlignment(CENTER_LEFT);
        left.setPrefWidth(192);

        HBox mid = labelContentHBox(midLabelName, midLabelContent);
        mid.setAlignment(CENTER_LEFT);
        mid.setPrefWidth(192);

        HBox right = labelContentHBox(rightLabelName, rightLabelContent);
        right.setAlignment(CENTER_LEFT);
        right.setPrefWidth(191);

        GridPane gridPane = new GridPane();
        gridPane.addRow(3, left, mid, right);

        return gridPane;
    }

    private  GridPane oneRow2ColGridPane(String description, String status){
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

    public String getId() {
        return id;
    }

    public HBox getTicketListItem() {
        return ticketListItem;
    }
}
