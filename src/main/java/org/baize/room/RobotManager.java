package org.baize.room;

import org.baize.server.GameServer;
import org.baize.server.session.SessionManager;
import org.baize.worktask.ISecondTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
@Service
public class RobotManager implements ISecondTimer {
    private List<GamblingParty> gamblingParty;
    private int timer;
    @Override
    public void executor() {
        System.out.println(timer+":"+ SessionManager.onLinePlayerNum());
        if(gamblingParty == null) {
            gamblingParty = new ArrayList<>(5);
            List<RoomAbstract> rooms = RoomFactory.getInstance().allRoom();
            for (RoomAbstract r : rooms) {
                if(r.getGamblingParty() != null)
                    gamblingParty.add(r.getGamblingParty());
            }
        }
        if(timer == 2){
            for (GamblingParty g:gamblingParty){
                g.start();
            }
        }else if(timer == 15) {
            for (GamblingParty g:gamblingParty){
                g.settleAccounts();
            }
        }else if(timer == 20) {
            for (GamblingParty g:gamblingParty){
                g.end();
                timer = 0;
            }
        }
        timer++;
    }
}
