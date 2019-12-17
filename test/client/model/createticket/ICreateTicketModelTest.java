package client.model.createticket;

import client.network.createticket.CreateTicketClientHandler;
import client.network.createticket.ICreateTicketClient;
import client.util.ClientProperties;
import client.viewmodel.createticket.CreateTicketViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Branch;
import shared.clients.ClientType;
import shared.clients.User;
import testdummies.TestSocketHandler;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ICreateTicketModelTest {

    CreateTicketModelHandler model;
    CreateTicketViewModel vm;
    TestSocketHandler csh;

    SimpleStringProperty currentCategory;
    SimpleStringProperty subjectTextField;
    SimpleStringProperty descriptionTextArea;
    SimpleStringProperty locationTextField;
    SimpleStringProperty infoLabel;

    void setupJavaFXAndInitialise() {
        new JFXPanel();
        ClientProperties.getInstance().setClient(new User(0, "Test user", ClientType.USER));
    }

    @BeforeEach
    void setUp() {

        setupJavaFXAndInitialise();

        csh = new TestSocketHandler();
        ICreateTicketClient client = new CreateTicketClientHandler(csh);
        model = new CreateTicketModelHandler(client);
        vm = new CreateTicketViewModel(model);


        currentCategory = new SimpleStringProperty();
        subjectTextField = new SimpleStringProperty();
        descriptionTextArea = new SimpleStringProperty();
        locationTextField = new SimpleStringProperty();
        infoLabel = new SimpleStringProperty();


        currentCategory.bindBidirectional(vm.currentCategory());
        subjectTextField.bindBidirectional(vm.subjectProperty());
        descriptionTextArea.bindBidirectional(vm.descriptionProperty());
        locationTextField.bindBidirectional(vm.locationProperty());
        infoLabel.bindBidirectional(vm.ticketResultProperty());

        csh.fireBranchResponse(new ArrayList<>(Arrays.asList(new Branch(0, "Test Branch"), new Branch(1, "Test Branch 2"))));

    }

    @Test
    void submittedTicket() {

        // arrange
        subjectTextField.setValue("Test Subject");
        descriptionTextArea.setValue("Test Description");
        locationTextField.setValue("Test Location");

        // act
        vm.submitTicket();

        // assert
        assertEquals("Test Subject", csh.sentTicket().getSubject());
        assertEquals("Test Description", csh.sentTicket().getDescription());
        assertEquals("Test Location", csh.sentTicket().getLocation());
    }


    @Test
    void submitTicketReturnsOK() throws InterruptedException {
        // arrange
        subjectTextField.setValue("Test Subject");
        currentCategory.setValue("Test Branch");
        descriptionTextArea.setValue("Test description");
        locationTextField.setValue("Test location");
        // act
        vm.submitTicket();

        long startTime = System.currentTimeMillis(); //fetch starting time
        while(vm.ticketResultProperty().getValue() == null||(System.currentTimeMillis()-startTime)<3000)
        {
            // sleep to make sure observer gets through
            Thread.sleep(100);
        }
        // assert
        assertEquals("OK", vm.ticketResultProperty().getValue());

    }




    @Test
    void getBranches() {

        assertEquals("Choose a category", vm.getCategories().get(0));
        assertEquals("Test Branch", vm.getCategories().get(1));
        assertEquals("Test Branch 2", vm.getCategories().get(2));
        assertEquals(3, vm.getCategories().size());


        csh.fireBranchResponse(new ArrayList<>());
        assertEquals(1, vm.getCategories().size());
        assertEquals("Choose a category", vm.getCategories().get(0));

    }

    @Test
    void clearFields() {
        subjectTextField.setValue("Test Subject");
        currentCategory.setValue("Test Branch");
        descriptionTextArea.setValue("Test description");
        locationTextField.setValue("Test location");
        vm.clearFields();
        assertEquals("", vm.subjectProperty().getValue());
        assertEquals("Choose a category", vm.currentCategory().getValue());
        assertEquals("", vm.descriptionProperty().getValue());
        assertEquals("", vm.locationProperty().getValue());
    }
}