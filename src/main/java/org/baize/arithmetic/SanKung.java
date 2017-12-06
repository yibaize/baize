package org.baize.arithmetic;
import org.apache.commons.lang3.ArrayUtils;
import org.baize.logic.card.data.Card;
import java.util.Arrays;

/**
 * 作者： 白泽
 * 时间： 2017/11/24.
 * 描述：
 */
public class SanKung {
    private static int count, cardsTotal;//count:大于等于10的有几张 cardsTotal:总数和 cow：牛几
    private static int cow = -1;
/**==========================================================检查牌型===================================================================*/
    /**
     * 检查是否都大于等于10
     */
    public static int checkType(int[] card) {
        int[] card1 = new int[3];
        int[] bomb = new int[3];
        Arrays.sort(card);
        for (int i = 0; i < card.length; i++) {
            bomb[i] = card[0];
            card1[i] = card[2 - i];
        }
        for (int i : card) {
            cardsTotal += i;
            if (i >= 10)
                count++;
        }
        //炸
        if(ArrayUtils.isEquals(card,bomb))
            return 20;
        return checkCount(card1);
    }

    private static int checkCount(int[] card) {
        switch (count) {
            case 0:
                int j = 0;
                for (int i = 0; i < card.length; i++) {
                    j += card[i];
                }
                cow = j % 10;
                break;
            case 1:
                cow = (card[1] + card[2]) % 10;
                break;
            case 2:
                cow = card[2] % 10;
                break;
            case 3:
                if (!ArrayUtils.contains(card, 10))
                    cow = 0;
                else
                    cow = -1;
                break;
        }
        return cow;
    }
/**==========================================================比较大小===================================================================*/
    public static boolean checkSize(Card bankers, Card others){
        int bankerSize = checkType(bankers.getCardIds());
        int otherSize = checkType(others.getCardIds());
        if(bankerSize != otherSize)
            return otherSize > bankerSize;
        if(otherSize == bankerSize)
            return BomFlower.scatteredSize(bankers,others);
        return otherSize > bankerSize;
    }
    public static void main(String[] args) {
        int[] i = new int[]{10, 10, 10};
        System.out.println(checkType(i));
    }
}
