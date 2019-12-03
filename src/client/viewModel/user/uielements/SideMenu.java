package client.viewmodel.user.uielements;

import client.viewmodel.user.UserViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;


public class SideMenu {
    private VBox menu;
    private UserViewModel userViewModel;


    public SideMenu(UserViewModel userViewModel, String username){
        this.userViewModel = userViewModel;
        menu = createMenu(username);
        menu.setPrefWidth(120);
    }

    private VBox createMenu(String username){

        HBox userImg = imgBox("M239.7,260.2c0.5,0,1,0,1.6,0c0.2,0,0.4,0,0.6,0c0.3,0,0.7,0,1,0c29.3-0.5,53-10.8,70.5-30.5 c38.5-43.4,32.1-117.8,31.4-124.9c-2.5-53.3-27.7-78.8-48.5-90.7C280.8,5.2,262.7,0.4,242.5,0h-0.7c-0.1,0-0.3,0-0.4,0h-0.6 c-11.1,0-32.9,1.8-53.8,13.7c-21,11.9-46.6,37.4-49.1,91.1c-0.7,7.1-7.1,81.5,31.4,124.9C186.7,249.4,210.4,259.7,239.7,260.2z M164.6,107.3c0-0.3,0.1-0.6,0.1-0.8c3.3-71.7,54.2-79.4,76-79.4h0.4c0.2,0,0.5,0,0.8,0c27,0.6,72.9,11.6,76,79.4 c0,0.3,0,0.6,0.1,0.8c0.1,0.7,7.1,68.7-24.7,104.5c-12.6,14.2-29.4,21.2-51.5,21.4c-0.2,0-0.3,0-0.5,0l0,0c-0.2,0-0.3,0-0.5,0 c-22-0.2-38.9-7.2-51.4-21.4C157.7,176.2,164.5,107.9,164.6,107.3z M446.8,383.6c0-0.1,0-0.2,0-0.3c0-0.8-0.1-1.6-0.1-2.5c-0.6-19.8-1.9-66.1-45.3-80.9c-0.3-0.1-0.7-0.2-1-0.3 c-45.1-11.5-82.6-37.5-83-37.8c-6.1-4.3-14.5-2.8-18.8,3.3c-4.3,6.1-2.8,14.5,3.3,18.8c1.7,1.2,41.5,28.9,91.3,41.7 c23.3,8.3,25.9,33.2,26.6,56c0,0.9,0,1.7,0.1,2.5c0.1,9-0.5,22.9-2.1,30.9c-16.2,9.2-79.7,41-176.3,41 c-96.2,0-160.1-31.9-176.4-41.1c-1.6-8-2.3-21.9-2.1-30.9c0-0.8,0.1-1.6,0.1-2.5c0.7-22.8,3.3-47.7,26.6-56 c49.8-12.8,89.6-40.6,91.3-41.7c6.1-4.3,7.6-12.7,3.3-18.8c-4.3-6.1-12.7-7.6-18.8-3.3c-0.4,0.3-37.7,26.3-83,37.8 c-0.4,0.1-0.7,0.2-1,0.3c-43.4,14.9-44.7,61.2-45.3,80.9c0,0.9,0,1.7-0.1,2.5c0,0.1,0,0.2,0,0.3c-0.1,5.2-0.2,31.9,5.1,45.3 c1,2.6,2.8,4.8,5.2,6.3c3,2,74.9,47.8,195.2,47.8s192.2-45.9,195.2-47.8c2.3-1.5,4.2-3.7,5.2-6.3 C447,415.5,446.9,388.8,446.8,383.6z" );
        userImg.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                userViewModel.getTicketList();
            }
        });
        HBox userLabel = imgLabel(username);


        GridPane user = new GridPane();
        user.addColumn(2, userImg, userLabel);
        user.setPadding(new Insets(5));

        HBox newTicketImg = imgBox("M140.744,0C63.138,0,0,63.138,0,140.744s63.138,140.744,140.744,140.744s140.744-63.138,140.744-140.744 S218.351,0,140.744,0z M140.744,263.488C73.063,263.488,18,208.426,18,140.744S73.063,18,140.744,18 s122.744,55.063,122.744,122.744S208.425,263.488,140.744,263.488z M210.913,131.744h-61.168V70.576c0-4.971-4.029-9-9-9s-9,4.029-9,9v61.168H70.576c-4.971,0-9,4.029-9,9s4.029,9,9,9h61.168 v61.168c0,4.971,4.029,9,9,9s9-4.029,9-9v-61.168h61.168c4.971,0,9-4.029,9-9S215.883,131.744,210.913,131.744z");
        newTicketImg.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                userViewModel.addNewTicket();
            }
        });
        HBox newTicketLabel = imgLabel("New Ticket");

        GridPane newTicket = new GridPane();
        newTicket.addColumn(2, newTicketImg, newTicketLabel);
        newTicket.setPadding(new Insets(5));

        return new VBox(user, newTicket);
    }

    public VBox getMenu() {
        return menu;
    }

    private HBox imgBox(String svg){
        HBox imgBox = new HBox(svgShape(svg));
        imgBox.setPrefWidth(120);
        imgBox.setAlignment(Pos.CENTER);
        return imgBox;
    }


    private HBox imgLabel(String text){
        Label imgLabel = new Label(text);
        imgLabel.setFont(Font.font("System", 10));
        HBox labelBox = new HBox(imgLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setPrefWidth(120);

        return labelBox;
    }

    private Region svgShape(String svg){
        SVGPath svgPath = new SVGPath();
        String path = svg;
        svgPath.setContent(path);
        Region svgShape = new Region();
        svgShape.setShape(svgPath);
        svgShape.setPrefHeight(60);
        svgShape.setPrefWidth(60);
        svgShape.setStyle("-fx-background-color: rgba(0,0,0,0.61)");

        return svgShape;
    }
}
