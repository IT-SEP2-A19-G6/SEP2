package testdummies;

import server.exceptions.DataConnectionException;
import server.persistence.login.ILoginDAO;

import shared.clients.BranchMember;
import shared.clients.Client;
import shared.clients.ClientType;
import shared.clients.User;

public class TestLoginDAO implements ILoginDAO {

    private User correctDummyUser;
    private User correctUser;
    private User correctDummyBranchMember;
    private BranchMember correctBranchMember;

    public TestLoginDAO(){
        correctDummyUser = new User("correctUser", "correctPassword");
        correctDummyBranchMember = new User("correctBranchMember", "correctPassword");
        correctUser = new User(0, correctDummyUser.getUsername(), ClientType.USER);
        correctBranchMember = new BranchMember(1, correctDummyBranchMember.getUsername(), ClientType.BRANCH_MEMBER, 1);
    }

    @Override
    public Client validateLogin(User user) throws DataConnectionException {
        if (user.getUsername().equals(correctDummyUser.getUsername())){
             if(user.getPassword().equals(correctDummyUser.getPassword())){
                 return correctUser;
            }
        }else if(user.getUsername().equals(correctDummyBranchMember.getUsername())){
            if (user.getPassword().equals(correctDummyBranchMember.getPassword())){
                return correctBranchMember;
            }
        }
        return null;
    }
}
