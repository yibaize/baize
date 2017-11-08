package org.baize.logic.room.impl;

import org.baize.dao.model.CorePlayer;
import org.baize.logic.card.Card;
import org.baize.logic.room.Bottom;
import org.baize.logic.room.IRoom;
import org.baize.server.manager.Response;
import org.baize.utils.DateUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public abstract class CardRoomImpl implements IRoom{
    private int id;//房间号
    private CorePlayer banker;//庄家
    private Set<CorePlayer> playerSet;//所有房间玩家
    private Set<Card> cardSet;//牌堆
    private ConcurrentLinkedQueue<CorePlayer> bankerLineUp;//上庄列表
    private ConcurrentMap<Integer,Bottom> bottom;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public long currentTime() {
        return DateUtils.currentTime();
    }

    @Override
    public long endTime() {
        return DateUtils.getFutureTimeMillis();
    }

    @Override
    public long jackpot() {
        return 0;
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
            notifyAllx(new Response());
            return true;
        }
        return false;
    }
    @Override
    public boolean into(CorePlayer corePlayer){
        if(playerSet.contains(corePlayer))
            return false;
        playerSet.add(corePlayer);
        return true;
    }
    @Override
    public void clear(){
        bottom.clear();
        cardSet.clear();
    }
    /**
     * 这里应该有个网络传输模型参数
     */
    @Override
    public void notifyAllx(Response msg){
        Iterator<CorePlayer> iterator = playerSet.iterator();
        while (iterator.hasNext()){
            //发送消息
            iterator.next().respones(msg);
        }
    }
    /**
     * 通知处自己之外的玩家
     * @param corePlayer
     * @param msg
     */
    @Override
    public void notifyAllx(CorePlayer corePlayer, Response msg){
        Iterator<CorePlayer> iterator = playerSet.iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals(corePlayer)) continue;
            //发送消息
            iterator.next().respones(msg);
        }
    }
    /**
     * 上庄申请
     * @param corePlayer
     * @return
     */
    public boolean bankerLineUp(CorePlayer corePlayer){
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
                notifyAllx(new Response());
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
    public void shuffle(){

    }

    /**
     * 发牌
     */
    public abstract void perflop();

    public void bottom(int position,int num,CorePlayer corePlayer){
        Bottom b = bottom.getOrDefault(position,null);
        if(b == null){
            ConcurrentMap<CorePlayer,Integer> map = new ConcurrentHashMap<>();
            map.put(corePlayer,num);
            b = new Bottom(position,map,new AtomicInteger(num));
            bottom.put(position,b);
        }else {
            b.addCorePlayerBottom(num,corePlayer);
        }
        //TODO 下发各个牌堆的金币和自己在哪下了多少
        notifyAllx(new Response());
    }
}
