package org.baize.logic.login.command;

import io.netty.channel.Channel;
import org.baize.EnumType.LoginType;
import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PlayerEntity;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.server.message.CommandAb;
import org.baize.utils.SpringUtils;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@Protocol(id = "1")
public class Login extends CommandAb{
    private final int loginType;
    private final String account;
    private final String password;

    public Login(int loginType, String account, String password) {
        this.loginType = loginType;
        this.account = account;
        this.password = password;
    }

    @Override
    public void execute() {
        PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
        PersistPlayerMapper playerMapper = mapper.selectOneForAccount(account);
        if(playerMapper == null){
            if(loginType != LoginType.Account.id()){
                //注册
            }else {
                //账号不存在
            }
        }
        PlayerEntity entity = playerMapper.playerEntity();
        if(entity == null){
            //数据异常
        }
        if(loginType == LoginType.Account.id()){
            if(!password.equals(entity.getPlayerInfo().getPassword()))
                System.out.println("账号不正确");
        }
        CorePlayer corePlayer = new CorePlayer();
        corePlayer.setEntity(entity);
        corePlayer.setCtx(this.getCtx());
        corePlayer.setId(entity.getId());
        //TODO
        corePlayer.setRoom(null);
    }
}
