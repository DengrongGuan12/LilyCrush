package common.gameInfo;

import java.io.Serializable;

import common.Info;

import enums.Message;

/*
 * 单人点击开始游戏时生成的对象（包括连击时效，提示时间，是否有道具）
 */
public class OneInitGameInfo extends Info implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mulCrash;
	private int promptTime;
	private boolean hasD;
	private int costCoins;
	public int getCostCoins() {
		return costCoins;
	}
	public void setCostCoins(int costCoins) {
		this.costCoins = costCoins;
	}
	public int getMulCrash() {
		return mulCrash;
	}
	public void setMulCrash(int mulCrash) {
		this.mulCrash = mulCrash;
	}
	public int getPromptTime() {
		return promptTime;
	}
	public void setPromptTime(int promptTime) {
		this.promptTime = promptTime;
	}
	public boolean isHasD() {
		return hasD;
	}
	public void setHasD(boolean hasD) {
		this.hasD = hasD;
	}
	public OneInitGameInfo(boolean hasC,boolean hasD,boolean hasE){
		super(Message.initOneGame);
		this.setCostCoins(0);
		if(hasC){
			this.setMulCrash(2);
			this.costCoins+=100;
		}else {
			this.setMulCrash(1);
		}
		if(hasE){
			this.setPromptTime(2);
			this.costCoins+=300;
		}else {
			this.setPromptTime(3);
		}
		this.setHasD(hasD);
		if(hasD){
			this.costCoins+=200;
		}
	}
	

}
