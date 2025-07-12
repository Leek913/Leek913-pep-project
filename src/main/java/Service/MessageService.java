
package Service;

import java.util.List;

import DAO.MessageDAO;
import DAO.DataException;
import Model.Message;

public class MessageService implements BaseService <Message, String> {
     private MessageDAO DAO;

    public MessageService(){
        DAO = new MessageDAO();
    }

    public MessageService(MessageDAO DAO){
        this.DAO = DAO;
    }
    
    @Override
    public List<Message> FindAll() throws DataException {
        return DAO.FindAll();
    }

    @Override
    public Message FindById(int Id) throws DataException {
        return DAO.FindById(Id);
    }

    public List<Message> FindByUser(int Id) throws DataException {
        return DAO.FindByUser(Id);
    }

    @Override
    public Message Add(Message Value) throws DataException {
        if(Value.getMessage_text().isBlank() || Value.getMessage_text().length() > 255 || Value.getMessage_text() == null ) {
            return null;
        }
        return DAO.Add(Value);
    }

    @Override
    public Message Update(Message Value) throws DataException {
        if(Value.getMessage_text().strip().isBlank() || Value.getMessage_text().length() > 255){
            return null;
        }
        return DAO.Update(Value);
    }

    @Override
    public Message Delete(Message Value) throws DataException {
        return DAO.Delete(Value);
    }
}
