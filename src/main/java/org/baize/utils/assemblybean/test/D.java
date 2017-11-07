package org.baize.utils.assemblybean.test;

import org.baize.server.message.MessageAb;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/2.
 * 描述：
 */
@Protocol(id = "4")
public class D extends MessageAb {
    private String b;
    private float bf;
    private String bc;
    private boolean bb;
    private byte by;
    private int bi;
    private double bd;
    private long bl;
    private short bs;
    private Integer bii;
    private Long bll;
    private Short bss;
    public D() {
    }

    public D(String b, float bf, String bc, boolean bb, byte by, int bi, double bd, long bl, short bs, Integer bii, Long bll, Short bss) {
        this.b = b;
        this.bf = bf;
        this.bc = bc;
        this.bb = bb;
        this.by = by;
        this.bi = bi;
        this.bd = bd;
        this.bl = bl;
        this.bs = bs;
        this.bii = bii;
        this.bll = bll;
        this.bss = bss;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public float getBf() {
        return bf;
    }

    public void setBf(float bf) {
        this.bf = bf;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public boolean isBb() {
        return bb;
    }

    public void setBb(boolean bb) {
        this.bb = bb;
    }

    public byte getBy() {
        return by;
    }

    public void setBy(byte by) {
        this.by = by;
    }

    public int getBi() {
        return bi;
    }

    public void setBi(int bi) {
        this.bi = bi;
    }

    public double getBd() {
        return bd;
    }

    public void setBd(double bd) {
        this.bd = bd;
    }

    public long getBl() {
        return bl;
    }

    public void setBl(long bl) {
        this.bl = bl;
    }

    public short getBs() {
        return bs;
    }

    public void setBs(short bs) {
        this.bs = bs;
    }

    public Integer getBii() {
        return bii;
    }

    public void setBii(Integer bii) {
        this.bii = bii;
    }

    public Long getBll() {
        return bll;
    }

    public void setBll(Long bll) {
        this.bll = bll;
    }

    public Short getBss() {
        return bss;
    }

    public void setBss(Short bss) {
        this.bss = bss;
    }

    @Override
    public void execute() {

    }
}
