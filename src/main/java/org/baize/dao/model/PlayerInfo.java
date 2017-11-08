package org.baize.dao.model;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class PlayerInfo extends Persist {
    private String account;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
