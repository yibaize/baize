package org.baize.jdbc.model;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 作者： 白泽
 * 时间： 2017/12/23.
 * 描述：
 */
public class SqlModel {
    private String insert;
    private String delete;
    private String update;
    private String select;
    private String field;
    private int index;
    private Class<?> clazz;
    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * 赋值
     * @return
     */
    public int insert(Connection conn, JdbcModel model){
        return update(conn,model,insert);
    }
    public String select(Connection conn, JdbcModel model){
        try {
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            return rs.getString(field);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "{}";
    }
    public int update(Connection conn, JdbcModel model){
        return update(conn,model,update);
    }
    public int delete(Connection conn, JdbcModel model){
        return update(conn,model,delete);
    }
    private int update(Connection conn, JdbcModel model,String sql){
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            String value = JSON.toJSONString(model);//转json字段存储
            ps.setObject(index,value);
            int i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
