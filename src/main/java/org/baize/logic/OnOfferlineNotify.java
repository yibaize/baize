package org.baize.logic;

import org.baize.server.message.IProtostuff;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public class OnOfferlineNotify implements IProtostuff{
    private String msg;

    public OnOfferlineNotify(String msg) {
        this.msg = msg;
    }

    public OnOfferlineNotify() {

    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
