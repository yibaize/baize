package org.baize;

import com.alibaba.fastjson.JSON;
import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.PlayerInfo;
import org.baize.dao.model.Weath;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.logic.card.manager.CardManager;
import org.baize.logic.mainroom.friends.module.Friends;
import org.baize.logic.mainroom.shop.module.Shop;
import org.baize.logic.mainroom.signin.module.SignIn;
import org.baize.logic.room.IRoom;
import org.baize.logic.room.RoomFactory;
import org.baize.server.GameServer;
import org.baize.utils.LoggerUtils;
import org.baize.utils.SpringUtils;
import org.baize.utils.createid.CreateIdUtils;
import org.baize.utils.excel.ExcelUtils;
import org.baize.worktask.impl.WorkTaskPoolManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
       SpringUtils.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
//        PersistPlayerMapper playerMapper = new PersistPlayerMapper();
//        playerMapper.setId(221);
//        playerMapper.setAccount("是的发生阿萨德");
//        playerMapper.setPlayerInfo(JSON.toJSONString(new PlayerInfo()));
//        playerMapper.setWeath(JSON.toJSONString(new Weath()));
//        playerMapper.setShop(JSON.toJSONString(new Shop()));
//        playerMapper.setSignIn(JSON.toJSONString(new SignIn()));
//        playerMapper.setFriends(JSON.toJSONString(new Friends()));
//        mapper.insert(playerMapper);
        ExcelUtils.init();
        IRoom room = RoomFactory.getInstance().getBean(3);
        room.perflop();
       GameServer.start();
    }
}
