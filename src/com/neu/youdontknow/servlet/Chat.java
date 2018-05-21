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
import java.util.Date;

@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
public class Chat {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static HashMap<Integer, Session> connections = new HashMap<Integer, Session>();
    private static Gson gson = new Gson();

    /**
     *
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
//        System.out.println("user is comming");
        User user = (User)((HttpSession)(config.getUserProperties().get("httpSession"))).getAttribute("user");
        if(user != null){
            System.out.println("add one user");
            connections.put(new Integer(user.getId()),session);
            List<Message> messages =  new MessageService().queryMessage(user.getId());
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
                System.out.println("reject");
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
            Message obj =  gson.fromJson(message,Message.class);
            obj.setTime(new Date().toString());
            message = gson.toJson(obj, Message.class);
            if(connections.containsKey(obj.getDestination())){
                Session destinctSession = connections.get(obj.getDestination());
                if(destinctSession.isOpen()){
                    destinctSession.getBasicRemote().sendText(message);
                }else{
                    connections.remove(obj.getDestination());
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
        System.out.println("A session is closed!");
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