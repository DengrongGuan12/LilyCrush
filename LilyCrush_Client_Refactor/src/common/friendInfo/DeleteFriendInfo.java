package common.friendInfo;

import common.Info;

import enums.Message;

public class DeleteFriendInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public DeleteFriendInfo(String name){
		super(Message.deleteFriend);
		this.setName(name);
	}

}
