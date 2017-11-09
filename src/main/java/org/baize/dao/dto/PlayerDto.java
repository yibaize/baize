package org.baize.dao.dto;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protocol;
import org.baize.utils.assemblybean.annon.Protostuff;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
@Protostuff
public class PlayerDto implements IProtostuff{
    /**
     * id
     */
    private int id; // id
    /**
     * 名稱
     */
    private String name; // 名称
    /**
     * 賬號
     */
    private String account; // 账号
    /**
     * 賬號類型
     */
    private int loginType; // 账号类型（QQ,WX）
    /**
     * vip
     */
    private int vip; // vip
    /**
     * 排名
     */
    private int rank; // 排名
    /**
     * 金幣
     */
    private long gold; // 金币
    /**
     * 鑽石
     */
    private int diamond; // 钻石
    /**
     * 性別
     */
    private int gender; // 性别
    /**
     * 頭像
     */
    private int head; // 头像
    /**
     * 描述
     */
    private String discibe; // 描述
    /**今天是周几*/
    private int weekDay;
    private String phon;
    private boolean hasNewFriend;
    private boolean signIn;

    public boolean isSignIn() {
        return signIn;
    }

    public void setSignIn(boolean signIn) {
        this.signIn = signIn;
    }

    public boolean isHasNewFriend() {
        return hasNewFriend;
    }

    public String getPhon() {
        return phon;
    }

    public void setPhon(String phon) {
        this.phon = phon;
    }

    public void setHasNewFriend(boolean hasNewFriend) {
        this.hasNewFriend = hasNewFriend;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getDiscibe() {
        return discibe;
    }

    public void setDiscibe(String discibe) {
        this.discibe = discibe;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }
}
