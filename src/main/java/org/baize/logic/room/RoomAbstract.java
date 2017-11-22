package org.baize.logic.room;

import org.baize.EnumType.ResultType;
import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PersistPlayer;
import org.baize.logic.BombFlower.dto.OpeningDto;
import org.baize.logic.card.data.Card;
import org.baize.logic.room.impl.BottomPosition;
import org.baize.server.GameServer;
import org.baize.server.message.IProtostuff;
import org.baize.utils.DateUtils;
import org.baize.worktask.ISecondTimer;

import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/21.
 * 描述：
 */

public abstract class RoomAbstract implements ISecondTimer{
    /**房间id*/
    private final int roomId;
    /**房间里的玩家*/
    private Set<CorePlayer> roomPlayers;
    /**房间玩法*/
    private IBottom roomBottom;
    /**房间局结束时间*/
    private long roomEndTime;
    /**房间开局状态*/
    private boolean roomBattleState;
    private final PlayAbstract play;

    public RoomAbstract(int roomId,PlayAbstract play,IBottom roomBottom) {
        this.roomId = roomId;
        this.roomBottom = roomBottom;
        if(roomPlayers == null)
            roomPlayers = new HashSet<>();
        roomBottom.players(roomPlayers);
        play.roomPlayers(roomPlayers);
        this.play = play;
    }

    /**
     * 房间当前时间
     * @return
     */
    public long roomCurrentTime(){
        return DateUtils.currentTime();
    }
    public long roomEndTime(){
        return roomEndTime;
    }
    public boolean isRoomBattleState(){
        return roomBattleState;
    }
    /**
     * 房间玩家数量
     * @return
     */
    public int roomPlayerCount(){
        return roomPlayers.size();
    }
    public IBottom getRoomBottom() {
        return roomBottom;
    }

    public void setRoomBottom(IBottom roomBottom) {
        this.roomBottom = roomBottom;
    }

    public int getRoomId() {
        return roomId;
    }
    public PlayAbstract getPlay() {
        return play;
    }

    public void setRoomBattleState(boolean roomBattleState) {
        this.roomBattleState = roomBattleState;
    }

    public void bottom(int position, int num, CorePlayer corePlayer){
        roomBottom.bottom(position,num,corePlayer);
    }

    /**
     * 正在局中
     */
    public abstract void battling();
    /**
     * 玩家进入房间
     * @param player 进入的玩家
     */
    public void intoRoom(CorePlayer player){
        if(roomPlayers == null)
            roomPlayers = new HashSet<>();
        if(!roomPlayers.contains(player)) {
            roomPlayers.add(player);
            Set<CorePlayer> playerSet = PersistPlayer.playerByNotSelf(player,roomPlayers);
            GameServer.notifyAllx(playerSet,(short)101,null);
        }
    }

    /**
     * 玩家离开房间
     * @param player
     */
    public void leaveRoom(CorePlayer player){
        if(roomPlayers.contains(player))
            roomPlayers.remove(player);
        roomBottom.leave(player);
        Set<CorePlayer> playerSet = PersistPlayer.playerByNotSelf(player,roomPlayers);
        GameServer.notifyAllx(playerSet,(short)102,null);
    }


    /**
     * 开局
     */
    public void startBattle(){
        play.shuffle();
        roomEndTime = DateUtils.getFutureTimeMillis();
        roomBattleState = true;
        ntf(1);
    }


    /**
     * 结束
     */
    public void endBattle(){
        roomBottom.clearBottom();
        roomEndTime = 0;
        ntf(2);
    }
    private void ntf(int type){
        OpeningDto dto = new OpeningDto(type);
        notifyAllx((short)105,dto);
    }

    protected void settleAccounts(){
        Iterator<Card> iterator = play.getCardSet().iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getResult() == ResultType.Win.id()) {
                if(roomBottom.bottomMap().containsKey(card.getId())){
                    BottomPosition b = roomBottom.bottomMap().get(card);
                    b.settleAccounts(card.getType());
                }
            }
        }
        //结算
        GameServer.notifyAllx(roomPlayers,(short)108,null);
    }
    private int timer = 0;
    @Override
    public void executor() {
        System.out.println(timer+":"+this.roomId);
        if(timer == 2){
            startBattle();//开局
        }
        if(timer == 15){
            //发送本剧开牌结果
            battling();
        }
        if(timer == 17){
            //结算
            settleAccounts();
        }
        if(timer == 20){
            endBattle();//结束
            timer = 0;
        }
        timer++;
    }
    public void notifyAllx(short id,IProtostuff pro){
        if(roomPlayers != null)
            GameServer.notifyAllx(roomPlayers,id,pro);
    }
    /**
     * 通知处自己之外的玩家
     * @param corePlayer
     * @param
     */
    public void notifyAllx(short id,CorePlayer corePlayer,IProtostuff pro){
        if(roomPlayers != null) {
            Set<CorePlayer> playerByNotSelf = PersistPlayer.playerByNotSelf(corePlayer,roomPlayers);
            GameServer.notifyAllx(playerByNotSelf,id,pro);
        }
    }
}
