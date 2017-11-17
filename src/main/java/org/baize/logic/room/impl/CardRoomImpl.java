package org.baize.logic.room.impl;

import org.apache.ibatis.jdbc.Null;
import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.Weath;
import org.baize.logic.BombFlower.dto.OpeningDto;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.data.PersistCard;
import org.baize.logic.card.manager.CardManager;
import org.baize.logic.room.RoomBottom;
import org.baize.logic.room.RoomBottomDto;
import org.baize.logic.room.IRoom;
import org.baize.worktask.ISecondTimer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public abstract class CardRoomImpl implements IRoom{
    protected int id;//房间号
    private CorePlayer banker;//庄家
    private Set<CorePlayer> playerSet;//所有房间玩家
    private Set<Card> cardSet;//牌堆
    private ConcurrentLinkedQueue<CorePlayer> bankerLineUp;//上庄列表
    private ConcurrentMap<Integer,RoomBottom> bottom;

    public CardRoomImpl(int id) {
        this.id = id;
        if(this.playerSet == null)
            playerSet = new HashSet<>();
    }

    public Set<Card> getCardSet() {
        return cardSet;
    }

    public void setCardSet(Set<Card> cardSet) {
        this.cardSet = cardSet;
    }

    @Override
    public int getId() {
        return id;
    }



    public long jackpot() {
        return 0;
    }

    @Override
    public Set<CorePlayer> getSet() {
        return playerSet;
    }

    @Override
    public boolean leave(CorePlayer corePlayer){
        if(playerSet.contains(corePlayer)) {
            playerSet.remove(corePlayer);
            if(banker == corePlayer) {
                banker = null;
                bankerUp();
            }
            if(bankerLineUp.contains(corePlayer))
                bankerLineUp.remove(corePlayer);
            //TODO 下发人数减少
            //移除下注
            RoomBottom b = bottom.get(getId());
            b.leave(corePlayer);

            notifyAllx((short)101,corePlayer,null);
            return true;
        }
        return false;
    }
    public void clear(){
        bottom.clear();
        cardSet.clear();
    }

    /**
     * 上庄申请
     * @param corePlayer
     * @return
     */
    public boolean bankerLineUp(CorePlayer corePlayer){
        if(bankerLineUp == null)
            bankerLineUp = new ConcurrentLinkedQueue<>();
        if(bankerLineUp.contains(corePlayer)){
            bankerLineUp.offer(corePlayer);
            return true;
        }
        return false;
    }
    private boolean checkBanker(){
        return banker == null;
    }

    /**
     * 需要同步
     * @return
     */
    private boolean bankerUp(){
        synchronized (banker) {
            if (checkBanker() && bankerLineUp != null || bankerLineUp.size() > 0) {
                banker = bankerLineUp.poll();
                //TODO 通知换换庄
                notifyAllx((short)103,null);
                return true;
            }
            return false;
        }
    }
    public boolean bankerDown(CorePlayer corePlayer){
        if(corePlayer.equals(banker)) {
            banker = null;
            bankerUp();
            return true;
        }
        return false;
    }

    /**
     * 洗牌
     */
    public List<PersistCard> shuffle(){
        List<PersistCard> cards = CardManager.getInstance().getCardPool();
        Collections.shuffle(cards);
        return cards;
    }

    /**
     * 发牌
     */
    public abstract void perflop();
    @Override
    public void bottom(int position,int num,CorePlayer corePlayer){
        RoomBottom b = bottom.getOrDefault(position,null);
        if(b == null){
            ConcurrentMap<CorePlayer,Integer> map = new ConcurrentHashMap<>();
            map.put(corePlayer,num);
            b = new RoomBottom(position,map,new AtomicInteger(num));
            bottom.put(position,b);
        }else {
            b.addCorePlayerBottom(num,corePlayer);
        }
        if(!hasBottom.get());{
            hasBottom.compareAndSet(false,true);
        }
    }
    protected void start(int type){
        OpeningDto dto = new OpeningDto(type);
        notifyAllx((short)105,dto);
    }
    @Override
    public void settleAccounts(){
        Iterator<Card> iterator = getCardSet().iterator();
        while (iterator.hasNext()){
            Card card = iterator.next();
            if(card.isResult()){
                RoomBottom b = bottom.getOrDefault(card.getId(),null);
                if(b == null)
                    return;
                for (Map.Entry<CorePlayer,Integer> e:b.getMap().entrySet()){
                    Weath w = e.getKey().entity().weath();
                    w.increaseGold(e.getValue() * card.getType());
                    w.update();
                }
            }
        }
    }
    private AtomicBoolean hasBottom = new AtomicBoolean(false);
    //TODO 下发各个牌堆的金币和自己在哪下了多少
    protected RoomBottomDto bottomNotify(){
        if(!hasBottom.get()) return null;
        Map<Integer,Integer> money = new HashMap<>(4);
        for (Map.Entry<Integer,RoomBottom> e:bottom.entrySet()){
            money.put(e.getKey(),e.getValue().allNum());
        }
        RoomBottomDto dto = new RoomBottomDto(money);
        notifyAllx((short)104,dto);
        return dto;
    }

    @Override
    public CorePlayer banker() {
        return banker;
    }

    @Override
    public List<CorePlayer> bankerUpList() {
        List<CorePlayer> bankers = new ArrayList<>(bankerLineUp.size());
        Iterator<CorePlayer> iterator = bankerLineUp.iterator();
        while (iterator.hasNext()){
            bankers.add(iterator.next());
        }
        return bankers;
    }

    @Override
    public RoomBottomDto roomBottom() {
        return bottomNotify();
    }
}
