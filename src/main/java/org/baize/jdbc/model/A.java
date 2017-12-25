package org.baize.jdbc.model;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class A implements JdbcModel{
    private int i = 12;
    private int j = 10000;
    private int id = 11231;
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return "A{" +
                "i=" + i +
                ", j=" + j +
                ", id=" + id +
                '}';
    }
}
