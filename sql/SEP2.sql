DROP SCHEMA IF EXISTS "sep2" CASCADE;
CREATE SCHEMA "sep2";

SET SEARCH_PATH = "sep2";

DROP TABLE IF EXISTS account_client CASCADE;
CREATE TABLE Account_Client (
                                Id serial,
                                Username varchar unique,
                                Password varchar,
                                Active boolean,
                                Type varchar check((Type = 'user') or (Type = 'branch_member') or (Type = 'administrator')),
                                PRIMARY KEY (Id)
);

DROP TABLE IF EXISTS Account_User CASCADE ;
CREATE TABLE Account_User (
    PRIMARY KEY (Id)
) INHERITS (Account_Client);

insert into account_user (Username, Password, Active, Type) VALUES ('user1', 'user', True, 'user');
insert into account_user (Username, Password, Active, Type) VALUES ('user2', 'user', True, 'user');
insert into account_user (Username, Password, Active, Type) VALUES ('user3', 'user', True, 'user');
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
                        Id_Branch integer references Branch(id_branch),
                        Assignee integer references Branch_Member(Id_BranchMember),
                        PRIMARY KEY (Id_Ticket)
);

INSERT INTO Ticket (User_Id, Subject, Description, Category, Location, Ticket_Status, Id_Branch, Assignee)
VALUES (1, 'Something broken', 'Hallo this is a test to make sure it works, broken chair',
        'Minor', 'ROOM 301A', 'OPEN', 1, 1);

SELECT * FROM Ticket;

-- This is only for use in case of id reset to 1 again...
ALTER SEQUENCE ticket_id_ticket_seq RESTART WITH 1;

-----------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS Branch CASCADE;
CREATE TABLE Branch (
                        Id_Branch INTEGER not null,
                        branchName varchar,
                        primary key (Id_Branch)
);

INSERT INTO Branch (Id_Branch, branchName) VALUES ('1', 'IT');

SELECT * FROM branch;

-----------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS Branch_Member CASCADE;
CREATE TABLE Branch_Member (
                               Id_BranchMember serial,
                               Id_Branch INTEGER references Branch(Id_Branch),
                               Username varchar unique,
                               Password varchar,
                               PRIMARY KEY (Id_BranchMember)
);

INSERT INTO Branch_Member (Id_Branch, Username, Password) VALUES
(1, 'David', 'dat');

SELECT * FROM Branch_Member;

-- This is only for use in case of id reset to 1 again...
ALTER SEQUENCE branch_member_id_branchmember_seq RESTART WITH 1;

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

