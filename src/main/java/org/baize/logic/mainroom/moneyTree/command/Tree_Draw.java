package org.baize.logic.mainroom.moneyTree.command;

import org.baize.dao.model.Weath;
import org.baize.error.Error;
import org.baize.logic.mainroom.moneyTree.data.MoneyTreeDataTable;
import org.baize.server.message.CommandAb;
import org.baize.utils.DateUtils;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/10.
 * 描述：
 */
@Protocol(id = "11")
public class Tree_Draw extends CommandAb {
    @Override
    public void execute() {
        Weath weath = player().entity().weath();
        long currentTime = DateUtils.currentTime();
        long lastTime = weath.getLastDrawTime();
        long distanceTime = currentTime - lastTime;
        distanceTime = distanceTime/3600000;
        if(distanceTime<=0)
            new Error(this.getCtx()).err(1);
        distanceTime = distanceTime > 24 ? 24 : distanceTime;
        MoneyTreeDataTable dataTable = MoneyTreeDataTable.get((int) distanceTime);
        weath.increaseGold(dataTable.getDrawNum());
        weath.update();
    }
}
