package org.baize.arithmetic;
import org.baize.logic.card.data.Card;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/11/24.
 * 描述：牛牛算法
 */
public class Cow {
    public enum CowCow {
        /**没牛*/
        Miss(-1),
        /**牛牛*/
        SanKong(0),
        /**有牛*/
        CowNuw(10),
        /**炸弹*/
        Bomb(20);
        private int id;
        private CowCow(int id) {
            this.id = id;
        }
        public int id(){
            return id;
        }
        private static final Map<Integer,CowCow> MAP = new HashMap<>();
        public static CowCow getType(int id){
            return MAP.getOrDefault(id,Miss);
        }
        static {
            for (CowCow t: CowCow.values()){
                MAP.put(t.id,t);
            }
        }
    }

    private static int count,cardsTotal;//count:大于等于10的有几张 cardsTotal:总数和 cow：牛几
    private static int cow = -1;
    /**
     * 检查是否都大于等于10
     */
    public static int check(int[] card){
        int[] card1 = new int[5];
        Arrays.sort(card);
        for (int i = 0;i<card.length;i++){
            card1[i] = card[4-i];
        }
        for (int i:card){
            cardsTotal += i;
            if(i >= 10)
                count++;
        }
        return checkCount(card1);
    }

    /**
     * 牛牛
     */
    private static int checkCount(int[] card){
        if(count == 5)
            return 0;
        switch (count){
            case 0:
                for (int i = 0;i<card.length-1;i++){
                    for (int j = 1;j<card.length;j++){
                        if((cardsTotal - card[i] - card[j]) % 10 == 0)
                            cow = (card[i] + card[j]) % 10;
                    }
                }
                return cow;
            case 1://有一张jqk10的情况,剩余4张中有3张之和要被10整除
                for(int j=0;j<5;j++ ){
                    if((cardsTotal-card[0]-card[j])%10==0){
                        cow = (cardsTotal-card[0])%10;
                    }
                }
                return cow;
            case 2://有2张jqk10的情况,剩余3张中有2张之和等于10或者剩余3张之和为10
                if((card[2]+card[3]+card[4]) % 10 == 0){
                    cow = 0;
                }else {
                    for (int i = count; i < 4; i++) {
                        for (int j = count + 1; j < 5; j++) {
                            if (i == j) continue;
                            if ((card[i] + card[j]) == 10 || (cardsTotal - card[i] - card[j]) % 10 == 0) {
                                for (int k = 0; k < count; k++) {
                                    cardsTotal -= card[k];
                                }
                                cow = cardsTotal % 10;
                            }
                        }
                    }
                }
                return cow;
            default://有3,4,5张jqk10的情况

                for(int i=0;i<count;i++){
                    cardsTotal -= card[i];
                }
                cow = cardsTotal%10;
                return cow;
        }
    }
    /**==========================================================比较大小===================================================================*/
    public static boolean compareTo(Card bankerx,Card other){
        int banerSize = check(bankerx.getCardIds());
        int otherSize = check(other.getCardIds());
        if(banerSize == 0)
            return false;
        else if(banerSize != 0 && otherSize == 0)
            return true;
        return otherSize > banerSize;

    }
    public static void main(String[] args) {
        System.out.println( 3 > 3);
    }
}
