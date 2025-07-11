
package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.DataException;
import Model.Account;

public class AccountService {
    private AccountDAO DAO;

    public AccountService(){
        DAO = new AccountDAO();
    }

    public AccountService(AccountDAO DAO){
        this.DAO = DAO;
    }

    public List<Account> FindAll() throws DataException{
        return DAO.FindAll();
    }

    public Account FindById(int Id) throws DataException{
        return DAO.FindById(Id);
    }

    public Account Add(Account Value) throws DataException{
        return DAO.Add(Value);
    }

    public Account Update(Account Value) throws DataException{
        return DAO.Update(Value);
    }

    public Account Delete(Account Value) throws DataException{
        return DAO.Delete(Value);
    }
}
