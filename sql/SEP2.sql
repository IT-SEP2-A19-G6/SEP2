DROP SCHEMA IF EXISTS "sep2" CASCADE;
CREATE SCHEMA "sep2";

SET SEARCH_PATH = "sep2";

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

INSERT INTO Ticket (User_Id, Subject, Description, Category, Location, Ticket_Status) 
VALUES (1, 'Something broken', 'Hallo this is a test to make sure it works, broken chair',
                                                                       'Minor', 'ROOM 301A', 'OPEN');

SELECT * FROM Ticket;

-- This is only for use in case of id reset to 1 again...
ALTER SEQUENCE ticket_id_ticket_seq RESTART WITH 1;




