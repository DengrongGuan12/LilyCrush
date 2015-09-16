package common.gameInfo;

import common.Info;

import enums.Message;

public class TimeInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int time;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public TimeInfo(int time){
		super(Message.timeInfo);
		this.setTime(time);
	}

}
