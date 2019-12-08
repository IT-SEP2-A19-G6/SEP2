package client.viewmodel.client.uielements;

import client.util.ClientProperties;
import client.viewmodel.client.ClientViewModel;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import java.util.ArrayList;

public class Menu {
    ClientViewModel clientViewModel;
    private final int menuWidth = 120;
    private final int picSize = 60;
    private final int dotSize = 7;
    private HBox branchDot;
    private HBox ticketDot;
    private HBox clientDot;

    public Menu(ClientViewModel clientViewModel){
        this.clientViewModel = clientViewModel;
    }

    private Region svgShape(String svg, int size){
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        Region svgShape = new Region();
        svgShape.setShape(svgPath);
        svgShape.setPrefHeight(size);
        svgShape.setPrefWidth(size);
        svgShape.setStyle("-fx-background-color: rgba(0,0,0,0.61)");

        return svgShape;
    }

    private HBox imgBox(int size, String svg){
        HBox imgBox = new HBox(svgShape(svg, size));
        //imgBox.setPrefWidth(menuWidth * 0.9);
        imgBox.setAlignment(Pos.CENTER);
        return imgBox;
    }

    public HBox addClientListItem(){
        HBox clientButton = imgBox(picSize, "M239.7,260.2c0.5,0,1,0,1.6,0c0.2,0,0.4,0,0.6,0c0.3,0,0.7,0,1,0c29.3-0.5,53-10.8,70.5-30.5 c38.5-43.4,32.1-117.8,31.4-124.9c-2.5-53.3-27.7-78.8-48.5-90.7C280.8,5.2,262.7,0.4,242.5,0h-0.7c-0.1,0-0.3,0-0.4,0h-0.6 c-11.1,0-32.9,1.8-53.8,13.7c-21,11.9-46.6,37.4-49.1,91.1c-0.7,7.1-7.1,81.5,31.4,124.9C186.7,249.4,210.4,259.7,239.7,260.2z M164.6,107.3c0-0.3,0.1-0.6,0.1-0.8c3.3-71.7,54.2-79.4,76-79.4h0.4c0.2,0,0.5,0,0.8,0c27,0.6,72.9,11.6,76,79.4 c0,0.3,0,0.6,0.1,0.8c0.1,0.7,7.1,68.7-24.7,104.5c-12.6,14.2-29.4,21.2-51.5,21.4c-0.2,0-0.3,0-0.5,0l0,0c-0.2,0-0.3,0-0.5,0 c-22-0.2-38.9-7.2-51.4-21.4C157.7,176.2,164.5,107.9,164.6,107.3z M446.8,383.6c0-0.1,0-0.2,0-0.3c0-0.8-0.1-1.6-0.1-2.5c-0.6-19.8-1.9-66.1-45.3-80.9c-0.3-0.1-0.7-0.2-1-0.3 c-45.1-11.5-82.6-37.5-83-37.8c-6.1-4.3-14.5-2.8-18.8,3.3c-4.3,6.1-2.8,14.5,3.3,18.8c1.7,1.2,41.5,28.9,91.3,41.7 c23.3,8.3,25.9,33.2,26.6,56c0,0.9,0,1.7,0.1,2.5c0.1,9-0.5,22.9-2.1,30.9c-16.2,9.2-79.7,41-176.3,41 c-96.2,0-160.1-31.9-176.4-41.1c-1.6-8-2.3-21.9-2.1-30.9c0-0.8,0.1-1.6,0.1-2.5c0.7-22.8,3.3-47.7,26.6-56 c49.8-12.8,89.6-40.6,91.3-41.7c6.1-4.3,7.6-12.7,3.3-18.8c-4.3-6.1-12.7-7.6-18.8-3.3c-0.4,0.3-37.7,26.3-83,37.8 c-0.4,0.1-0.7,0.2-1,0.3c-43.4,14.9-44.7,61.2-45.3,80.9c0,0.9,0,1.7-0.1,2.5c0,0.1,0,0.2,0,0.3c-0.1,5.2-0.2,31.9,5.1,45.3 c1,2.6,2.8,4.8,5.2,6.3c3,2,74.9,47.8,195.2,47.8s192.2-45.9,195.2-47.8c2.3-1.5,4.2-3.7,5.2-6.3 C447,415.5,446.9,388.8,446.8,383.6z" );
        clientDot = createDot();
        clientDot.setVisible(true);

        clientButton.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                clientViewModel.getTicketList();
                clientDot.setVisible(true);
                if (ticketDot != null){
                    ticketDot.setVisible(false);
                }
                if (branchDot != null){
                    branchDot.setVisible(false);
                }
            }
        });
        Label clientLabel = new Label(ClientProperties.getInstance().getClient().getUsername());
        VBox clientItem = new VBox(clientButton, clientLabel);
        clientItem.setAlignment(Pos.CENTER);

        HBox client = new HBox(clientItem, clientDot);
        client.setPrefWidth(menuWidth);
        client.setSpacing(10);
        client.setAlignment(Pos.CENTER);

        return client;
    }

    public HBox addNewTicketItem(){
        HBox ticketButton = imgBox(picSize, "M140.744,0C63.138,0,0,63.138,0,140.744s63.138,140.744,140.744,140.744s140.744-63.138,140.744-140.744 S218.351,0,140.744,0z M140.744,263.488C73.063,263.488,18,208.426,18,140.744S73.063,18,140.744,18 s122.744,55.063,122.744,122.744S208.425,263.488,140.744,263.488z M210.913,131.744h-61.168V70.576c0-4.971-4.029-9-9-9s-9,4.029-9,9v61.168H70.576c-4.971,0-9,4.029-9,9s4.029,9,9,9h61.168 v61.168c0,4.971,4.029,9,9,9s9-4.029,9-9v-61.168h61.168c4.971,0,9-4.029,9-9S215.883,131.744,210.913,131.744z");
        ticketDot = createDot();
        ticketDot.setVisible(false);

        ticketButton.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                clientViewModel.createNewTicket();
                ticketDot.setVisible(true);
                if (branchDot != null){
                    branchDot.setVisible(false);
                }
                if (clientDot != null){
                    clientDot.setVisible(false);
                }
            }
        });
        Label ticketLabel = new Label("New ticket");
        VBox ticketItem = new VBox(ticketButton, ticketLabel);
        ticketItem.setAlignment(Pos.CENTER);

        HBox ticket = new HBox(ticketItem, ticketDot);
        ticket.setPrefWidth(menuWidth);
        ticket.setSpacing(10);
        ticket.setAlignment(Pos.CENTER);

        return ticket;
    }

    public HBox addBranchItem(){
        HBox branchButton = imgBox(picSize, "M506.555,208.064L263.859,30.367c-4.68-3.426-11.038-3.426-15.716,0L5.445,208.064 c-5.928,4.341-7.216,12.665-2.875,18.593s12.666,7.214,18.593,2.875L256,57.588l234.837,171.943c2.368,1.735,5.12,2.57,7.848,2.57 c4.096,0,8.138-1.885,10.744-5.445C513.771,220.729,512.483,212.405,506.555,208.064z M442.246,232.543c-7.346,0-13.303,5.956-13.303,13.303v211.749H322.521V342.009c0-36.68-29.842-66.52-66.52-66.52 s-66.52,29.842-66.52,66.52v115.587H83.058V245.847c0-7.347-5.957-13.303-13.303-13.303s-13.303,5.956-13.303,13.303v225.053 c0,7.347,5.957,13.303,13.303,13.303h133.029c6.996,0,12.721-5.405,13.251-12.267c0.032-0.311,0.052-0.651,0.052-1.036v-128.89 c0-22.009,17.905-39.914,39.914-39.914s39.914,17.906,39.914,39.914v128.89c0,0.383,0.02,0.717,0.052,1.024 c0.524,6.867,6.251,12.279,13.251,12.279h133.029c7.347,0,13.303-5.956,13.303-13.303V245.847 C455.549,238.499,449.593,232.543,442.246,232.543z");
        branchDot = createDot();
        branchDot.setVisible(false);

        branchButton.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                clientViewModel.getBranchList();
                branchDot.setVisible(true);
                if (ticketDot != null){
                    ticketDot.setVisible(false);
                }
                if (clientDot != null){
                    clientDot.setVisible(false);
                }
            }
        });

        Label branchLabel = new Label("Branch");
        VBox branchItem = new VBox(branchButton, branchLabel);
        branchItem.setAlignment(Pos.CENTER);

        HBox branch = new HBox(branchItem, branchDot);
        branch.setPrefWidth(menuWidth);
        branch.setSpacing(10);
        branch.setAlignment(Pos.CENTER);

        return branch;
    }

    public VBox createMenu(ArrayList<Node> nodes){
        VBox menu = new VBox();
        for (Node node : nodes){
            menu.getChildren().add(node);
        }
        menu.setSpacing(10);
        return menu;
    }

    private HBox createDot(){
        String svg ="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z";
        HBox dotBox = new HBox(svgShape(svg, dotSize));
        dotBox.setMaxHeight(dotSize);
        return dotBox;
    }






}
