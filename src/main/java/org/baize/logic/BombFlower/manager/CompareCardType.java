package org.baize.logic.BombFlower.manager;

import org.apache.commons.lang3.ArrayUtils;
import org.baize.EnumType.CardType;
import org.baize.logic.card.data.Card;

import java.util.Arrays;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
public class CompareCardType {
    public static void setType(Card card){
        CardType cardType = getType(card.getCardNum(),card.getCardType());
        card.setType(cardType.id());
    }
    private static CardType getType(int[] num,int[] type){
        if(aaa(num)) return CardType.AAA;
        else if (bomb(num)) return CardType.Bomb;
        else if (obyeAndSum(type,num)) return CardType.ObyeAndSume;
        else if (obye(num)) return CardType.Obey;
        else if (sume(type)) return CardType.SumeType;
        else if (both(num)) return CardType.Both;
        return CardType.Scattered;
    }
    private static boolean bomb(int[] num){
        int[] i = new int[]{num[0],num[0],num[0]};
        return ArrayUtils.isEquals(i,num);
    }
    private static boolean aaa(int[] num){
        int[] bomb = new int[]{1,1,1};
        return ArrayUtils.isEquals(bomb,num);
    }
    private static boolean obyeAndSum(int[] type,int[] num){
        return (sume(type) && obye(num));
    }
    private static boolean sume(int[] type){
        int[] i = new int[]{type[0],type[0],type[0]};
        return ArrayUtils.isEquals(type,i);
    }
    private static boolean obye(int[] num){
        Arrays.sort(num);
        int[] n = new int[]{num[0],num[0]+1,num[0]+2};
        return ArrayUtils.isEquals(num,n);
    }
    private static boolean both(int[] num){
        Arrays.sort(num);
        if((num[0] == num[1]) || num[1] == num[2])
            return true;
        return false;
    }
}
