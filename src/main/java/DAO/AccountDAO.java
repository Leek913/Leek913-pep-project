
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO implements BaseRepository<Account, Integer>{

    @Override
    public List<Account> FindAll() throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        List<Account> Accounts = new ArrayList<>();
        try {
            String Sql = "SELECT * FROM account";
            Statement Statement = Connection.createStatement();
            ResultSet Results = Statement.executeQuery(Sql);
            while(Results.next()){
                Account Account = new Account(Results.getInt("account_id"), Results.getString("username"), Results.getString("password"));
                Accounts.add(Account);
            }
        } catch (SQLException E) {
            throw new DataException("Failed to find all accounts from the Database." , E);
        }
        return Accounts;
    }

    @Override
    public Account FindById(int Id) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        try {
            String Sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql);

            PreparedStatement.setInt(1, Id);
            ResultSet Results = PreparedStatement.executeQuery();
            if(Results.next()){
                return new Account(Results.getInt("account_id"), Results.getString("username"), Results.getString("password"));
            }
        } catch (SQLException E) {
            throw new DataException("Failed to find account from the Database." , E);
        }
        return null;
    }

    @Override
    public Account Add(Account Value) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        try {
            String Sql = "INSERT INTO account (username,password) VALUES (?,?)";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql, Statement.RETURN_GENERATED_KEYS);

            PreparedStatement.setString(1, Value.getUsername());
            PreparedStatement.setString(2, Value.getPassword());
            PreparedStatement.executeUpdate();
            ResultSet Results = PreparedStatement.getGeneratedKeys();
            if(Results.next()){
                int GeneratedKey = Results.getInt(1);
                return new Account(GeneratedKey, Value.getUsername(), Value.getPassword());
            }
        } catch (SQLException E) {
            throw new DataException("Failed to add account to the Database." , E);
        }
        return null;
    }

    @Override
    public Account Update(Account Value) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        try {
            String Sql = "UPDATE account SET username = ?, password = ? WHERE account_id = ?";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql);

            PreparedStatement.setString(1, Value.getUsername());
            PreparedStatement.setString(2, Value.getPassword());
            PreparedStatement.setInt(3, Value.getAccount_id());
            int Rows = PreparedStatement.executeUpdate();
            if(Rows == 0){
                return null;
            }
            
            return Value;

        } catch (SQLException E) {
            throw new DataException("Failed to update account." , E);
        }
    }

    @Override
    public Account Delete(Account Value) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        try {
            String RetrieveSql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement Statement = Connection.prepareStatement(RetrieveSql);
            Statement.setInt(1, Value.getAccount_id());
            ResultSet Results = Statement.executeQuery();

            if(!Results.next()){
                return null;
            }
            Account Account = new Account(
                Results.getInt("account_id"),
                Results.getString("username"),
                Results.getString("password")
                
            );

            String Sql = "DELETE FROM account WHERE account_id = ?";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql);
            PreparedStatement.setInt(1, Value.getAccount_id());
            PreparedStatement.executeUpdate();
            return Account;

        } catch (SQLException E) {
            throw new DataException("Failed to Delete message." , E);
        }
    }
}
