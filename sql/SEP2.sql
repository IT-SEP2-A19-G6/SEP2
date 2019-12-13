DROP SCHEMA IF EXISTS "sep2" CASCADE;
CREATE SCHEMA "sep2";

SET SEARCH_PATH = "sep2";

DROP TABLE IF EXISTS account_client CASCADE;
CREATE TABLE Account_Client (
    Id serial,
    Username varchar unique,
    Password varchar,
    Active boolean,
    Type varchar check((Type = 'USER') or (Type = 'BRANCH_MEMBER') or (Type = 'ADMINISTRATOR')),
    PRIMARY KEY (Id)
);


DROP TABLE IF EXISTS Account_User CASCADE ;
CREATE TABLE Account_User (
    PRIMARY KEY (Id)
) INHERITS (Account_Client);

insert into account_user (Username, Password, Active, Type) VALUES ('user1', 'user', True, 'USER');
insert into account_user (Username, Password, Active, Type) VALUES ('user2', 'user', True, 'USER');
insert into account_user (Username, Password, Active, Type) VALUES ('user3', 'user', True, 'USER');
insert into account_client (Username, Password, Active, Type) VALUES ('user4', 'user', True);

-- This is only for use in case of id reset to 1 again...
-- ALTER SEQUENCE account_client_id_seq RESTART WITH 1;
-----------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS Branch CASCADE;
CREATE TABLE Branch (
    Id serial,
    branchName varchar,
    primary key (Id)
);

INSERT INTO Branch (branchName) VALUES ('IT');
INSERT INTO Branch (branchName) VALUES ('Facility Management');


-----------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS Branch_Member CASCADE;
CREATE TABLE account_branch_member (
    Id_Branch INTEGER references Branch(Id),
    PRIMARY KEY (Id)
) INHERITS (Account_Client);

insert into account_branch_member (Username, Password, Active, Type, id_branch) VALUES ('IT1', 'member', True, 'BRANCH_MEMBER', 1);
insert into account_branch_member (Username, Password, Active, Type, id_branch) VALUES ('FM1', 'member', True, 'BRANCH_MEMBER', 2);

SELECT * FROM account_branch_member;

-----------------------------------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS Ticket CASCADE;
CREATE TABLE Ticket (
    Id serial,
    created_at timestamp default current_timestamp,
    user_Id integer references Account_user(Id),
    Subject varchar,
    Description varchar,
    Category varchar,
    Location varchar,
    Ticket_Status varchar,
    Id_Branch integer references Branch(id),
    Assignee integer references account_branch_member(Id),
    PRIMARY KEY (Id)
);

INSERT INTO Ticket (user_Id, Subject, Description, Category, Location, Ticket_Status, Id_Branch, Assignee)
VALUES (1, 'Something broken', 'Hallo this is a test to make sure it works, broken chair',
        'Minor', 'ROOM 301A', 'OPEN', 1, 4);

INSERT INTO Ticket (user_Id, Subject, Description, Category, Location, Ticket_Status, Id_Branch, Assignee)
VALUES (1, 'Something broken', 'Hallo this is a test to make sure it works, broken chair',
        'Minor', 'ROOM 301A', 'OPEN', 2, 5);


-- This is only for use in case of id reset to 1 again...
--ALTER SEQUENCE ticket_id_ticket_seq RESTART WITH 1;



