package org.baize.logic.login.manager;

import org.baize.EnumType.ScenesType;
import org.baize.dao.dto.PlayerDto;
import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.*;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.error.LogAppError;
import org.baize.logic.IFactory;
import org.baize.room.IRoom;
import org.baize.room.RoomAbstract;
import org.baize.room.RoomFactory;
import org.baize.room.RoomPlayer;
import org.baize.server.message.IProtostuff;
import org.baize.server.session.ISession;
import org.baize.server.session.SessionManager;
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

    public IProtostuff rest(int type,ISession is,String account){
        PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
        PersistPlayerMapper playerMapper = mapper.selectOneForAccount(account);
        PlayerEntity entity = null;
        if(playerMapper == null){
            //注册
            entity = entity(type);
            if(entity == null)
                new GenaryAppError(AppErrorCode.DATA_ERR);
            playerMapper = new PersistPlayerMapper(entity);
            try {
                mapper.insert(playerMapper);
            }catch (Exception e){
                new LogAppError("注册执行sql语句时出现异常");
            }
            entity = playerMapper.playerEntity();

        }else {
            entity = playerMapper.playerEntity();
        }
        //是否登录过
        if(SessionManager.isOnlinePlayer(entity.getId())){
            ISession oldSession = SessionManager.removeSession(Integer.parseInt(account));
            RoomPlayer c = (RoomPlayer) oldSession.getAttachment();
            entity = c.entity();
            oldSession.removeAttachment();
            oldSession.close();
        }
        putCache(is,entity);
        return dto(entity);
    }
    private void putCache(ISession session,PlayerEntity entity){
        RoomPlayer roomPlayer = new RoomPlayer(entity);
        RoomAbstract room = RoomFactory.getInstance().getBean(ScenesType.Mian.id());
        roomPlayer.setRoom(room);
        if(SessionManager.putSession(entity.getId(),session)){
            session.setAttachment(roomPlayer);
        }else {
            new GenaryAppError(AppErrorCode.LOGIN_FAIL);
        }
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
    public PlayerEntity entity(int type){
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
