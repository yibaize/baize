package org.baize.logic.login.command;

import org.baize.EnumType.LoginType;
import org.baize.logic.login.manager.LoginManager;
import org.baize.server.message.CommandAb;
import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@Protocol(id = "1")
public class Login extends CommandAb{
    private final int loginType;
    private final String account;
    private final String password;

    public Login(int loginType, String account, String password) {
        this.loginType = loginType;
        this.account = account;
        this.password = password;
    }

    @Override
    public void execute() {
        LoginManager manager = LoginManager.getInstace();
        IProtostuff dto = null;
        if(loginType != LoginType.Account.id())
            dto = manager.account( this.getCtx(),account,password);
        else
            dto = manager.rest(this.getCtx(),account);
       this.responce(dto);
    }
}
