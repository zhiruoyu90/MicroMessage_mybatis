package com.imooc.servlet;

import com.imooc.bean.Message;
import com.imooc.service.ListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016-03-08.
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String qCommand = req.getParameter("q_command");
        String qDescription = req.getParameter("q_description");
        req.setAttribute("q_command", qCommand);
        req.setAttribute("q_description", qDescription);
        List<Message> messageList = new ListService().getMessage(qCommand, qDescription);

        req.setAttribute("messageList", messageList);
        req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
