package org.baize.room;

import org.baize.EnumType.ResultType;
import org.baize.logic.BombFlower.dto.CardResultNotify;
import org.baize.logic.BombFlower.dto.CardResultsNotify;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.manager.CardManager;
import org.baize.server.manager.Response;
import org.baize.server.session.ISession;
import org.baize.server.session.SessionManager;
import org.baize.utils.DateUtils;
import org.baize.utils.ProtostuffUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：牌局
 */
public class GamblingParty implements  IPlay{
    private RoomAbstract room;
    private BottomPosition bottomPosition;
    private long endTime;
    private boolean isStartBattle;
    private Set<Card> holdCard;
    /**发牌总个数*/
    private int cardNum;
    /**每堆牌个数*/
    private int cardHoldCount;
    public GamblingParty(int cardNum,int cardHoldCount) {
        this.cardNum = cardNum;
        this.cardHoldCount = cardHoldCount;
    }

    @Override
    public void start() {
        endTime = DateUtils.getFutureTimeMillis();
        isStartBattle = true;
        holdCard = CardManager.getInstance().perflop(cardNum,cardHoldCount);
        byte[] buf = ProtostuffUtils.serializer(1);
        stateNotify((short)105,buf);
        room.compare();//比较牌面
    }

    @Override
    public void settleAccounts() {
        //发送牌面
        CardResultsNotify dtos = new CardResultsNotify();
        List<CardResultNotify> r = new ArrayList<>(5);
        for (Card c:holdCard){
            CardResultNotify dto = new CardResultNotify();
            dto.setId(c.getPosition());
            dto.setCardId(c.getId());
            dto.setResult(c.getResult().id());
            dto.setType(c.getCardType().id());
            r.add(dto);
        }
        dtos.setCardResultDtos(r);
        byte[] buf = ProtostuffUtils.serializer(dtos);
        stateNotify((short)106,buf);
        //结算
        for (Card c:holdCard){
            if(c.getResult() == ResultType.Lose) return;
            bottomPosition.settleAccounts(c.getPosition(),c.getCardType().id());
        }
    }
    @Override
    public void end() {
        endTime = 0L;
        isStartBattle = false;
        holdCard = null;
        byte[] buf = ProtostuffUtils.serializer(2);
        stateNotify((short)105,buf);
    }


    public RoomAbstract getRoom() {
        return room;
    }

    public void setRoom(RoomAbstract room) {
        this.room = room;
    }

    public BottomPosition getBottomPosition() {
        if(bottomPosition == null)
            bottomPosition = new BottomPosition(0);
        return bottomPosition;
    }

    public void setBottomPosition(BottomPosition bottomPosition) {
        this.bottomPosition = bottomPosition;
    }

    public long getEndTime() {
        return endTime;
    }


    public boolean isStartBattle() {
        return isStartBattle;
    }
    private void stateNotify(short id,byte[] buf){
        Set<RoomPlayer> players = room.roomPlayer();
        Response response = new Response(id,buf);
        for (RoomPlayer p:players){
            p.getSession().write(response);
        }
    }
    public Set<Card> holdCard(){
        return holdCard;
    }
}
