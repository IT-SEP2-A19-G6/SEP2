/*
    IT-SEP2-A19-G6

    The following example SQL is used as a template
    to create branches, branch members and if nessesary
    users for the system.
    A showcase of how it could look can be seen in SEP2_Example.sql

    NOTE: Make sure the SEP2_Installation.sql have been run before
    adding users, branches or branch members to the database.

 */


SET SEARCH_PATH = 'sep2';

-- To add users, use the following query structure:
insert into account_client (Username, Password) VALUES ('usernameHere', 'passwordHere');


-- to create new branches, use the following query structure:
INSERT INTO Branch (branchName) VALUES ('BranchName');


-- to add branch members into branches, use the following query structure (NOTE: Remember to query branch to get the correct branch id)
select * from branch;
insert into account_client (Username, Password, branchId) VALUES ('branchName', 'password', 1); -- Username: branchName, --Password: password, -- Branch: first branch

