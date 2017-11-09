package org.baize.logic.mainroom.rank.dto;

import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protostuff
public class RankDto implements IProtostuff{
    private List<OtherInfoDto> rank;

    public RankDto() {
    }

    public RankDto(List<OtherInfoDto> rank) {
        this.rank = rank;
    }

    public List<OtherInfoDto> getRank() {
        return rank;
    }

    public void setRank(List<OtherInfoDto> rank) {
        this.rank = rank;
    }
}
