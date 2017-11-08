package org.baize.error;

import org.baize.utils.excel.DataTableMessage;
import org.baize.utils.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public class ErrorCode implements DataTableMessage {
    private final int id;
    private final String msg;
    public static ErrorCode get(int id){
        return StaticConfigMessage.getInstance().get(ErrorCode.class,id);
    }
    public ErrorCode() {
        this.id = 0;
        this.msg = "";
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
