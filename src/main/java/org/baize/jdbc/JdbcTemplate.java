package org.baize.jdbc;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/12/21.
 * 描述：
 */
public interface JdbcTemplate {
    int insert(String tableName,String sql);
    int update(String tableName,String sql);
    List<String[]> select(String tableName,String sql);
    int delete(String tableName,String sql);
   // void batch(List<String> sql);
}
