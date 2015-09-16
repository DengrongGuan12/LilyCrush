package vos;

import enums.Avatar;


public class PersonalInfoVO {
	private String id;
	private String nickname;
	private Avatar image;	
	private String briefIntroduction;
	private int coin;
	
	public PersonalInfoVO(String id,String nickname,Avatar image,String briefIntroduction , int coin){
		this.id = id;
		this.nickname = nickname;
		this.image = image;
		this.briefIntroduction = briefIntroduction;
		this.coin = coin;
	}
	
	
	public String getId() {
		return id;
	}
	public String getNickname() {
		return nickname;
	}
	public Avatar getImage() {
		return image;
	}
	public String getBriefIntroduction() {
		return briefIntroduction;
	}
	public int getCoin() {
		return coin;
	}
}
