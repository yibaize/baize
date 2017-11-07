package org.baize.utils.assemblybean.test;


import org.baize.server.message.MessageAb;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/2.
 * 描述：
 */
@Protocol(id = "2")
public class C extends MessageAb {
    private int c;
    private final int asd;

    public C(int c, int asd, String asdf, double dfdsf) {
        this.c = c;
        this.asd = asd;
        this.asdf = asdf;
        this.dfdsf = dfdsf;
    }

    private final String asdf;
    private final double dfdsf;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getAsd() {
        return asd;
    }

    public String getAsdf() {
        return asdf;
    }

    public double getDfdsf() {
        return dfdsf;
    }

    @Override
    public void execute() {

    }
}
