package org.baize.dao.model;

import org.baize.utils.assemblybean.annon.DataTable;
import org.baize.utils.excel.DataTableMessage;
import org.baize.utils.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@DataTable
public class PlayerDataTable implements DataTableMessage{
    private final int id;
    /**名稱*/
    private final String name; // 名称
    /**賬號*/
    private final String account; // 账号描述
    /**密码*/
    private final  String password;
    /**賬號類型*/
    private final int loginType; // 账号类型（QQ,WX）
    /**排名*/
    private final int rank; // 排名
    /**性別*/
    private final int gender; // 性别
    /**頭像*/
    private final int head; // 头像
    /**描述*/
    private final String discibe; //
    private final int weekDay;
    private final int vip;
    private final long gold;
    private final int diamond;
    public PlayerDataTable() {
        this.id = 0;
        this.name = "";
        this.account = "";
        this.password = "";
        this.loginType = 0;
        this.rank = 0;
        this.gender = 0;
        this.head = 1;
        this.discibe = "";
        this.weekDay = 0;
        this.vip = 0;
        this.gold = 0;
        this.diamond = 0;
    }
    public static PlayerDataTable get(int id){
        return StaticConfigMessage.getInstance().get(PlayerDataTable.class,id);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getLoginType() {
        return loginType;
    }

    public int getRank() {
        return rank;
    }

    public int getGender() {
        return gender;
    }

    public int getHead() {
        return head;
    }

    public String getDiscibe() {
        return discibe;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public int getVip() {
        return vip;
    }

    public long getGold() {
        return gold;
    }

    public int getDiamond() {
        return diamond;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
