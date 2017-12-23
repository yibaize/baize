package org.baize.jdbc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/12/23.
 * 描述：
 */
public class JdbcTemplate {
    private String tableName;
    private Map<Class<?>,SqlModel> sqlModelMap = new HashMap<>();
    private JdbcModel jdbcModel;
    private SqlJiont sqlJiont;
    public JdbcTemplate(JdbcModel jdbcModel) {
        this.jdbcModel = jdbcModel;
        sqlJiont = new SqlJiont(jdbcModel);
        tableName = sqlJiont.getTableName();
    }
    private void init(){
        Map<Class<?>,String> fieldMap = sqlJiont.getFieldMap();
        Map<Class<?>,Integer> fieldIndex = sqlJiont.getFieldIndex();
        for (Map.Entry<Class<?>,String> e : fieldMap.entrySet()){
            Class<?> clzz = e.getKey();

            SqlModel sqlModel = new SqlModel();
            sqlModel.setField(e.getValue());
            sqlModel.setIndex(fieldIndex.get(clzz));
            sqlModel.setClazz(clzz);

            sqlModel.setInsert(sqlJiont.oneField(clzz,tableName));
            sqlModel.setSelect(sqlJiont.selectOneField(clzz,tableName));
            sqlModel.setUpdate(sqlJiont.updateOne(clzz,tableName));
            sqlModel.setDelete(sqlJiont.delete(clzz,tableName));
        }
    }
}
