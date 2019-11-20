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




