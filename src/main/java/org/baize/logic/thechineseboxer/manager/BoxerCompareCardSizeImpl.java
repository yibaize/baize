package org.baize.logic.thechineseboxer.manager;

import org.baize.EnumType.ResultType;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.manager.ICompareCardSize;

import java.util.Arrays;

/**
 * 作者： 白泽
 * 时间： 2017/11/20.
 * 描述：
 */
public class BoxerCompareCardSizeImpl implements ICompareCardSize {
    public void obyeAndSume(Card banker,Card self){
        Arrays.sort(banker.getCardNum());
        Arrays.sort(self.getCardNum());
        if(banker.getCardNum()[2] == 1)
            self.setResult(ResultType.Lose.id());
        else if(banker.getCardNum()[2]>self.getCardNum()[2])
            self.setResult(ResultType.Lose.id());
        else if(banker.getCardNum()[2] == self.getCardNum()[2])
            self.setResult(ResultType.Draw.id());
        else {
            self.setResult(ResultType.Win.id());
        }
    }
    public void obye(Card banker,Card self){
        obyeAndSume(banker,self);
    }
    public void sume(Card banker,Card self){
        scattered(banker,self);
    }
    public void both(Card banker,Card self){
        Arrays.sort(banker.getCardNum());
        Arrays.sort(self.getCardNum());
        int bankerBoth = both(banker)[0];
        int bankerScattered = both(banker)[1];
        int selfBoth = both(self)[0];
        int selfScattered = both(self)[1];

        if(bankerBoth > selfBoth){
            self.setResult(ResultType.Lose.id());
        }else if(bankerBoth == selfBoth){
            if(bankerScattered > selfScattered) {
                self.setResult(ResultType.Lose.id());
            }else if(bankerScattered == selfScattered){
                self.setResult(ResultType.Draw.id());
            }
        }
        else {
            self.setResult(ResultType.Win.id());
        }
    }
    public void scattered(Card banker,Card self){
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
                if(banker.getCardNum()[0] > self.getCardNum()[0])
                    self.setResult(ResultType.Lose.id());
                else if(banker.getCardNum()[0] == self.getCardNum()[0])
                    self.setResult(ResultType.Draw.id());
               else
                    self.setResult(ResultType.Win.id());
            }
        }else
            self.setResult(ResultType.Win.id());
    }
}
