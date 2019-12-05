DROP SCHEMA IF EXISTS "sep2" CASCADE;
CREATE SCHEMA "sep2";

SET SEARCH_PATH = "sep2";

DROP TABLE IF EXISTS account_client CASCADE ;
CREATE TABLE Account_Client (
                                Id serial,
                                Username varchar unique,
                                Password varchar,
                                Active boolean,
                                PRIMARY KEY (Id)
);
DROP TABLE IF EXISTS Account_User CASCADE ;
CREATE TABLE Account_User (
    PRIMARY KEY (Id)
) INHERITS (Account_Client);

insert into account_user (Username, Password, Active) VALUES ('user1', 'user', True);
insert into account_user (Username, Password, Active) VALUES ('user2', 'user', True);
insert into account_user (Username, Password, Active) VALUES ('user3', 'user', True);
insert into account_client (Username, Password, Active) VALUES ('user4', 'user', True);
select * from account_client;

-- This is only for use in case of id reset to 1 again...
ALTER SEQUENCE account_client_id_seq RESTART WITH 1;

-----------------------------------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS Ticket CASCADE;
CREATE TABLE Ticket (
                        Id_Ticket serial,
                        created_at timestamp default current_timestamp,
                        User_Id integer references Account_User(Id),
                        Subject varchar,
                        Description varchar,
                        Category varchar,
                        Location varchar,
                        Ticket_Status varchar,
                        PRIMARY KEY (Id_Ticket)
);

INSERT INTO Ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (1, 'Something broken', 'Hallo this is a test to make sure it works, broken chair',
        'Minor', 'ROOM 301A', 'OPEN');

SELECT * FROM Ticket;

-- This is only for use in case of id reset to 1 again...
ALTER SEQUENCE ticket_id_ticket_seq RESTART WITH 1;

/*
INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (3, 'Something more is broken', 'Very Important Hallo this is a test to make sure it works, broken chair','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (1, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');


SELECT * FROM sep2.ticket t
        INNER JOIN Account_Client c ON t.User_Id = c.id
        WHERE c.Username = 'user3'
        GROUP BY t.Id_Ticket, c.id;

SELECT * FROM sep2.ticket t INNER JOIN Account_Client c ON t.User_Id = c.id WHERE c.Username = 'user3' GROUP BY t.Id_Ticket, c.id;


INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (5, 'Toilet', 'there has been an incident','Minor', 'ROOM 301A', 'OPEN');

*/

