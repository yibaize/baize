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
    private OtherInfoDto banker;
    private List<OtherInfoDto> bankerList;

    public OtherInfoDto getBanker() {
        return banker;
    }

    public void setBanker(OtherInfoDto banker) {
        this.banker = banker;
    }

    public List<OtherInfoDto> getBankerList() {
        return bankerList;
    }

    public void setBankerList(List<OtherInfoDto> bankerList) {
        this.bankerList = bankerList;
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
}
