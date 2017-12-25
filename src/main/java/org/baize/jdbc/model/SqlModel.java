package org.baize.jdbc.model;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;

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
        PreparedStatement ps = null;
        try {
            String value = JSON.toJSONString(model);//转json字段存储
            String s = value;
            ps = conn.prepareStatement(insert);
            ps.setObject(1,value);
            int i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public JdbcModel select(Connection conn, JdbcModel model){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(select);
            ps.setObject(1,model.id());
            rs = ps.executeQuery();
            String s = "{}";
            while (rs.next()){
                s = rs.getString(field);
            }
            model = JSON.parseObject(s,model.getClass());
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int update(Connection conn, JdbcModel model){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(update);
            String value = JSON.toJSONString(model);//转json字段存储
            ps.setObject(1,value);
            ps.setObject(2,model.id());
            int i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int delete(Connection conn, JdbcModel model){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(delete);
            ps.setObject(1,model.id());
            int i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void updateBatch(Connection conn,List<JdbcModel> models){
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(update);
            for(int i = 0;i<models.size();i++) {
                JdbcModel model = models.get(i);
                String value = JSON.toJSONString(model);//转json字段存储
                ps.setObject(1,value);
                ps.setObject(2,model.id());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
