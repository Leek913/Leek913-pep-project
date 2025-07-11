
package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MessageDAO implements BaseRepository<Message, String>{

    @Override
    public List<Message> FindAll() throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        List<Message> Messages = new ArrayList<>();
        try {
            String Sql = "SELECT * FROM message";
            Statement Statement = Connection.createStatement();
            ResultSet Results = Statement.executeQuery(Sql);
            while(Results.next()){
                Message Message = new Message(Results.getInt("message_id"), Results.getInt("posted_by"), Results.getString("message_text"), Results.getLong("time_posted_epoch"));
                Messages.add(Message);
            }
        } catch (SQLException E) {
            throw new DataException("Failed to find all messages from the Database." , E);
        }
        return Messages;
    }

    @Override
    public Message FindById(int Id) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        try {
            String Sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql);

            PreparedStatement.setInt(1, Id);
            ResultSet Results = PreparedStatement.executeQuery();
            if(Results.next()){
                return new Message(Results.getInt("message_id"), Results.getInt("posted_by"), Results.getString("message_text"), Results.getLong("time_posted_epoch"));   
            }
        } catch (SQLException E) {
            throw new DataException("Failed to find message from the Database." , E);
        }
        return null;
    }

    public List<Message> FindByUser(int Id) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        List<Message> Messages = new ArrayList<>();
        try {
            String Sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql);

            PreparedStatement.setInt(1, Id);
            ResultSet Results = PreparedStatement.executeQuery();
            while(Results.next()){
                Message Message = new Message(Results.getInt("message_id"), Results.getInt("posted_by"), Results.getString("message_text"), Results.getLong("time_posted_epoch"));
                Messages.add(Message);
            }
        } catch (SQLException E) {
            throw new DataException("Failed to find message from the Database." , E);
        }
        return Messages;
    }

    @Override
    public Message Add(Message Value) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        try {
            String Sql = "INSERT INTO message (posted_by,message_text,time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql, Statement.RETURN_GENERATED_KEYS);

            PreparedStatement.setInt(1, Value.getPosted_by());
            PreparedStatement.setString(2, Value.getMessage_text());
            PreparedStatement.setLong(3, Value.getTime_posted_epoch());
            PreparedStatement.executeUpdate();
            ResultSet Results = PreparedStatement.getGeneratedKeys();
            if(Results.next()){
                int GeneratedKey = Results.getInt(1);
                return new Message(GeneratedKey, Value.getPosted_by(), Value.getMessage_text(), Value.getTime_posted_epoch());
            }
        } catch (SQLException E) {
            throw new DataException("Failed to add message to the Database." , E);
        }
        return null;
    }

    @Override
    public Message Update(Message Value) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        try {
            String Sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql);

            PreparedStatement.setString(1, Value.getMessage_text());
            PreparedStatement.setInt(2, Value.getMessage_id());
            int Rows = PreparedStatement.executeUpdate();
            if(Rows == 0){
                return null;             
            }
            return Value;

        } catch (SQLException E) {
            throw new DataException("Failed to update message." , E);
        }
    }

    @Override
    public Message Delete(Message Value) throws DataException {
        Connection Connection = ConnectionUtil.getConnection();
        try {
            String RetrieveSql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement Statement = Connection.prepareStatement(RetrieveSql);
            Statement.setInt(1, Value.getMessage_id());
            ResultSet Results = Statement.executeQuery();

            if(!Results.next()){
                return null;
            }
            Message Message = new Message(
                Results.getInt("message_id"),
                Results.getInt("posted_by"),
                Results.getString("message_text"),
                Results.getLong("time_posted_epoch")
            );

            String Sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement PreparedStatement = Connection.prepareStatement(Sql);
            PreparedStatement.setInt(1, Value.getMessage_id());
            PreparedStatement.executeUpdate();
            return Message;

        } catch (SQLException E) {
            throw new DataException("Failed to Delete message." , E);
        }
    }
}
