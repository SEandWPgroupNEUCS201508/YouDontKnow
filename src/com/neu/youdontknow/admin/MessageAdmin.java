package com.neu.youdontknow.admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.neu.youdontknow.models.Message;
import com.neu.youdontknow.utils.DataBaseUtils;

import java.sql.SQLException;
import java.util.List;

public class MessageAdmin  {
    public int save(Message message) throws SQLException{
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "insert into message(source, destination, message, time) values(?, ?, ?, ?)";
        return queryRunner.update(sql, message.getSource(), message.getDestination(),
                message.getMessage(), message.getTime());
    }

    public List<Message> getMessage(int distination) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "select * from message where destination=?";
        List<Message> list =  queryRunner.query(sql, new BeanListHandler<>(Message.class), distination);
        sql = "delete from message where destination=?";
        queryRunner.update(sql, distination);
        return list;
    }
}
