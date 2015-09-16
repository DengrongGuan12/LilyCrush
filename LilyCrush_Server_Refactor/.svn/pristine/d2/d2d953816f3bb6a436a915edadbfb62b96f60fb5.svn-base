package common.rankingInfo;

import java.io.Serializable;
import java.util.ArrayList;

import enums.Avatar;



public class MultiOverallItemInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Avatar friendAvatar;
	private String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;
	private ArrayList<String> coworkers=new ArrayList<String>();
	private int maxPoint;
	public Avatar getFriendAvatar() {
		return friendAvatar;
	}
	public void setFriendAvatar(Avatar friendAvatar) {
		this.friendAvatar = friendAvatar;
	}
	public ArrayList<String> getCoworkers() {
		return coworkers;
	}
	public void setCoworkers(ArrayList<String> coworkers) {
		this.coworkers = coworkers;
	}
	public int getMaxPoint() {
		return maxPoint;
	}
	public void setMaxPoint(int maxPoint) {
		this.maxPoint = maxPoint;
	}
	public MultiOverallItemInfo(Avatar friendAvatar,ArrayList<String> coworkers,int maxPoint , String id,String nickname){
		this.setFriendAvatar(friendAvatar);
		this.setCoworkers(coworkers);
		this.setMaxPoint(maxPoint);
		this.nickname = nickname;
		this.id = id;
	}
	

}
