package common.settingInfo;

import common.Info;

import enums.Avatar;
import enums.Message;



public class PersonalInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickname;
	private Avatar avatar;
	private int coins;
	private String id;
	private String briefIntroduction;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public PersonalInfo(String id,String briefIntroduction,String nickname,Avatar avatar,int coins){
		super(Message.personalInfo);
		this.setId(id);
		this.setBriefIntroduction(briefIntroduction);
		this.setNickname(nickname);
		this.setCoins(coins);
		this.setAvatar(avatar);
		
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

}
