package pos;

import enums.Avatar;

public class FriendInfoPO {
	private String id;
	private String nickname;
	private Avatar imageType;
	private int coins;
	private String briefIntro;
	
	public String getBriefIntro() {
		return briefIntro;
	}

	public int getCoins() {
		return coins;
	}

	public Avatar getImageType() {
		return imageType;
	}

	public FriendInfoPO(String id,String nickname,Avatar imageType,int coins,String briefIntro){
		this.id = id;
		this.nickname = nickname;
		this.imageType=imageType;
		this.coins=coins;
		this.briefIntro = briefIntro;
	}
	
	public String getId() {
		return id;
	}
	public String getNickname() {
		return nickname;
	}
}
