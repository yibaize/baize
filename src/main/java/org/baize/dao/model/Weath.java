package org.baize.dao.model;

import org.baize.dao.dto.WeathDto;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class Weath extends Persist {
    /**金幣*/
    private long gold; // 金币
    /**鑽石*/
    private int diamond; // 钻石
    /**是否已经购买摇钱树*/
    private boolean hasTree;
    /**上次领取时间*/
    private long lastDrawTime;
    public Weath() {
    }

    public Weath(long gold, int diamond) {
        this.gold = gold;
        this.diamond = diamond;
    }

    public boolean isHasTree() {
        return hasTree;
    }

    public void setHasTree(boolean hasTree) {
        this.hasTree = hasTree;
    }

    public long getLastDrawTime() {
        return lastDrawTime;
    }

    public void setLastDrawTime(long lastDrawTime) {
        this.lastDrawTime = lastDrawTime;
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

    @Override
    public void update() {
        super.update();
        CorePlayer corePlayer = PersistPlayer.getById(getId());
        if(corePlayer == null)
            return;
        corePlayer.respones((short)100,new WeathDto(gold,diamond));
    }
}
