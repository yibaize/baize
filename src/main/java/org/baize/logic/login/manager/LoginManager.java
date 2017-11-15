package org.baize.logic.login.manager;

import io.netty.channel.Channel;
import org.baize.EnumType.ScenesType;
import org.baize.dao.dto.PlayerDto;
import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.*;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.error.Error;
import org.baize.logic.IFactory;
import org.baize.logic.room.IRoom;
import org.baize.logic.room.RoomFactory;
import org.baize.server.message.IProtostuff;
import org.baize.utils.createid.CreateIdUtils;
import org.baize.utils.SpringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@Service
public class LoginManager {
    public static LoginManager getInstace(){
        return SpringUtils.getBean(LoginManager.class);
    }
    public IProtostuff account(Channel ctx, String account,String password){
        PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
        PersistPlayerMapper playerMapper = mapper.selectOneForId(Integer.parseInt(account));
        if(playerMapper == null)
            new Error(this.getClass(),ctx).debug(1);
        PlayerEntity entity = playerMapper.playerEntity();
        if(entity == null)
            new Error(this.getClass(),ctx).debug(100);
        if(!password.equals(entity.getPlayerInfo().getPassword()))
            new Error(this.getClass(),ctx).debug(2);

        CorePlayer loginPlayer = PersistPlayer.getById(entity.getId());
        if(loginPlayer != null){
            //账号在别的地方登陆将替换登陆老账号下线
            loginPlayer.getCtx().writeAndFlush(null);
            loginPlayer.getCtx().closeFuture();
        }
        putCache(ctx,entity);
        return dto(entity);
    }
    public IProtostuff rest(int type,Channel ctx,String account){
        PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
        PersistPlayerMapper playerMapper = mapper.selectOneForId(Integer.parseInt(account));
        PlayerEntity entity = null;
        if(playerMapper == null){
            //注册
            entity = entity(type,account);
            playerMapper = new PersistPlayerMapper(entity);
            mapper.insert(playerMapper);
            entity = playerMapper.playerEntity();

        }else {
            entity = playerMapper.playerEntity();
        }
        CorePlayer loginPlayer = PersistPlayer.getById(entity.getId());
        if(loginPlayer != null){
            //账号在别的地方登陆将替换登陆老账号下线
            loginPlayer.getCtx().writeAndFlush(null);
            loginPlayer.getCtx().closeFuture();
        }
        putCache(ctx,entity);
        return dto(entity);
    }
    private void putCache(Channel channel,PlayerEntity entity){
        CorePlayer corePlayer = new CorePlayer();
        corePlayer.setEntity(entity);
        corePlayer.setCtx(channel);
        corePlayer.setId(entity.getId());

        IFactory factory = new RoomFactory();
        IRoom room = (IRoom) factory.getBean(ScenesType.Mian.id());
        if(room == null)
            new Error(this.getClass(),channel).err(100);
        room.into(corePlayer);

        corePlayer.setRoom(room);
        corePlayer.setScenesId(ScenesType.Mian.id());

        PersistPlayer.putByCtx(channel,corePlayer);
        PersistPlayer.putById(corePlayer.getId(),corePlayer);
    }
    private PlayerDto dto(PlayerEntity entity){
        PlayerInfo info = entity.getPlayerInfo();
        Weath weath = entity.getWeath();
        PlayerDto dto = new PlayerDto();
        BeanUtils.copyProperties(info,dto);
        BeanUtils.copyProperties(weath,dto);
        dto.setId(entity.getId());
        dto.setHasNewFriend(entity.getFriends().isHasNewFriend());
        dto.setSignIn(entity.getSignIn().hasDraw());
        return dto;
    }
    private PlayerEntity entity(int type,String account){
        PlayerInfo info = new PlayerInfo();
        PlayerDataTable dataTable = PlayerDataTable.get(1);
        if(dataTable == null)
            return null;
        BeanUtils.copyProperties(dataTable,info);
        info.setLoginType(type);
        Weath weath = new Weath();
        BeanUtils.copyProperties(dataTable,weath);
        int id = CreateIdUtils.id();
        PlayerEntity entity = new PlayerEntity();
        entity.setPlayerInfo(info);
        entity.setWeath(weath);
        entity.setId(id);
        return entity;
    }
}
