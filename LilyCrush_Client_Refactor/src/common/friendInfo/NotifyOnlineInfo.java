package common.friendInfo;

import common.Info;

import enums.Message;

/**
 * 更改在线状态时的提醒
 *
 */
public class NotifyOnlineInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NotifyOnlineInfo(String id) {
		super(Message.notifyOnlineInfo);
		this.setId(id);
	}

}
