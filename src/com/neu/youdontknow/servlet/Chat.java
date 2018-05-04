package com.neu.youdontknow.servlet;

import com.neu.youdontknow.models.Message;
import com.neu.youdontknow.models.User;
import com.google.gson.Gson;
import com.neu.youdontknow.service.GetHttpSessionConfigurator;

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
    private static String ERROR = "error";

    private String userID;
    private Session session;


    /**
     *
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        System.out.println("connecting........");
        User user = (User)((HttpSession)(config.getUserProperties().get("httpSession"))).getAttribute("user");
        if(user != null){
            this.userID = (new Integer(user.getId())).toString();
            this.session = session;
            System.out.println("connecting.." + this.userID);
            connections.put(this.userID, this);
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
        System.out.println(message);
        try {
            Gson gson = new Gson();
            Message obj =  gson.fromJson(message,Message.class);

            Chat target = connections.get(obj.getDestination());
            target.session.getBasicRemote().sendText(message);
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