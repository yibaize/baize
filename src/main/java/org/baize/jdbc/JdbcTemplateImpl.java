package org.baize.jdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/21.
 * 描述：
 */
public class JdbcTemplateImpl implements JdbcTemplate{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/baize";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123";
    private final JdbcConnectionPools jdbcConnectionPools;
    private static JdbcTemplateImpl instance;
    public static JdbcTemplateImpl getInstance(){
        if(instance == null)
            instance = new JdbcTemplateImpl();
        return instance;
    }
    public JdbcTemplateImpl() {
        jdbcConnectionPools = new JdbcConnectionPools(5,10,DB_URL,USER,PASS);
    }

    public void insert(){

    }
    public void select(){

    }

    /**
     * 尽量避免使用PreparedStatement而使用Statement
     */
    public void batch(){
        Connection ct = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            ct = jdbcConnectionPools.getConnection();

            ct.setAutoCommit(false);//不自动提交
            System.out.println("实例化Statement对...");
            st = ct.createStatement();
            for(int i = 0;i<20000;i++){
                st.addBatch("insert into t_student (id,username) values (?,?)");
            }
            st.executeBatch();//批量提交
            ct.commit();//手动提交

        }catch (Exception e){
            e.printStackTrace();
        }finally {
           close(st,rs);
            jdbcConnectionPools.close(ct);
        }
        System.out.println("GoodBye!");
    }
    private static void close(Statement st,ResultSet rs){
        //后开先关
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(st != null)
                st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(String tableName,String obj) {
        Connection ct = null;
        PreparedStatement ps = null;
        int result = -1;
        try {
            ct = jdbcConnectionPools.getConnection();

            String sql;
            sql = "insert into t_student (id,username) values (?,?)";
            ps = ct.prepareStatement(sql);
            ps.setInt(1,1);
            ps.setString(2,"name");

            result = ps.executeUpdate();//插入，更新,删除一般用这个
            //ps.executeQuery();//查询，返回结果一般用这个
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(ps,null);
            jdbcConnectionPools.close(ct);
        }
        System.out.println("GoodBye!");
        return result;
    }

    @Override
    public int update(String tableName,String sql) {
        return 0;
    }

    @Override
    public List<String[]> select(String tableName,String sql) {
        Connection ct = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ct = jdbcConnectionPools.getConnection();

//            String sql;
//            sql = "SELECT "+field+" FROM "+tableName+" WHERE id = ?";
            ps = ct.prepareStatement(sql);

            rs = ps.executeQuery();//查询，返回结果一般用这个
            ResultSetMetaData rsmd = rs.getMetaData() ;
            List<String[]> result = new ArrayList<>();
            while (rs.next()){
                int length = rsmd.getColumnCount();
                String[] results = new String[length];
                for (int i = 0;i<length;i++){
                    results[i] = rs.getString(i+1);
                }
                result.add(results);
//                /**
//                 * 1:数据库中的第一列
//                 * 2：数据库中的第二列
//                 * 3：数据库中的第三列
//                 * .....以此类推
//                 */
//                System.out.println(rs.getInt(1)+" "+rs.getString(2)+"---"+rs.getString(3));
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //后开先关
            close(ps,rs);
            jdbcConnectionPools.close(ct);
        }
        return null;
    }


    @Override
    public int delete(String tableName, String sql) {
        return 0;
    }
    public void insert(Map<Integer,Object> map){
        String key = "";
        Object[] o = new Object[map.size()];
        //for(int i = )
    }
}
