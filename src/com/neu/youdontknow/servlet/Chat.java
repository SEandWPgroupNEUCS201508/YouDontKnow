package com.neu.youdontknow.servlet;

import com.neu.youdontknow.models.Message;
import com.neu.youdontknow.models.User;
import com.google.gson.Gson;
import com.neu.youdontknow.service.GetHttpSessionConfigurator;
import com.neu.youdontknow.service.MessageService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
public class Chat {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static HashMap<String, Chat> connections = new HashMap<String, Chat>();

    private String userID;
    private Session session;

    public Session getSession() {
        return session;
    }

    public String getUserID() {
        return userID;
    }

    /**
     *
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        User user = (User)((HttpSession)(config.getUserProperties().get("httpSession"))).getAttribute("user");
        if(user != null){
            this.userID = (new Integer(user.getId())).toString();
            this.session = session;
            connections.put(this.userID, this);
            List<Message> messages =  new MessageService().queryMessage(this.userID.toString());
            Gson gson = new Gson();
            for(int index = 0; index < messages.size(); index++){
                String message = gson.toJson(messages.get(index), Message.class);
                try {
                    session.getBasicRemote().sendText(message);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else{
            try {
                session.getBasicRemote().sendText("error");
                session.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){
//        System.out.println(message);
        try {
            Gson gson = new Gson();
            Message obj =  gson.fromJson(message,Message.class);
            if(connections.containsKey(obj.getDestination())){
                Chat destination = connections.get(obj.getDestination());
                if(destination.getSession().isOpen()){
                    destination.getSession().getBasicRemote().sendText(message);
                }else{
                    connections.remove(destination.getUserID());
                    new MessageService().saveMessage(obj);
                }
            }else{
                new MessageService().saveMessage(obj);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        connections.remove(this.userID);

    }

    /**
     *
     * @param session
     * @param t
     */
    @OnError
    public void onError(Session session, Throwable t){
    }
}