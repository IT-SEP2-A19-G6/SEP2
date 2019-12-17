DROP SCHEMA IF EXISTS "sep2" CASCADE;
CREATE SCHEMA "sep2";

SET SEARCH_PATH = "sep2";


DROP TABLE IF EXISTS account_client CASCADE;
CREATE TABLE Account_Client (
    Id serial,
    Username varchar unique,
    Password varchar,
    branchId int references branch(id),
    PRIMARY KEY (Id)
);


DROP TABLE IF EXISTS branch CASCADE;
CREATE TABLE branch (
    Id serial,
    branchName varchar,
    primary key (Id)
);


DROP TABLE IF EXISTS Ticket CASCADE;
CREATE TABLE Ticket (
    Id serial,
    created_at timestamp default current_timestamp,
    client_Id integer references Account_Client(Id),
    Subject varchar,
    Description varchar,
    Location varchar,
    Ticket_Status varchar,
    Id_Branch integer references Branch(id),
    Assignee integer references Account_Client(id),
    PRIMARY KEY (Id)
);

DROP TABLE IF EXISTS Reply CASCADE;
CREATE TABLE  Reply (
    Reply_Id serial,
    Ticket_Id integer references Ticket(Id),
    tStamp timestamp default current_timestamp,
    client_Id integer,
    message varchar,
    primary key (Reply_Id),
    foreign key (client_Id) references Account_Client(id)
);



select * from Account_Client
where Username = 'user1';




--TODO remove types and have only USER and add trigger if the client id is in branch_members, then type = 'BRANCH_MEMBER'
insert into account_client (Username, Password) VALUES ('user1', 'user');
insert into account_client (Username, Password, branchId) VALUES ('fm2', 'user', 1);
insert into account_client (Username, Password) VALUES ('user3', 'user');
insert into account_client (Username, Password) VALUES ('user4', 'user');
insert into account_client (Username, Password) VALUES ('user5', 'user');
insert into account_client (Username, Password) VALUES ('user6', 'user');
insert into account_client (Username, Password) VALUES ('user7', 'user');
insert into account_client (Username, Password, branchId) VALUES ('it2', 'member', 1);
select * from Account_Client;
-- This is only for use in case of id reset to 1 again...
-- ALTER SEQUENCE account_client_id_seq RESTART WITH 1;
-----------------------------------------------------------------------------------------------------------------------
INSERT INTO Branch (branchName) VALUES ('IT');
INSERT INTO Branch (branchName) VALUES ('Facility Management');

-----------------------------------------------------------------------------------------------------------------------
-- insert into branch_members (Id_client, Id_Branch) VALUES ( 7, 1);
-- insert into branch_members (Id_client, Id_Branch) VALUES ( 8, 2);
-- insert into branch_members (Id_client, Id_Branch) VALUES ( 9, 2);
-- SELECT * FROM branch_members;
-----------------------------------------------------------------------------------------------------------------------


INSERT INTO Ticket (client_Id, Subject, Description, Location, Id_Branch)
VALUES (1, 'Something broken', 'Hallo this is a test to make sure it works, broken chair',
         'ROOM 301A', 1);


INSERT INTO Ticket (client_Id, Subject, Description, Location, Ticket_Status, Id_Branch)
VALUES (1, 'Something broken', 'Hallo this is a test to make sure it works, broken chair',
         'ROOM 301A', 'OPEN', 1);


INSERT INTO Ticket (client_Id, Subject, Description, Location, Ticket_Status, Id_Branch, assignee)
VALUES (1, 'Something broken', 'Hallo this is a test to make sure it works, broken chair',
         'ROOM 301A', 'OPEN', 1, 3);

select * from Ticket;






SELECT t.id AS ticketId, created_at, Subject, Description, Location, Ticket_Status, c.username AS creator, branchName, a.Username AS assignee FROM ticket t
INNER JOIN Account_Client c ON t.client_Id = c.Id
INNER JOIN branch b ON t.Id_Branch = b.Id
LEFT JOIN  account_client a ON t.assignee = a.Id
WHERE c.id = '1'
ORDER BY created_at DESC;

SELECT t.id AS ticketId, created_at, Subject, Description, Location, Ticket_Status, c.username AS creator, branchName, a.Username AS assignee FROM ticket t
INNER JOIN Account_Client a ON t.assignee = a.Id
INNER JOIN branch b ON t.Id_Branch = b.Id
LEFT JOIN  account_client c ON t.client_Id = c.Id
WHERE a.id = '3';

SELECT t.id AS ticketId, created_at, Subject, Description, Location, Ticket_Status, c.username AS creator, branchName, a.Username AS assignee  FROM Ticket t
LEFT JOIN Account_Client a ON t.Assignee = a.Id
INNER JOIN Account_Client c ON t.client_Id = c.id
INNER JOIN branch b ON t.Id_Branch = b.Id
    WHERE t.id_branch = 1;

-- This is only for use in case of id reset to 1 again...
--ALTER SEQUENCE ticket_id_ticket_seq RESTART WITH 1;
-----------------------------------------------------------------------------------------------------------------------

INSERT INTO Reply ( Ticket_Id, client_Id, message)
VALUES ( 2, 2, 'I will figure it out');

INSERT INTO Reply ( Ticket_Id, client_Id, message)
values ( 2, 1, 'Sounds good');

SELECT * FROM Reply;

SELECT Ticket_Id AS ticketId, tStamp AS timestamp, c.Username AS replier, message FROM Reply
INNER JOIN Ticket T on Reply.Ticket_Id = T.Id
INNER JOIN Account_Client c on Reply.client_Id = c.Id
where t.id = 2
ORDER BY timestamp DESC;
