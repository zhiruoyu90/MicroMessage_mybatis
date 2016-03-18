package com.imooc.dao;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-03-18.
 */
public class ListDao {
    public List<Message> getMessageList(String qCommand, String qDescription) {
        List<Message> messageList = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = new DBAccess().getSqlSession();
            Connection conn = sqlSession.getConnection();
//            缓存前端参数
            StringBuilder sqlBuiler = new StringBuilder("select id, command, description, content from message where 1 = 1 ");
            List<String> paramList = new ArrayList<String>();
            if (null != qCommand && !("").equals(qCommand)) {
                sqlBuiler.append(" and command = ?");
                paramList.add(qCommand);
            }
            if (null != qDescription && !("").equals(qDescription)) {
                sqlBuiler.append(" and description like '%' ? '%'");
                paramList.add(qDescription);
            }
            PreparedStatement preStmt = conn.prepareStatement(sqlBuiler.toString());
            for (int i = 0; i < paramList.size(); i++) {
                preStmt.setString(i + 1, paramList.get(i));
            }

            ResultSet rs = preStmt.executeQuery();
            Message message = null;
            messageList = new ArrayList<Message>();
            while (rs.next()) {
                message = new Message();
                message.setId(rs.getInt("ID"));
                message.setCommand(rs.getString("COMMAND"));
                message.setDescription(rs.getString("DESCRIPTION"));
                message.setContent(rs.getString("CONTENT"));
                messageList.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return messageList;
    }
}