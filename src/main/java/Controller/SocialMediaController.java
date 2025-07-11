package Controller;

import java.util.List;
import java.util.Objects;

import DAO.DataException;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
   
    AccountService AccountService;
    MessageService MessageService;


    public SocialMediaController(){
        this.AccountService = new AccountService();
        this.MessageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postNewAccountHandler);
        app.get("/login", this::getAccountLoginHandler);
        app.post("/messages", this::postNewMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::patchUpdateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByUserHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postNewAccountHandler(Context Context) throws JsonProcessingException, DataException {
        ObjectMapper Mapper = new ObjectMapper();
        Account Account = Mapper.readValue(Context.body(), Account.class);
        Account AddedAccount = AccountService.Add(Account);
        if(AddedAccount != null){
            Context.json(Mapper.writeValueAsString(AddedAccount));
        } else{
            Context.status(400);
        }
    }

    private void getAccountLoginHandler(Context Context){
        ///Need to ask about this.
    }

    private void postNewMessageHandler(Context Context) throws JsonProcessingException, DataException {
        ObjectMapper Mapper = new ObjectMapper();
        Message Message = Mapper.readValue(Context.body(), Message.class);
        Message AddedMessage = MessageService.Add(Message);
        if(AddedMessage != null){
            Context.json(Mapper.writeValueAsString(AddedMessage));
        } else{
            Context.status(400);
        }
    }

    private void getAllMessagesHandler(Context Context) throws JsonProcessingException, DataException {
        List<Message> Messages = MessageService.FindAll();
        Context.json(Messages);
    }

    private void getMessageByIdHandler(Context Context) throws DataException{
        try{
            int Id = Integer.parseInt(Objects.requireNonNull(Context.pathParam("message_id")));
            Message Message = MessageService.FindById(Id);
            if(Message != null){
                Context.json(Message);
            } else {
                Context.json("");
            }
        } catch(NumberFormatException E){
            
        }
    }

    private void deleteMessageHandler(Context Context) {

    }

    private void patchUpdateMessageHandler(Context Context) {

    }

    private void getMessagesByUserHandler(Context Context) {
        
    }



}