
package Service;

import java.util.List;

import DAO.MessageDAO;
import DAO.DataException;
import Model.Message;

public class MessageService {
     private MessageDAO DAO;

    public MessageService(){
        DAO = new MessageDAO();
    }

    public MessageService(MessageDAO DAO){
        this.DAO = DAO;
    }

    public List<Message> FindAll() throws DataException{
        return DAO.FindAll();
    }

    public Message FindById(int Id) throws DataException{
        return DAO.FindById(Id);
    }

    public Message Add(Message Value) throws DataException{
        return DAO.Add(Value);
    }

    public boolean Update(Message Value) throws DataException{
        return DAO.Update(Value);
    }

    public boolean Delete(Message Value) throws DataException{
        return DAO.Delete(Value);
    }
}
