package common.roomInfo;

import common.Info;

import enums.Message;

public class JoinCooprtGame extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int GameID;
	
	public int getGameID() {
		return GameID;
	}

	public void setGameID(int gameID) {
		GameID = gameID;
	}

	public JoinCooprtGame(int GameID){
		super(Message.joinCooprtGame);
		this.setGameID(GameID);
	}

}
