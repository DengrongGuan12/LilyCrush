package common.roomInfo;

import common.friendInfo.AddFriendInfo;

import enums.Message;

public class InviteFriendInfo extends AddFriendInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gameID;

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public InviteFriendInfo(String nickname,String name,int gameId){
		super(nickname, name);
		this.setMessage(Message.inviteFriendCooperate);
		this.setGameID(gameId);
		
	}

}
