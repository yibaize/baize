package org.baize.logic.mainroom.signin.module;

import org.baize.dao.model.Persist;
import org.baize.dao.model.PlayerEntity;
import org.baize.dao.model.Weath;
import org.baize.logic.mainroom.signin.data.SignInDataTable;
import org.baize.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
public class SignIn extends Persist {
    private boolean draw;
    private int week;
    private List<Integer> weekDay;
    private transient PlayerEntity player;

    public SignIn() {
    }

    public PlayerEntity player() {
        return player;
    }

    public void player(PlayerEntity player) {
        this.player = player;
    }


    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<Integer> getWeekDay() {
        return weekDay;
    }
    public List<Integer> weekDay() {
        if(this.weekDay == null)
            weekDay = new ArrayList<>(7);
        return weekDay;
    }

    public void setWeekDay(List<Integer> weekDay) {
        this.weekDay = weekDay;
    }
    public boolean draw(){
        int teday = DateUtils.week();
        if(!isDraw() && teday == week)
            return false;
        draw = true;
        week = teday;
        SignInDataTable signInDataTable = SignInDataTable.get(teday);
        if(signInDataTable == null)
            return false;
        int award = signInDataTable.getDraw();
        Weath weath = player().getWeath();
        weath.increaseGold(award);
        weath.update();
        weekDay().add(teday);
        return true;
    }
    public boolean hasDraw(){
        if(DateUtils.week() != week)
            draw = false;
        return draw;
    }
}
