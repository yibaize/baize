package org.baize.logic.mainroom.signin.dto;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protostuff
public class SignInDto implements IProtostuff {
    private boolean draw;

    public SignInDto() {
    }

    public SignInDto(boolean draw) {
        this.draw = draw;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }
}
