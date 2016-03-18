package com.imooc.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Administrator on 2016-03-18.
 */
public class DBAccess {
    public SqlSession getSqlSession() throws IOException {
//        通过配置文件获取数据库连接相关信息
        Reader reader = Resources.getResourceAsReader("config/Configuration.xml");
//        通过配置信息构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) new SqlSessionFactoryBuilder().build(reader);
//        通过sqlSessionFactory打开一个数据库会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
