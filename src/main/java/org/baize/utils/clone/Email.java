package org.baize.utils.clone;

import org.baize.jdbc.Annotations.TableName;

import java.io.Serializable;

/**
 * 作者：泡泡大湿
 * 时间：*******
 */
@TableName("player")
public class Email implements Serializable {
    private String msg;

    public Email(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
