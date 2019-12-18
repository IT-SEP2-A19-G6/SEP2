/*
    IT-SEP2-A19-G6

    The following SQL file contains all nessesary tables to
    make the system run. Under SEP2_UserInstallation.sql
    is it possible to find related queries to create
    branch members and branches.


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