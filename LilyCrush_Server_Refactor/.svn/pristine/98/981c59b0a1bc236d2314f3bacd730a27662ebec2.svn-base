package common.friendInfo;

import java.util.ArrayList;

import common.rankingInfo.SingleGameHistoryItemInfo;
import common.settingInfo.PersonalInfo;
import enums.Avatar;



public class Friend extends PersonalInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean inline;
	private String name;
	private ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems=new ArrayList<SingleGameHistoryItemInfo>();
	

	public ArrayList<SingleGameHistoryItemInfo> getSingleGameHistoryItems() {
		return singleGameHistoryItems;
	}

	public void setSingleGameHistoryItems(
			ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems) {
		this.singleGameHistoryItems = singleGameHistoryItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInline() {
		return inline;
	}

	public void setInline(boolean inline) {
		this.inline = inline;
	}

	public Friend(String name, String briefIntroduction,String nickname, Avatar avatar, int coins,
			boolean inline,ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems) {
		super(name,briefIntroduction,nickname, avatar, coins);
		this.setInline(inline);
		this.setName(name);
		this.setSingleGameHistoryItems(singleGameHistoryItems);

	}

}
