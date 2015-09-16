package common.rankingInfo;

import java.util.ArrayList;

import common.Info;

import enums.Message;

public class MultiGameRankInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MultiOverallItemInfo> multiOverallItemInfos=new ArrayList<MultiOverallItemInfo>();
	public ArrayList<MultiOverallItemInfo> getMultiOverallItemInfos() {
		return multiOverallItemInfos;
	}
	public void setMultiOverallItemInfos(
			ArrayList<MultiOverallItemInfo> multiOverallItemInfos) {
		this.multiOverallItemInfos = multiOverallItemInfos;
	}
	public MultiGameRankInfo(ArrayList<MultiOverallItemInfo> multiOverallItemInfos){
		super(Message.multiGameHistoryRank);
		this.setMultiOverallItemInfos(multiOverallItemInfos);
	}

}
