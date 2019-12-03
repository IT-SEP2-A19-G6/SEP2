package client.viewmodel.user;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import static javafx.geometry.Pos.*;

public class TicketItem {

    public HBox addTicketItem(String id, String createdTime, String lastUpdateTime, String description, String status, String branch, String assignee, String tag){

        GridPane topRow = oneRow3ColGridPane("ID: ", id, "Created: ", createdTime, "Last Update: ", lastUpdateTime);
        topRow.setPrefWidth(575);
        topRow.setPadding(new Insets(5));

        GridPane midRow = oneRow2ColGridPane(description, status);
        midRow.setPrefWidth(575);
        midRow.setPadding(new Insets(5));

        GridPane bottomRow = oneRow3ColGridPane("Branch: ", branch, "Assigned to: ", assignee, "#", tag);
        bottomRow.setPrefWidth(575);
        bottomRow.setPadding(new Insets(5));

        VBox vBox = new VBox(topRow, midRow, bottomRow);
        vBox.setPrefWidth(575);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));


        HBox hBox = new HBox(vBox);
        hBox.setPrefWidth(575);
        hBox.setMaxWidth(hBox.getPrefWidth());
        hBox.setPadding(new Insets(5));
        hBox.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                UserViewModel.getTicket(id);
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

}
