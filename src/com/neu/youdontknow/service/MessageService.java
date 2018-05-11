package com.neu.youdontknow.service;

import com.neu.youdontknow.admin.MessageAdmin;

import java.sql.SQLException;
import java.util.List;
import com.neu.youdontknow.models.Message;
public class MessageService {
    public void saveMessage(Message message){
        MessageAdmin messageAdmin = new MessageAdmin();
        try {
            messageAdmin.save(message);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Message> queryMessage(int distination){
        MessageAdmin messageAdmin = new MessageAdmin();
        List<Message> list = null;
        try {
            list = messageAdmin.getMessage(distination);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
