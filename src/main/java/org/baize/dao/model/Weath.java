package org.baize.dao.model;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class Weath extends Persist {
    /**vip*/
    private int vip; // vip
    /**金幣*/
    private long gold; // 金币
    /**鑽石*/
    private int diamond; // 钻石

    public Weath() {
    }

    public Weath(int vip, long gold, int diamond) {
        this.vip = vip;
        this.gold = gold;
        this.diamond = diamond;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }
    public void increaseGold(int num){
        gold += num;
    }
    public void decreaseGold(int num){
        gold -= num;
    }
    public void increaseDiamond(int num){
        diamond += num;
    }
    public void decreaseDiamond(int num){
        diamond -= num;
    }
    public void increaseVip(int num){
        vip += num;
    }
    public void decreaseVip(int num){
        vip -= num;
    }
}
