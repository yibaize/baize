package org.baize.jdbc.model;

import com.alibaba.fastjson.JSON;
import org.baize.jdbc.JdbcConnectionPools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * 作者： 白泽
 * 时间： 2017/12/22.
 * 描述：
 */
public class JdbcTemplate {
    private static JdbcConnectionPools jdbcConnectionPools;
    public static int insert(JdbcModel model){
        Connection ct = null;
        PreparedStatement ps = null;
        int result = -1;
        try {
            ct = jdbcConnectionPools.getConnection();
            String sql = SqlJiont.jiontTableInsertSql(model.getClass());
            if(sql.equals(""))
                throw new UnsupportedOperationException("sql语句拼接异常");
            ps = ct.prepareStatement(sql);
            ps = SqlJiont.valuation(ct,ps,model);
            result = ps.executeUpdate();//插入，更新,删除一般用这个
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            jdbcConnectionPools.close(ct);
        }
        System.out.println("GoodBye!");
        return result;
    }
    public static int insertOneField(JdbcModel model){
        Connection ct = null;
        PreparedStatement ps = null;
        int result = -1;
        try {
            ct = jdbcConnectionPools.getConnection();
            String sql = SqlJiont.oneField(model.getClass());
            if(sql.equals(""))
                throw new UnsupportedOperationException("sql语句拼接异常");
            ps = ct.prepareStatement(sql);
            int index = SqlJiont.fieldIndex(model.getClass());
            if(index <= 0)
                throw new UnsupportedOperationException("数据库中没有"+model.getClass()+"对应的字段");
            ps.setObject(index,JSON.toJSONString(model));
            result = ps.executeUpdate();//插入，更新,删除一般用这个
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            jdbcConnectionPools.close(ct);
        }
        return result;
    }
    public static JdbcModel selectOneObj(){
        return null;
    }
    public static JdbcModel selectAllObj(){
        return null;
    }
    public static JdbcModel selectOneField(JdbcModel model){
        Connection ct = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ct = jdbcConnectionPools.getConnection();
            ps = ct.prepareStatement(SqlJiont.selectOne(model.getClass()));
            rs = ps.executeQuery();//查询，返回结果一般用这个
            ResultSetMetaData rsmd = rs.getMetaData() ;
            String resut = rs.getString(SqlJiont.fieldName(model.getClass()));
            model = JSON.parseObject(resut,model.getClass());
            return model;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //后开先关
            jdbcConnectionPools.close(ct);
        }
        return null;
    }
    public static int update(JdbcModel model){
        return 0;
    }
    public static int delete(String tableName, String sql) {
        return 0;
    }
    public static void batch(){}
}
