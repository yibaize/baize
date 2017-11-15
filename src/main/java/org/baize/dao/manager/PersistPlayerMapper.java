package org.baize.dao.manager;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.baize.dao.model.Persist;
import org.baize.dao.model.PlayerEntity;
import org.baize.dao.model.PlayerInfo;
import org.baize.dao.model.Weath;
import org.baize.error.Error;
import org.baize.logic.mainroom.friends.module.Friends;
import org.baize.logic.mainroom.shop.module.Shop;
import org.baize.logic.mainroom.signin.module.SignIn;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class PersistPlayerMapper {
    private int id;
    private String account;
    private String playerInfo;
    private String weath;
    private String shop;
    private String signIn;
    private String friends;
    public PersistPlayerMapper() {
    }

    public PersistPlayerMapper(PlayerEntity entity){
        this.id = entity.getId();
        this.account = entity.getPlayerInfo().getAccount();
        this.playerInfo = JSON.toJSONString(entity.getPlayerInfo());
        this.weath = JSON.toJSONString(entity.getWeath());
        this.shop = JSON.toJSONString(entity.getShop());
        this.signIn = JSON.toJSONString(entity.getSignIn());
        this.friends = JSON.toJSONString(entity.getFriends());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerEntity playerEntity(){
        PlayerEntity entity = new PlayerEntity();
        entity.setId(this.getId());
        entity.setPlayerInfo((PlayerInfo) this.persist(new PlayerInfo()));
        entity.setWeath((Weath) this.persist(new Weath()));
        entity.setShop((Shop) this.persist(new Shop()));
        entity.setFriends((Friends)this.persist(new Friends()));
        entity.setSignIn((SignIn)this.persist(new SignIn()));
        entity.getSignIn().player(entity);
        return entity;
    }

    public Persist persist(Persist p){
        Persist persist = null;
        String str = obj(p);
        if(StringUtils.isEmpty(str) || str.equals("{}") || str.equals("null")) {
            try {
                persist = p.getClass().newInstance();
            } catch (Exception e) {
                new Error(this.getClass()).err(1);
            }
        }
        persist = JSON.parseObject(str,p.getClass());
        persist.setId(id);
        return persist;
    }
    private String obj(Persist p){
        String beanName = p.getClass().getSimpleName();
        if(StringUtils.equalsIgnoreCase(beanName,"playerInfo"))
            return this.playerInfo;
        else if(StringUtils.equalsIgnoreCase(beanName,"weath"))
            return this.weath;
        else if(StringUtils.equalsIgnoreCase(beanName,"shop"))
            return this.shop;
        else if(StringUtils.equalsIgnoreCase(beanName,"signIn"))
            return this.signIn;
        else if(StringUtils.equalsIgnoreCase(beanName,"friends"))
            return this.friends;
        else
            return null;
    }

    public static void main(String[] args) {
        System.out.println("xxxxx");
    }
}
