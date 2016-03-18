package com.imooc.service;

import com.imooc.bean.Message;
import com.imooc.dao.ListDao;

import java.util.List;

/**
 * Created by Administrator on 2016-03-18.
 */
public class ListService {
    public List<Message> getMessage(String qCommand, String qDescription) {
        return new ListDao().getMessageList(qCommand, qDescription);
    }
}
