package org.baize.dao.manager;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.baize.dao.model.Persist;
import org.baize.dao.model.PlayerEntity;

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
    public PersistPlayerMapper() {
    }

    public PersistPlayerMapper(PlayerEntity entity){
        this.id = entity.getId();
        this.account = entity.getPlayerInfo().getAccount();
        this.playerInfo = JSON.toJSONString(entity.getPlayerInfo());
        this.weath = JSON.toJSONString(entity.getWeath());
        this.shop = JSON.toJSONString(entity.getShop());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(String playerInfo) {
        this.playerInfo = playerInfo;
    }

    public String getWeath() {
        return weath;
    }

    public void setWeath(String weath) {
        this.weath = weath;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }



    public PlayerEntity playerEntity(){
        return new PlayerEntity(this);
    }

    public Persist persist(Persist p){
        Persist persist = null;
        String str = obj(p);
        if(StringUtils.isEmpty(str))
            return null;
        persist = JSON.parseObject(str,p.getClass());
        persist.setId(id);
        return persist;
    }
    public String persistStr(Persist p){
        String perSrt = "{}";
        if(p == null)
            return perSrt;
        perSrt = JSON.toJSONString(p);
        return perSrt;
    }
    private String obj(Persist p){
        String beanName = p.getClass().getSimpleName();
        if(StringUtils.equalsIgnoreCase(beanName,"playerInfo"))
            return this.playerInfo;
        else if(StringUtils.equalsIgnoreCase(beanName,"weath"))
            return this.weath;
        else if(StringUtils.equalsIgnoreCase(beanName,"shop"))
            return this.shop;
        else
            return null;
    }
}
