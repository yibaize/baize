package org.baize.logic.mainroom.signin.command;

import org.baize.error.Error;
import org.baize.logic.mainroom.signin.dto.SignInDto;
import org.baize.logic.mainroom.signin.module.SignIn;
import org.baize.server.message.CommandAb;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "9")
public class SignIn_Award extends CommandAb {
    @Override
    public void execute() {
        SignIn signIn = player().entity().signIn();
        boolean draw = signIn.draw();
        if(!draw)
            new Error(this.getCtx()).err(1);
        responce(new SignInDto(draw));
    }
}
