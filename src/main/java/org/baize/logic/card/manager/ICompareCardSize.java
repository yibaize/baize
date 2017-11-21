package org.baize.logic.card.manager;

import org.baize.EnumType.ResultType;
import org.baize.logic.BombFlower.manager.CompareCardType;
import org.baize.logic.card.data.Card;

import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/20.
 * 描述：
 */
public interface ICompareCardSize {
    default void result(Set<Card> set){
        boolean result = false;
        List<Card> ilist = new ArrayList<>(set);
        Collections.sort(ilist);
        Iterator<Card> iterator = ilist.iterator();
        int i = 0;
        Card banker = null;
        Card other = null;
        while (iterator.hasNext()){
            if(i == 0){
                banker = iterator.next();
                CompareCardType.setType(banker);
                i++;
                continue;
            }
            other = iterator.next();
            CompareCardType.setType(other);
            if(other.getType() > banker.getType()){
                other.setResult(ResultType.Win.id());
            }else if(other.getType() < banker.getType()){
                other.setResult(ResultType.Lose.id());
            }
            else if(other.getType() == banker.getType()) {
                switch (other.getType()){
                    case 1:
                        scattered(banker, other);
                        break;
                    case 2:
                        both(banker, other);
                        break;
                    case 3:
                        obye(banker, other);
                        break;
                    case 4:
                        sume(banker, other);
                        break;
                    case 5:
                        obyeAndSume(banker, other);
                        break;
                    case 6:
                        bomb(banker, other);
                        break;
                    case 7:
                        aaa(banker, other);
                }
            }
            i++;
        }
    }
    default void aaa(Card banker,Card self){
        if(banker.getType()>self.getType())
            self.setResult(ResultType.Lose.id());
        else if(banker.getType()<self.getType())
            self.setResult(ResultType.Win.id());
    }
    default void bomb(Card banker,Card self){
        if(banker.getCardNum()[0] > self.getCardNum()[0])
            self.setResult(ResultType.Lose.id());
        else
            self.setResult(ResultType.Win.id());
    }
    default void obyeAndSume(Card banker,Card self){
        Arrays.sort(banker.getCardNum());
        Arrays.sort(self.getCardNum());
        if(banker.getCardNum()[2] == 1)
            self.setResult(ResultType.Lose.id());
        else if(banker.getCardNum()[2]>self.getCardNum()[2])
            self.setResult(ResultType.Lose.id());
        else if(banker.getCardNum()[2] == self.getCardNum()[2])
            self.setResult(ResultType.Lose.id());
        else {
            self.setResult(ResultType.Win.id());
        }
    }
    default void obye(Card banker,Card self){
        obyeAndSume(banker,self);
    }
    default void sume(Card banker,Card self){
        scattered(banker,self);
    }
    default void both(Card banker,Card self){
        Arrays.sort(banker.getCardNum());
        Arrays.sort(self.getCardNum());
        int bankerBoth = both(banker)[0];
        int bankerScattered = both(banker)[1];
        int selfBoth = both(self)[0];
        int selfScattered = both(self)[1];

        if(bankerBoth > selfBoth){
            self.setResult(ResultType.Lose.id());
        }else if(bankerBoth == selfBoth){
            if(bankerScattered > selfScattered || bankerScattered == selfScattered) {
                self.setResult(ResultType.Lose.id());
            }
        }
        else {
            self.setResult(ResultType.Win.id());
        }
    }
    default int[] both(Card card){
        int[] both = new int[2];
        if(card.getCardNum()[0] == card.getCardNum()[1]){
            both[0] = card.getCardNum()[0];
            both[1] = card.getCardNum()[2];
        }else {
            both[0] = card.getCardNum()[1];
            both[1] = card.getCardNum()[0];
        }
        return both;
    }
    default void scattered(Card banker,Card self){
        Arrays.sort(banker.getCardNum());
        Arrays.sort(self.getCardNum());
        if(banker.getCardNum()[2] > self.getCardNum()[2]) {
            self.setResult(ResultType.Lose.id());
        }
        else if(banker.getCardNum()[2] == self.getCardNum()[2]) {
            if(banker.getCardNum()[1] > self.getCardNum()[1]) {
                self.setResult(ResultType.Lose.id());
            }
            if(banker.getCardNum()[1] == self.getCardNum()[1]) {
                if(banker.getCardNum()[0] > self.getCardNum()[0] || banker.getCardNum()[0] == self.getCardNum()[0]) {
                    self.setResult(ResultType.Lose.id());
                }
            }
        }else
            self.setResult(ResultType.Win.id());
    }
}
