package pos;

import enums.Avatar;

public class PersonalInfoPO {
	private String id;
	private String nickname;
	private Avatar avatar;	
	private String briefIntroduction;
	private int coin;
	
	public PersonalInfoPO(String id,String nickname,Avatar avatar,String briefIntroduction , int coin){
		this.id = id;
		this.nickname = nickname;
		this.avatar = avatar;
		this.briefIntroduction = briefIntroduction;
		this.coin = coin;
	}
	
	
	public String getId() {
		return id;
	}
	public String getNickname() {
		return nickname;
	}
	public Avatar getAvatar() {
		return avatar;
	}
	public String getBriefIntroduction() {
		return briefIntroduction;
	}
	public int getCoin() {
		return coin;
	}
}
