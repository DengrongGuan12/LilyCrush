package common.roomInfo;

import java.util.ArrayList;

import common.Info;
import common.settingInfo.PersonalInfo;
import enums.Message;

/*
 * 找到合适的协作游戏，将该游戏的玩家昵称发出
 */
public class FindCooprtGame extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int GameID;
	private PersonalInfo roomHostPersonalInfo;
	public PersonalInfo getRoomHostPersonalInfo() {
		return roomHostPersonalInfo;
	}
	public void setRoomHostPersonalInfo(PersonalInfo roomHostPersonalInfo) {
		this.roomHostPersonalInfo = roomHostPersonalInfo;
	}
	private ArrayList<PersonalInfo> personalsInfo=new ArrayList<PersonalInfo>();
	public ArrayList<PersonalInfo> getPersonalsInfo() {
		return personalsInfo;
	}
	public void setPersonalsInfo(ArrayList<PersonalInfo> personalsInfo) {
		this.personalsInfo = personalsInfo;
	}
	public int getGameID() {
		return GameID;
	}
	public void setGameID(int gameID) {
		GameID = gameID;
	}
	public FindCooprtGame(int GameID,ArrayList<PersonalInfo>personalsInfo,PersonalInfo roomHostPersonalInfo){
		super(Message.findCooprtGame);
		this.setGameID(GameID);
		this.setRoomHostPersonalInfo(roomHostPersonalInfo);
		this.setPersonalsInfo(personalsInfo);
	}

}
