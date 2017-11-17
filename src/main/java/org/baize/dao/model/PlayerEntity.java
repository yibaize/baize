package org.baize.dao.model;

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
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public SignIn signIn() {
        if(signIn == null)
            signIn = new SignIn();
        return signIn;
    }

    public void setSignIn(SignIn signIn) {
        this.signIn = signIn;
    }
    public PlayerInfo playerInfo() {
        if(playerInfo == null)
            playerInfo = new PlayerInfo();
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public Weath weath() {
        if(weath == null)
            weath = new Weath();
        return weath;
    }


    public void setWeath(Weath weath) {
        this.weath = weath;
    }

    public Shop shop() {
        if(shop == null)
            shop = new Shop();
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Friends friends() {
        if(friends == null)
            friends = new Friends();
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    @Override
    public int compareTo(PlayerEntity o) {
        return playerInfo.getRank() - o.playerInfo().getRank();
    }
}
