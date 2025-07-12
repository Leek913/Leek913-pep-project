
package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.DataException;
import Model.Account;

public class AccountService implements BaseService <Account, Integer> {
    private AccountDAO DAO;

    public AccountService(){
        DAO = new AccountDAO();
    }

    public AccountService(AccountDAO DAO){
        this.DAO = DAO;
    }

    @Override
    public List<Account> FindAll() throws DataException {
        return DAO.FindAll();
    }

    @Override
    public Account FindById(int Id) throws DataException {
        return DAO.FindById(Id);
    }

    public Account FindByUser(String Username) throws DataException {
        return DAO.FindByUser(Username);
    }

    @Override
    public Account Add(Account Value) throws DataException {
        if(Value.getUsername().strip().isBlank() || Value.getPassword().strip().isBlank() || Value.getPassword().length() < 4 || DAO.FindByUser(Value.getUsername()) != null){
            return null;
        }
        return DAO.Add(Value);
    }

    @Override
    public Account Update(Account Value) throws DataException {
        return DAO.Update(Value);
    }

    @Override
    public Account Delete(Account Value) throws DataException {
        return DAO.Delete(Value);
    }

    public Account Login(Account Login) throws DataException {
        Account Account = DAO.FindByUser(Login.getUsername());
        if(Account != null && Account.getPassword().equals(Login.getPassword())){
            return Account;
        }
        return null;
    }
}
