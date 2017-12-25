package org.baize.jdbc.model;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class D implements JdbcModel{
    private int id = 12;
    private String lk = "ASFDASDasdap;sdjpasdkjap;sdas";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLk() {
        return lk;
    }

    public void setLk(String lk) {
        this.lk = lk;
    }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public String toString() {
        return "D{" +
                "id=" + id +
                ", lk='" + lk + '\'' +
                '}';
    }
}
