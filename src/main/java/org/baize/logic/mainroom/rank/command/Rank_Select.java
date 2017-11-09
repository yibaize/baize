package org.baize.logic.mainroom.rank.command;

import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.logic.mainroom.rank.dto.RankDto;
import org.baize.logic.mainroom.rank.manaer.RankManager;
import org.baize.server.message.CommandAb;
import org.baize.utils.assemblybean.annon.Protocol;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "8")
public class Rank_Select extends CommandAb{
    @Override
    public void execute() {
        List<OtherInfoDto> other = RankManager.getInstance().getRanks();
        this.responce(new RankDto(other));
    }
}
