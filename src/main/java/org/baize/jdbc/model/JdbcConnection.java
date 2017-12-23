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
public class JdbcConnection {
    private static JdbcConnectionPools jdbcConnectionPools;
//    public static int insert(JdbcModel model){
//        Connection ct = null;
//        PreparedStatement ps = null;
//        int result = -1;
//        try {
//            ct = jdbcConnectionPools.getConnection();
//            String sql = SqlJiont.jiontTableInsertSql(model.getClass());
//            if(sql.equals(""))
//                throw new UnsupportedOperationException("sql语句拼接异常");
//            ps = ct.prepareStatement(sql);
//            ps = SqlJiont.valuation(ct,ps,model);
//            result = ps.executeUpdate();//插入，更新,删除一般用这个
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//
//            jdbcConnectionPools.close(ct);
//        }
//        System.out.println("GoodBye!");
//        return result;
//    }
    public static int insertOneField(SqlModel sqlModel,JdbcModel model){
        Connection ct = null;
        try {
            ct = jdbcConnectionPools.getConnection();
            return sqlModel.insert(ct,model);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbcConnectionPools.close(ct);
        }
        return -1;
    }
//    public static JdbcModel selectOneObj(JdbcModel model){
//        Connection ct = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ct = jdbcConnectionPools.getConnection();
//            ps = ct.prepareStatement(SqlJiont.selectOneField(model.getClass(),SqlJiont.tableName(model.getClass())));
//            rs = ps.executeQuery();//查询，返回结果一般用这个
//            ResultSetMetaData rsmd = rs.getMetaData() ;
//            return model;
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            //后开先关
//            jdbcConnectionPools.close(ct);
//        }
//        return null;
//    }
    public static JdbcModel selectAllObj(){
        return null;
    }
    public static JdbcModel selectOneField(JdbcModel model,SqlModel sqlModel){
        Connection ct = null;
        try {
            ct = jdbcConnectionPools.getConnection();
            model = JSON.parseObject(sqlModel.select(ct,model),model.getClass());
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
        //UPDATE table_anem SET column_name1 = value1, column_name2 = value2, WHERE
        Connection ct = null;
        PreparedStatement ps = null;
        int result = -1;
        try {
            ct = jdbcConnectionPools.getConnection();
            ps = ct.prepareStatement("");
            result = ps.executeUpdate();//插入，更新,删除一般用这个
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbcConnectionPools.close(ct);
        }
        System.out.println("GoodBye!");
        return result;
    }
    public static int delete(String tableName, String sql) {
        return 0;
    }
    public static void batch(){}
}
