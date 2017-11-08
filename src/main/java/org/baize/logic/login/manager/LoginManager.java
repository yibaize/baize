package org.baize.logic.login.manager;

import io.netty.channel.Channel;
import org.baize.EnumType.LoginType;
import org.baize.dao.dto.PlayerDto;
import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PersistPlayer;
import org.baize.dao.model.PlayerEntity;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.error.Error;
import org.baize.server.message.IProtostuff;
import org.baize.utils.SpringUtils;
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
        putCache(ctx,entity);
        return dto(entity);
    }
    public IProtostuff rest(){
        return null;
    }
    private boolean check(Channel channel,int id){
        if(PersistPlayer.getByCtx(channel) != null)
            return true;
        return false;
    }
    private void putCache(Channel channel,PlayerEntity entity){
        CorePlayer corePlayer = new CorePlayer();
        corePlayer.setEntity(entity);
        corePlayer.setCtx(channel);
        corePlayer.setId(entity.getId());
        corePlayer.setRoom(null);

        PersistPlayer.putByCtx(channel,corePlayer);
        PersistPlayer.putById(corePlayer.getId(),corePlayer);
    }
    private PlayerDto dto(PlayerEntity entity){
        PlayerDto dto = new PlayerDto();
        return dto;
    }
}
