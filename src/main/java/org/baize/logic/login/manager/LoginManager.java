package org.baize.logic.login.manager;

import io.netty.channel.Channel;
import org.baize.EnumType.ScenesType;
import org.baize.dao.dto.PlayerDto;
import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.*;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.error.AppErrorCode;
import org.baize.error.Error;
import org.baize.logic.IFactory;
import org.baize.logic.room.RoomFactory;
import org.baize.server.message.IProtostuff;
import org.baize.utils.LoggerUtils;
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
        CorePlayer p =  checkOnline(ctx,account,password);
        PlayerEntity entity = null;
        if(p == null){
            PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
            PersistPlayerMapper playerMapper = mapper.selectOneForId(Integer.parseInt(account));
            if(playerMapper == null)
                new Error(ctx).err(AppErrorCode.DATA_ERR);
            entity = playerMapper.playerEntity();
            if(entity == null)
                new Error(ctx).err(AppErrorCode.DATA_ERR);
            if(!password.equals(entity.playerInfo().getPassword()))
                new Error(ctx).err(AppErrorCode.DATA_ERR);
        }
        putCache(ctx,entity);
        return dto(entity);
    }
    private CorePlayer checkOnline(Channel ctx, String account,String password){
        PlayerEntity entity = null;
        CorePlayer p = PersistPlayer.getById(Integer.parseInt(account));
        if(p != null){
            entity = p.entity();
            if(entity.playerInfo().getPassword().equals(password))
                new Error(ctx).err(AppErrorCode.PASSWORD_ERR);
            forceLogin(p.getCtx());
        }else {
            p = PersistPlayer.getOffLinePlayer(Integer.parseInt(account));
            if(p != null){
                entity = p.entity();
                if(entity.playerInfo().getPassword().equals(password))
                    new Error(ctx).err(AppErrorCode.PASSWORD_ERR);
                forceLogin(p.getCtx());
            }
        }
        return p;
    }
    public IProtostuff rest(int type,Channel ctx,String account){
        PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
        PersistPlayerMapper playerMapper = mapper.selectOneForId(Integer.parseInt(account));
        PlayerEntity entity = null;
        if(playerMapper == null){
            //注册
            entity = entity(type,account);
            if(entity == null)
                new Error(ctx).err(AppErrorCode.DATA_ERR);
            playerMapper = new PersistPlayerMapper(entity);
            try {
                mapper.insert(playerMapper);
            }catch (Exception e){
                LoggerUtils.getLogicLog().error("注册执行sql语句时出现异常",e);
            }
            entity = playerMapper.playerEntity();

        }else {
            entity = playerMapper.playerEntity();
        }
        CorePlayer loginPlayer = PersistPlayer.getById(entity.getId());
        if(loginPlayer != null){
            //账号在别的地方登陆将替换登陆老账号下线
           forceLogin(loginPlayer.getCtx());
        }
        putCache(ctx,entity);
        return dto(entity);
    }
    private void forceLogin(Channel ctx){
        new Error(ctx).debug(AppErrorCode.ACCOUNT_ERR);
        ctx.closeFuture();
    }
    private void putCache(Channel channel,PlayerEntity entity){
        CorePlayer corePlayer = new CorePlayer();
        corePlayer.setEntity(entity);
        corePlayer.setCtx(channel);
        corePlayer.setId(entity.getId());

        IFactory factory = RoomFactory.getInstance();
        IRoom room = (IRoom) factory.getBean(ScenesType.Mian.id());
        if(room == null)
            new Error(channel).err(AppErrorCode.DATA_ERR);

        room.into(corePlayer);
        corePlayer.setRoom(room);
        corePlayer.setScenesId(ScenesType.Mian.id());

        PersistPlayer.putPlayer(corePlayer);
    }
    private PlayerDto dto(PlayerEntity entity){
        PlayerInfo info = entity.playerInfo();
        Weath weath = entity.weath();
        PlayerDto dto = new PlayerDto();
        BeanUtils.copyProperties(info,dto);
        BeanUtils.copyProperties(weath,dto);
        dto.setId(entity.getId());
        dto.setHasNewFriend(entity.friends().isHasNewFriend());
        dto.setSignIn(entity.signIn().hasDraw());
        return dto;
    }
    public PlayerEntity entity(int type,String account){
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
