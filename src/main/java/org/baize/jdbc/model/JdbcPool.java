package org.baize.jdbc.model;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class JdbcPool {
    private DruidDataSource dataSource = new DruidDataSource();

    private JdbcPool() {
    }
    public JdbcPool(String driver, String url, String user, String password, int initPools, int min, int max) {
        initPool(driver,url,user,password,initPools,min,max);
    }
    private void initPool(String driver,String url,String user,String password,int initPools,int min,int max){
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        // 初始化连接数量
        dataSource.setInitialSize(initPools);
        // 最小空闲连接数
        dataSource.setMinIdle(min);
        // 最大并发连接数
        dataSource.setMaxActive(max);
        // 监控统计用的filter:stat
        // 日志用的filter:log4j
        // 防御SQL注入的filter:wall
        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setPoolPreparedStatements(false);
    }

    private final Object lock = new Object();
    public Connection getConnction(){
        synchronized (lock) {
            Connection conn = null;
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }
    }
}
