package common.gameInfo;

import common.Info;

import enums.Message;

/*
 * 消除的坐标，生成的道具，得分
 */
public class CrashInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PositionsGroup positionsGroup;
	public PositionsGroup getPositionsGroup() {
		return positionsGroup;
	}
	public void setPositionsGroup(PositionsGroup positionsGroup) {
		this.positionsGroup = positionsGroup;
	}
	public CrashInfo(PositionsGroup positionsGroup){
		super(Message.crash);
		this.setPositionsGroup(positionsGroup);
	}

}
