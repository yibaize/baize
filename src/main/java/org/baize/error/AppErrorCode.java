package org.baize.error;

import org.baize.utils.assemblybean.annon.ExcelInversion;
import org.baize.utils.assemblybean.annon.ExcelValue;
import org.baize.utils.excel.DataTableMessage;
import org.baize.utils.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@ExcelInversion
public final class AppErrorCode{
    @ExcelValue(value = "数据异常")
    public final static int DATA_ERR = 404;
    @ExcelValue(value = "账号在其他地方登陆")
    public final static int ACCOUNT_ERR = 1;
    @ExcelValue(value = "还没达到领取奖励时间")
    public final static int TIMER_ERR = 2;
    @ExcelValue(value = "钻石不足，请先充值钻石")
    public final static int DIAMOND_ERR = 3;
    @ExcelValue(value = "您还没有购买摇钱树，请先购买摇钱树")
    public final static int TREE_NOT = 4;
    @ExcelValue(value = "今天已经签到")
    public final static int SIGNIN_ERR = 5;
    @ExcelValue(value = "密码错误")
    public final static int PASSWORD_ERR = 6;
    @ExcelValue(value = "账号已经登录")
    public final static int LOGIN_ERR = 7;
    @ExcelValue(value = "登录失败")
    public final static int LOGIN_FAIL = 8;

}
