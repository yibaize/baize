package org.baize.jdbc.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/23.
 * 描述：
 */
public class JdbcTemplate {
    private String tableName;
    private Map<Class<?>,SqlModel> sqlModelMap = new HashMap<>();
    private SqlModel tableSqlModel;
    private JdbcModel jdbcModel;
    private JdbcPool jdbcPool;
    private SqlJiont sqlJiont;
    public JdbcTemplate(JdbcModel jdbcModel) {
        jdbcPool = new JdbcPool("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/mysql", "root","",5,1,10);
        this.jdbcModel = jdbcModel;
        sqlJiont = new SqlJiont(jdbcModel);
        tableName = sqlJiont.getTableName();
        intTable();
        initField();
    }
    private void initField(){
        Map<Class<?>,String> fieldMap = sqlJiont.getFieldMap();
        Map<Class<?>,Integer> fieldIndex = sqlJiont.getFieldIndex();
        for (Map.Entry<Class<?>,String> e : fieldMap.entrySet()){
            Class<?> clzz = e.getKey();

            SqlModel sqlModel = new SqlModel();
            sqlModel.setField(e.getValue());
            sqlModel.setIndex(fieldIndex.get(clzz));
            sqlModel.setClazz(clzz);

            sqlModel.setInsert(sqlJiont.oneField(clzz));
            sqlModel.setSelect(sqlJiont.selectOneField(clzz));
            sqlModel.setUpdate(sqlJiont.updateOne(clzz));
            sqlModel.setDelete(sqlJiont.delete());
            sqlModelMap.put(clzz,sqlModel);
        }
        System.out.println("");
    }
    private void intTable(){
        tableSqlModel = new SqlModel();
        tableSqlModel.setInsert(sqlJiont.jiontTableInsertSql(jdbcModel.getClass()));
        tableSqlModel.setSelect(sqlJiont.tableSelect());
        tableSqlModel.setField(sqlJiont.tableSelectAll());
        tableSqlModel.setUpdate(sqlJiont.tableUpdate());
        tableSqlModel.setDelete(sqlJiont.delete());
    }

    public void inset(JdbcModel model){
        Connection conn = null;
        try {
            conn = jdbcPool.getConnction();
            if(model.getClass() == jdbcModel.getClass())
                tableInsert(model,conn);
            else
                sqlModelMap.get(model.getClass()).insert(conn,model);
        }finally {
            close(conn);
        }
    }
    public void update(JdbcModel model){
        Connection conn = null;
        try {
            conn = jdbcPool.getConnction();
            if(model.getClass() == jdbcModel.getClass())
                tableUpdate(model,jdbcPool.getConnction());
            else
                sqlModelMap.get(model.getClass()).update(conn,model);
        }finally {
            close(conn);
        }
    }
    public JdbcModel select(JdbcModel model){
        Connection conn = null;
        try {
            conn = jdbcPool.getConnction();
            System.out.println(conn);
            if(model.getClass() == jdbcModel.getClass())
                model = tableSelect(model,conn);
            else
                model = sqlModelMap.get(model.getClass()).select(conn,model);
        }finally {
            close(conn);
        }
        return model;
    }
    public void delete(JdbcModel model){
        Connection conn = null;
        try {
            conn = jdbcPool.getConnction();
            if(model.getClass() == jdbcModel.getClass())
                tableDelete(model,conn);
            else
                sqlModelMap.get(model.getClass()).delete(conn,model);
        }finally {
            close(conn);
        }
    }
    private int tableInsert(JdbcModel model, Connection conn){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(tableSqlModel.getInsert());
            ps = sqlJiont.valuation(ps,model);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    private int tableUpdate(JdbcModel model, Connection conn){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(tableSqlModel.getUpdate());
            ps = sqlJiont.valuation(ps,model);
            ps.setInt(sqlJiont.getMap().size() + 1,model.id());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    private void tableDelete(JdbcModel model, Connection conn){
        PreparedStatement ps = null;
        try {
            conn.prepareStatement(tableSqlModel.getDelete());
            ps.setObject(1,model.id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private JdbcModel tableSelect(JdbcModel model, Connection conn){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(tableSqlModel.getSelect());
            JdbcModel m = sqlJiont.tableInfo(ps,model);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void tableUpdateBatch(List<JdbcModel> models){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcPool.getConnction();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(tableSqlModel.getUpdate());
            for(int i = 0;i<models.size();i++) {
                JdbcModel model = models.get(i);
                ps = sqlJiont.valuation(ps, model);
                ps.setInt(sqlJiont.getMap().size() + 1, model.id());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(conn);
        }
    }
    private void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
