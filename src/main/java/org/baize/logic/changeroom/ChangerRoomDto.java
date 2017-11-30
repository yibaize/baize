package org.baize.logic.changeroom;

import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protostuff
public class ChangerRoomDto implements IProtostuff{
    private int timer;
    private int online;
    private boolean battle;
    private OtherInfoDto banker;
    public OtherInfoDto getBanker() {
        return banker;
    }

    public void setBanker(OtherInfoDto banker) {
        this.banker = banker;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public boolean isBattle() {
        return battle;
    }

    public void setBattle(boolean battle) {
        this.battle = battle;
    }
}
