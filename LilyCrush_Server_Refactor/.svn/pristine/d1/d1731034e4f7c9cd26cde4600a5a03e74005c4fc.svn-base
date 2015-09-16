package common.rankingInfo;

import java.util.ArrayList;

import common.Info;
import enums.Message;

public class SingleGameHistoryInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems=new ArrayList<SingleGameHistoryItemInfo>();
	public ArrayList<SingleGameHistoryItemInfo> getSingleGameHistoryItems() {
		return singleGameHistoryItems;
	}
	public void setSingleGameHistoryItems(
			ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems) {
		this.singleGameHistoryItems = singleGameHistoryItems;
	}
	public SingleGameHistoryInfo(ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems){
		super(Message.SingleHistoryData);
		this.singleGameHistoryItems=singleGameHistoryItems;
	}

}
