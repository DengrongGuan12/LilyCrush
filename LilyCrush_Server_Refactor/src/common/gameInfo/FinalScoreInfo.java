package common.gameInfo;

import common.Info;

import enums.Message;

public class FinalScoreInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int score;

	public int getScore() {
		return score;
	}
	public FinalScoreInfo(int score){
		super(Message.finalScore);
		this.score=score;
	}
	

}
