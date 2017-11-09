package org.baize.dao.model;

import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.logic.mainroom.friends.module.Friends;
import org.baize.logic.mainroom.shop.module.Shop;
import org.baize.logic.mainroom.signin.module.SignIn;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class PlayerEntity implements Comparable<PlayerEntity>{
    private int id;
    private PlayerInfo playerInfo;
    private Weath weath;
    private Shop shop;
    private Friends friends;
    private SignIn signIn;
    public PlayerEntity() {
    }

    public PlayerEntity(PersistPlayerMapper mapper){
        this.id = mapper.getId();
        this.playerInfo = (PlayerInfo) mapper.persist(new PlayerInfo());
        this.weath = (Weath) mapper.persist(new Weath());
        this.shop = (Shop) mapper.persist(new Shop());
        this.friends = (Friends)mapper.persist(new Friends());
        this.signIn = (SignIn)mapper.persist(new SignIn());
    }
    public int getId() {
        return id;
    }

    public void setId(PlayerEntity entity,int id) {
        this.shop.setEntity(entity);
        this.weath.setEntity(entity);
        this.signIn.setEntity(entity);
        this.playerInfo.setEntity(entity);
        this.friends.setEntity(entity);
        this.id = id;
    }

    public SignIn getSignIn() {
        return signIn;
    }

    public void setSignIn(SignIn signIn) {
        this.signIn = signIn;
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

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    @Override
    public int compareTo(PlayerEntity o) {
        return playerInfo.getRank() - o.getPlayerInfo().getRank();
    }
}
