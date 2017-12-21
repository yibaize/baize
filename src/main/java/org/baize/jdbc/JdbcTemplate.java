package org.baize.jdbc;
import java.sql.*;
/**
 * 作者： 白泽
 * 时间： 2017/12/21.
 * 描述：
 */
public class JdbcTemplate {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/baize";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123";
    public static void insert(){
        Connection ct = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.print("连接数据库...");
            ct = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("实例化Statement对...");

            String sql;
            sql = "insert into t_student (id,username) values (?,?)";
            ps = ct.prepareStatement(sql);
            ps.setInt(1,1);
            ps.setString(2,"name");

            System.out.println("插入一条数据...");

            ps.executeUpdate();//插入，更新,删除一般用这个
            //ps.executeQuery();//查询，返回结果一般用这个
            ps.close();
            ct.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(ps != null) try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(ct != null) try {
                ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("GoodBye!");
    }
    public static void select(){
        Connection ct = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.print("连接数据库...");
            ct = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("实例化Statement对...");

            String sql;
            sql = "SELECT id,username,password FROM t_student WHERE id > ?";
            ps = ct.prepareStatement(sql);

            rs = ps.executeQuery();//查询，返回结果一般用这个
            while (rs.next()){
                /**
                 * 1:数据库中的第一列
                 * 2：数据库中的第二列
                 * 3：数据库中的第三列
                 * .....以此类推
                 */
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+"---"+rs.getString(3));
            }
            ps.close();
            ct.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //后开先关
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } try {
                if(ct != null)
                    ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("GoodBye!");
    }
}
