package org.baize.jdbc.model;

import org.baize.jdbc.Annotations.TableName;

/**
 * 作者： 白泽
 * 时间： 2017/12/25.
 * 描述：
 */
@TableName("test")
public class ModelTest implements JdbcModel{
    private transient int id = 1083;
    private A a;
    private B b;
    private D d;
    private C c;

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return "ModelTest{" +
                "id=" + id +
                ", a=" + a +
                ", b=" + b +
                ", d=" + d +
                ", c=" + c +
                '}';
    }
}
