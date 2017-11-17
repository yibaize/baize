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
public class CommandRecive{
	private static CommandRecive instance;
	public static CommandRecive getInstance(){
		if(instance == null)
			instance = new CommandRecive();
		return instance;
	}
	public CommandAb recieve(int id,String[] params){
		switch (id){
			case 1:
				return getLogin(params);
			case 2:
				return getInfo_Reset(params);
			case 3:
				return getFriend_Add(params);
			case 4:
				return getFriend_Remove(params);
			case 5:
				return getFriend_Consent(params);
			case 6:
				return getFriend_Chat(params);
			case 7:
				return getFriend_Select(params);
			case 8:
				return getRank_Select(params);
			case 9:
				return getSignIn_Award(params);
			case 10:
				return getShop_Buy(params);
			case 11:
				return getTree_Draw(params);
			case 12:
				return getPlayerBottom(params);
			case 13:
				return getChange_Room(params);
			default:
				return null;
		}
	}
	private CommandAb getLogin(String[] params){
		int value1 = Integer.parseInt(params[1]);
		String value2 = params[2];
		String value3 = params[3];
		return new Login(value1,value2,value3);
	}
	private CommandAb getInfo_Reset(String[] params){
		String value1 = params[1];
		String value2 = params[2];
		String value3 = params[3];
		int value4 = Integer.parseInt(params[4]);
		String value5 = params[5];
		return new Info_Reset(value1,value2,value3,value4,value5);
	}
	private CommandAb getFriend_Add(String[] params){
		int value1 = Integer.parseInt(params[1]);
		return new Friend_Add(value1);
	}
	private CommandAb getFriend_Remove(String[] params){
		int value1 = Integer.parseInt(params[1]);
		return new Friend_Remove(value1);
	}
	private CommandAb getFriend_Consent(String[] params){
		int value1 = Integer.parseInt(params[1]);
		return new Friend_Consent(value1);
	}
	private CommandAb getFriend_Chat(String[] params){
		int value1 = Integer.parseInt(params[1]);
		String value2 = params[2];
		return new Friend_Chat(value1,value2);
	}
	private CommandAb getFriend_Select(String[] params){
		return new Friend_Select();
	}
	private CommandAb getRank_Select(String[] params){
		return new Rank_Select();
	}
	private CommandAb getSignIn_Award(String[] params){
		return new SignIn_Award();
	}
	private CommandAb getShop_Buy(String[] params){
		int value1 = Integer.parseInt(params[1]);
		int value2 = Integer.parseInt(params[2]);
		return new Shop_Buy(value1,value2);
	}
	private CommandAb getTree_Draw(String[] params){
		return new Tree_Draw();
	}
	private CommandAb getPlayerBottom(String[] params){
		int value1 = Integer.parseInt(params[1]);
		int value2 = Integer.parseInt(params[2]);
		return new PlayerBottom(value1,value2);
	}
	private CommandAb getChange_Room(String[] params){
		int value1 = Integer.parseInt(params[1]);
		return new Change_Room(value1);
	}
}
