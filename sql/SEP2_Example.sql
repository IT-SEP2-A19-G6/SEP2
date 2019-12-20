/*
    IT-SEP2-A19-G6

    The following example SQL is an example of how the
    database can be build with related branches, users and predefined ticket.
    This sql can be used to showcase the system.


 */


-- 1. Create schema
CREATE SCHEMA "sep2";
-- 2. Set search Path
SET SEARCH_PATH = "sep2";
-- 3. Create tables
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

-- 4. insert users
insert into account_client (Username, Password) VALUES ('user1', 'user');
insert into account_client (Username, Password) VALUES ('user2', 'user');
insert into account_client (Username, Password) VALUES ('user3', 'user');
insert into account_client (Username, Password) VALUES ('user4', 'user');
insert into account_client (Username, Password) VALUES ('user5', 'user');
insert into account_client (Username, Password) VALUES ('user6', 'user');

-- 5. insert branches
INSERT INTO Branch (branchName) VALUES ('IT');
INSERT INTO Branch (branchName) VALUES ('Facility Management');

-- 6. insert branch members into IT
insert into account_client (Username, Password, branchId) VALUES ('it1', 'member', 1);
insert into account_client (Username, Password, branchId) VALUES ('it2', 'member', 1);
insert into account_client (Username, Password, branchId) VALUES ('it3', 'member', 1);
-- 7. insert branch members into facility management
insert into account_client (Username, Password, branchId) VALUES ('fm1', 'member', 2);
insert into account_client (Username, Password, branchId) VALUES ('fm2', 'member', 2);
insert into account_client (Username, Password, branchId) VALUES ('fm3', 'member', 2);

-- 8. insert tickets
INSERT INTO Ticket (client_Id, Subject, Description, Location, , Ticket_Status, Id_Branch)
VALUES (1, 'Toilet incident', 'All out of paper....',
         'Mens room', 'OPEN', 2);

INSERT INTO Ticket (client_Id, Subject, Description, Location, Ticket_Status, Id_Branch)
VALUES (1, 'Teams inaccessible', 'Can´t log in to MS Teams', 'ROOM 301A', 'OPEN', 1);