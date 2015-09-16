package common.roomInfo;

import common.Info;

import enums.Message;

/*
 * 创建协作游戏请求
 */
public class CooprtGameInitInfo extends Info{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mulCrash;
	private int promptTime;
	private boolean hasD;
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
	public CooprtGameInitInfo(boolean hasC,boolean hasD,boolean hasE){
		super(Message.createCooprtGame);
		if(hasC){
			this.setMulCrash(2);
		}else {
			this.setMulCrash(1);
		}
		if(hasE){
			this.setPromptTime(2);
		}else {
			this.setPromptTime(3);
		}
		this.setHasD(hasD);
	}
	

}
