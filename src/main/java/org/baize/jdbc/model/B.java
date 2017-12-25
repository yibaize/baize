package org.baize.jdbc.model;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
public class B implements JdbcModel {
    private int id = 1;
    private String str = "撒的发生";
    private String s = "dsfsdfdsf奥术大师";

    public String getStr() {
        return str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return "B{" +
                "id=" + id +
                ", str='" + str + '\'' +
                ", s='" + s + '\'' +
                '}';
    }
}
