package org.baize.logic.mainroom.rank.command;

import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.logic.mainroom.rank.dto.RankDto;
import org.baize.logic.mainroom.rank.manaer.RankManager;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.assemblybean.annon.Protocol;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "8")
public class Rank_Select extends OperateCommandAbstract {
    @Override
    public IProtostuff execute() {
        List<OtherInfoDto> other = RankManager.getInstance().getRanks();
        return new RankDto(other);
    }
}
