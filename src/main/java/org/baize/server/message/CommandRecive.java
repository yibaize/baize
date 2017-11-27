package org.baize.server.message;
import org.baize.logic.login.command.Login;
import org.baize.logic.mainroom.info.Info_Reset;
import org.baize.logic.mainroom.friends.command.Friend_Add;
import org.baize.logic.mainroom.friends.command.Friend_Remove;
import org.baize.logic.mainroom.friends.command.Friend_Consent;
import org.baize.logic.mainroom.friends.command.Friend_Chat;
import org.baize.logic.mainroom.friends.command.Friend_Select;
import org.baize.logic.mainroom.rank.command.Rank_Select;
import org.baize.logic.mainroom.signin.command.SignIn_Award;
import org.baize.logic.mainroom.shop.command.Shop_Buy;
import org.baize.logic.mainroom.moneyTree.command.Tree_Draw;
import org.baize.logic.bottom.PlayerBottom;
import org.baize.logic.changeroom.Change_Room;
import org.baize.logic.outline.Out_line;
public class CommandRecive{
	private static CommandRecive instance;
	public static CommandRecive getInstance(){
		if(instance == null)
			instance = new CommandRecive();
		return instance;
	}
}
