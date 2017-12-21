package org.baize.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/12/21.
 * 描述：
 */
public class JdbcConnectionPools {
    /**
     * 池
     */
    private List<Connection> pool;
    /**
     * 最大
     */
    private final int pooMaxSize;
    /**
     * 最小
     */
    private final int poolMinSize;

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String url;
    // 数据库的用户名与密码，需要根据自己的设置
    private final String user;
    private final String password;

    public JdbcConnectionPools(int poolMinSize, int pooMaxSize, String url, String user, String password) {
        this.pooMaxSize = pooMaxSize;
        this.poolMinSize = poolMinSize;
        this.url = url;
        this.user = user;
        this.password = password;
        initPool();
    }

    private void initPool(){
        if(pool == null){
            pool = new ArrayList<>(poolMinSize);
        }
        while (pool.size()< poolMinSize){
            put(getConn());
            System.out.println("初始化连接第："+pool.size()+"个");
        }
    }

    /**
     * 获取连接
     * @return
     */
    public synchronized Connection getConnection(){
        int lastIndex = pool.size()-1;
        Connection conn = null;
        if(pool.size() <= 0){
            put(conn);
            lastIndex = pool.size()-1;
        }
        conn = pool.get(lastIndex);
        pool.remove(lastIndex);
        return conn;
    }

    /**
     * 将连接放回
     * @param conn
     */
    public synchronized void close(Connection conn){
        if(pool.size() >= pooMaxSize)
            closeConn(conn);
        else
            put(conn);
    }
    private void put(Connection connection){
        if(pool.size() < pooMaxSize){
            pool.add(connection);
        }else {
            closeConn(connection);
        }
    }
    private Connection getConn(){
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private void closeConn(Connection ct){
        //后开先关
        try {
            if(ct != null)
                ct.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
