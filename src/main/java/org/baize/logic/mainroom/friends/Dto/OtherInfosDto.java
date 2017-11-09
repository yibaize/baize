package org.baize.logic.mainroom.friends.Dto;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protostuff
public class OtherInfosDto implements IProtostuff {
    private List<OtherInfoDto> friends;
    private List<OtherInfoDto> friendApplyDtos;

    public OtherInfosDto(List<OtherInfoDto> friends, List<OtherInfoDto> friendApplyDtos) {
        this.friends = friends;
        this.friendApplyDtos = friendApplyDtos;
    }

    public OtherInfosDto() {

    }

    public List<OtherInfoDto> getFriends() {
        return friends;
    }

    public void setFriends(List<OtherInfoDto> friends) {
        this.friends = friends;
    }

    public List<OtherInfoDto> getFriendApplyDtos() {
        return friendApplyDtos;
    }

    public void setFriendApplyDtos(List<OtherInfoDto> friendApplyDtos) {
        this.friendApplyDtos = friendApplyDtos;
    }
}
