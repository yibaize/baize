package org.baize.jdbc.model;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class C implements JdbcModel {
    private int id = 10;
    private String name = "asdasd";
    @Override
    public int id() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "C{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
