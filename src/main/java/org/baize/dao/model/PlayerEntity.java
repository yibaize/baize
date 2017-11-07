package org.baize.dao.model;

import org.baize.dao.manager.PersistPlayerMapper;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class PlayerEntity {
    private int id;
    private PlayerInfo playerInfo;
    private Weath weath;
    private Shop shop;

    public PlayerEntity() {
    }

    public PlayerEntity(PersistPlayerMapper mapper){
        this.id = mapper.getId();
        this.playerInfo = (PlayerInfo) mapper.persist(playerInfo);
        this.weath = (Weath) mapper.persist(weath);
        this.shop = (Shop) mapper.persist(shop);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public Weath getWeath() {
        return weath;
    }

    public void setWeath(Weath weath) {
        this.weath = weath;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

}
