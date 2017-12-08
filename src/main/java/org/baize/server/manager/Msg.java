package org.baize.server.manager;

import org.baize.server.message.IProtostuff;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/12/7.
 * 描述：
 */
public class Msg implements IProtostuff{
    private String msg;

    public Msg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
