package org.baize.logic.BombFlower.manager;

import org.baize.logic.card.data.Card;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
public class CompareCardSize {
    public static void result(Set<Card> cards){
        boolean result = false;
        Iterator<Card> iterator = cards.iterator();
        int i = 0;
        Card banker = null;
        while (iterator.hasNext()){
            CompareCardType.setType(iterator.next());
            if(i == 0){
                banker = iterator.next();
                continue;
            }
            if(aaa(banker,iterator.next())){
                iterator.next().setResult(true);
                continue;
            }
            if(bomb(banker,iterator.next())){
                iterator.next().setResult(true);
                continue;
            }
            if(obyeAndSume(banker,iterator.next())){
                iterator.next().setResult(true);
                continue;
            }
            if(sume(banker,iterator.next())){
                iterator.next().setResult(true);
                continue;
            }
            if(obye(banker,iterator.next())){
                iterator.next().setResult(true);
                continue;
            }
            if(both(banker,iterator.next())){
                iterator.next().setResult(true);
                continue;
            }
            if(scattered(banker,iterator.next())){
                iterator.next().setResult(true);
                continue;
            }
        }
    }
    private static boolean aaa(Card banker,Card self){
        if(banker.getType()>self.getType())
            self.setResult(false);
        else if(banker.getType()<self.getType()) {
            self.setResult(true);
            return true;
        }
        return false;
    }
    private static boolean bomb(Card banker,Card self){
        if(banker.getCardNum()[0] > self.getCardNum()[0])
            self.setResult(false);
        else {
            self.setResult(true);
            return true;
        }
        return false;
    }
    private static boolean obyeAndSume(Card banker,Card self){
        Arrays.sort(banker.getCardNum());
        Arrays.sort(self.getCardNum());
        if(banker.getCardNum()[2] == 1)
            self.setResult(false);
        else if(self.getCardNum()[2]>self.getCardNum()[2])
            self.setResult(false);
        else if(self.getCardNum()[2] == self.getCardNum()[2])
            self.setResult(false);
        else {
            self.setResult(true);return true;
        }
        return false;
    }
    private static boolean obye(Card banker,Card self){
         return obyeAndSume(banker,self);
    }
    private static boolean sume(Card banker,Card self){
        return scattered(banker,self);
    }
    private static boolean both(Card banker,Card self){
        Arrays.sort(banker.getCardNum());
        Arrays.sort(self.getCardNum());
        int bankerBoth = 0;
        int selfBoth = 0;
        int bankerScattered = 0;
        int selfScattered = 0;
        if(banker.getCardNum()[0] == banker.getCardNum()[1]){
            bankerBoth = banker.getCardNum()[0];
            bankerScattered = banker.getCardNum()[2];
        }else {
            bankerBoth = banker.getCardNum()[1];
            bankerScattered = banker.getCardNum()[0];
        }
        if(self.getCardNum()[0] == self.getCardNum()[1]){
            selfBoth = self.getCardNum()[0];
            selfScattered = self.getCardNum()[2];
        }else {
            selfBoth = self.getCardNum()[1];
            selfScattered = self.getCardNum()[0];
        }
        if(bankerBoth > selfBoth){
            self.setResult(false);
        }else if(bankerBoth == selfBoth){
            if(bankerScattered > selfScattered || bankerScattered == selfScattered) {
                self.setResult(false);
            }
        }
        else {
            self.setResult(true);
            return true;
        }
        return false;
    }
    private static boolean scattered(Card banker,Card self){
        Arrays.sort(banker.getCardNum());
        Arrays.sort(self.getCardNum());
        if(banker.getCardNum()[2] > self.getCardNum()[2]) {
            self.setResult(false);
            return false;
        }
        if(banker.getCardNum()[2] == self.getCardNum()[2]) {
            if(banker.getCardNum()[1] > self.getCardNum()[1]) {
                self.setResult(false);
                return false;
            }
            if(banker.getCardNum()[1] == self.getCardNum()[1]) {
                if(banker.getCardNum()[0] > self.getCardNum()[0] || banker.getCardNum()[0] == self.getCardNum()[0]) {
                    self.setResult(false);
                    return false;
                }
            }
        }
        self.setResult(true);
        return true;
    }
}
