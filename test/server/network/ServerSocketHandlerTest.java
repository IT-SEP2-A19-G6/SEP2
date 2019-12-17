package server.network;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.exceptions.DataConnectionException;
import server.model.createticket.CreateTicketServerModelHandler;
import server.model.createticket.ICreateTicketServerModel;
import server.persistence.createticket.ICreateTicketDAO;
import shared.Ticket;
import testdummies.TestCreateTicketDAO;
import static org.junit.jupiter.api.Assertions.*;

class ServerSocketHandlerTest {
    private ICreateTicketDAO createTicketDAO;
    private int clientId;
    private String subject = "some subject";
    private String description = "some description";
    private String location = "VIA";
    private int branchId;

    @BeforeEach
    void setUp() {
        createTicketDAO = new TestCreateTicketDAO();
        ICreateTicketServerModel createTicketServerModel = new CreateTicketServerModelHandler(createTicketDAO);
    }

    @Test
    void create1TicketInBranch1OK(){
        branchId = 1;
        clientId = 1;
        String response = "";
        try {
            response = createTicketDAO.addTicket(new Ticket(clientId, subject, description, location, branchId));
        } catch (DataConnectionException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("OK", response);
    }

    @Test
    void create1TicketInBranch2OK(){
        branchId = 2;
        clientId = 2;
        String response = "";
        try {
            response = createTicketDAO.addTicket(new Ticket(clientId, subject, description, location, branchId));
        } catch (DataConnectionException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("OK", response);
    }

    @Test
    void createTicketInBranch1And2OK(){
        branchId = 1;
        clientId = 1;
        String response1 = "";
        try {
            response1 = createTicketDAO.addTicket(new Ticket(clientId, subject, description, location, branchId));
        } catch (DataConnectionException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("OK", response1);

        branchId = 2;
        clientId = 2;
        String response2 = "";
        try {
            response2 = createTicketDAO.addTicket(new Ticket(clientId, subject, description, location, branchId));
        } catch (DataConnectionException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("OK", response2);
    }


    @Test
    void failToCreate1TicketInNonExistingBranch(){
        branchId = 3;
        clientId = 1;
        String response = "";
        try {
            response = createTicketDAO.addTicket(new Ticket(clientId, subject, description, location, branchId));
        } catch (DataConnectionException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("Error ticket not saved.", response);
    }

    @Test
    void failToCreate1TicketFromNonExistingUser(){
        branchId = 1;
        clientId = 3;
        String response = "";
        try {
            response = createTicketDAO.addTicket(new Ticket(clientId, subject, description, location, branchId));
        } catch (DataConnectionException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("Error ticket not saved.", response);
    }




}