package org.baize.logic.login.command;

import org.baize.EnumType.ScenesType;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.logic.IFactory;
import org.baize.logic.login.manager.LoginManager;
import org.baize.room.IRoom;
import org.baize.room.RoomFactory;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@Protocol(id = "1")
public class Login extends OperateCommandAbstract {
    private final int loginType;
    private final String account;

    public Login(int loginType, String account) {
        this.loginType = loginType;
        this.account = account;
    }

    @Override
    public IProtostuff execute() {
        if(getSession().getAttachment() != null)
            new GenaryAppError(AppErrorCode.LOGIN_ERR);
        LoginManager manager = LoginManager.getInstace();
        IProtostuff dto = null;
        dto = manager.rest(loginType,getSession(),account);

        return (dto);
    }
}
