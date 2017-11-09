package org.baize.dao.model;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class PlayerInfo extends Persist{
    /**名稱*/
    private String name; // 名称
    /**賬號*/
    private String account; // 账号描述
    /**密码*/
    private  String password;
    /**賬號類型*/
    private int loginType; // 账号类型（QQ,WX）
    /**排名*/
    private int rank; // 排名
    /**性別*/
    private int gender; // 性别
    /**頭像*/
    private int head; // 头像
    /**描述*/
    private String discibe; //

    private int weekDay;
    private String phon;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public String getPhon() {
        return phon;
    }

    public void setPhon(String phon) {
        this.phon = phon;
    }

}
