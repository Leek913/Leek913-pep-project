
package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.util.List;
import java.sql.*;

public class MessageDAO implements BaseRepository<Message, String>{

    @Override
    public List<Message> FindAll() throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'FindAll'");
    }

    @Override
    public Message FindById(int Id) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'FindById'");
    }

    @Override
    public Message Add(Message Value) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Add'");
    }

    @Override
    public boolean Update(Message Value) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }

    @Override
    public boolean Delete(Message Value) throws DataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }
    
}
