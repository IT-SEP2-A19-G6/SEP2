DROP SCHEMA IF EXISTS "sep2" CASCADE;
CREATE SCHEMA "sep2";

SET SEARCH_PATH = 'sep2';

CREATE TABLE Account_Client (
    Id serial,
    Username varchar unique,
    Password varchar,
    Active boolean,
    PRIMARY KEY (Id)
);

CREATE TABLE Account_User ( -- User is a reserved name, so this has been changed to account_user

) INHERITS (Account_Client);


insert into account_user (Username, Password, Active) VALUES ('user1', 'user', True);
insert into account_user (Username, Password, Active) VALUES ('user2', 'user', True);
insert into account_user (Username, Password, Active) VALUES ('user3', 'user', True);
select * from account_client;


CREATE TABLE ticket (
    Created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    Id_Ticket serial,
    User_Id integer references Account_User(Id),
    Subject varchar,
    Description varchar,
    Category varchar,
    Location varchar,
    Ticket_Status varchar,
    PRIMARY KEY (Id_Ticket)
);



INSERT INTO ticket (User_Id, Subject, Description, Category, Location, Ticket_Status)
VALUES (3, 'Something broken', 'Hallo this is a test to make sure it works, broken chair','Minor', 'ROOM 301A', 'OPEN');


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



