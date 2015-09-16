package common.rankingInfo;

import java.io.Serializable;
import java.util.Date;

import enums.Avatar;



public class SingleGameOverallItemInfo extends SingleGameHistoryItemInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickname;
	private Avatar avatar;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Avatar getAvatar() {
		return avatar;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	
	
	public SingleGameOverallItemInfo(int score,String nickname,Avatar avatar , String id){
		super(new Date(),score,0);
		this.setNickname(nickname);
		this.setAvatar(avatar);
		this.setId(id);
	}
	

}
