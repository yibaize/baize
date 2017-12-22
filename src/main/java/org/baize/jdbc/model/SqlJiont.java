package org.baize.jdbc.model;

import com.alibaba.fastjson.JSON;
import org.baize.jdbc.Annotations.TableName;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/22.
 * 描述：sql语句拼装
 */
public class SqlJiont {
    private static Map<String,Integer> map = new HashMap<>();
    private static Map<Class<?>,String> fieldMap = new HashMap<>();
    private static Map<Class<?>,Integer> fieldIndex = new HashMap<>();
    /**
     * 拼接大的对象的sql插入语句
     * @param
     * @return
     */
    public static String jiontTableInsertSql(Class<?> clazz){
        StringBuffer sb = new StringBuffer();

        sb.append("INSERT INTO "+tableName(clazz)+" ");
        String key = "";
        String value = "";
        try {
            Field[] fields = clazz.getDeclaredFields();
            for(int i = 0;i<fields.length;i++){
                String v = fields[i].getName();
                map.put(v,i+1);
                fieldIndex.put(fields[i].getType(),i+1);
                fieldMap.put(fields[i].getType(),v);
                if(i == 0){
                    key += "("+v+",";
                    value += "("+"?,";
                }else if(i == fields.length-1){
                    key += v+")";
                    value += "?)";
                }else {
                    key += v+",";
                    value += "?,";
                }
            }
            sb.append(key+" VALUES "+value+";");//INSERT INTO player (1,2,3,4,5,6,7,8,9,10)  VALUES (?,?,?,?,?,?,?,?,?,?) ;
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    private static String tableName(Class<?> clazz){
        Annotation ann = clazz.getAnnotation(TableName.class);
        if(ann instanceof TableName){
            TableName value = (TableName) ann;
            String tableName = value.value();
            if(tableName == null || tableName.equals(""))
                throw new UnsupportedOperationException("没有为"+clazz+"的TableName注解的tableName赋值");
            return tableName;
        }
        return "";
    }

    /**
     * 赋值
     * @return
     */
    public static PreparedStatement valuation(Connection conn,PreparedStatement ps,JdbcModel model){
        Field[] fields = model.getClass().getDeclaredFields();
        for(Field f:fields){
            f.setAccessible(true);
        }
        try {
            ResultSetMetaData rsmd = resultSetMetaData(conn);
            int length = rsmd.getColumnCount();
            for (int i = 0;i<length;i++){
                String name = rsmd.getColumnName(i+1);
                if(map.containsKey(name)){
                    String value = "{}";
                    for(Field f:fields){
                        if(f.getName().equals(name));{
                            value = JSON.toJSONString(f.get(model));//转json字段存储
                            break;
                        }
                    }
                    ps.setObject(map.get(name),value);
                }
            }
            return ps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static ResultSetMetaData resultSetMetaData(Connection conn){
        try {
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getColumns("",null,"","%");
            if(!rs.next())
                return null;
            return rs.getMetaData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String oneField(Class<?> clazz){
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO player "+fieldMap.get(clazz)+" VALUES (?) WHERE id = ?;");
        return sb.toString();
    }
    public static int fieldIndex(Class<?> clazz){
        return fieldIndex.getOrDefault(clazz,0);
    }
    public static String selectOne(Class<?> clazz){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT "+fieldMap.get(clazz)+" FROM"+"tableName"+"WHERE id = ?;");
        return sb.toString();
    }
}
