package org.baize;

import org.baize.server.GameServer;
import org.baize.utils.SpringUtils;
import org.baize.utils.excel.ExcelUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

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
        GameServer.start();
    }
}
