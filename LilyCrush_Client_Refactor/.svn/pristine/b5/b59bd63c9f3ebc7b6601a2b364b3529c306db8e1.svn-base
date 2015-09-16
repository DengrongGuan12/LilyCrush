package common.rankingInfo;

import java.util.ArrayList;

import common.Info;

import enums.Message;
public class SingleGameRankInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<SingleGameOverallItemInfo> singleGameOverallItemInfos=new ArrayList<SingleGameOverallItemInfo>();
	public ArrayList<SingleGameOverallItemInfo> getSingleGameOverallItemInfos() {
		return singleGameOverallItemInfos;
	}
	public void setSingleGameOverallItemInfos(
			ArrayList<SingleGameOverallItemInfo> singleGameOverallItemInfos) {
		this.singleGameOverallItemInfos = singleGameOverallItemInfos;
	}
	public SingleGameRankInfo(ArrayList<SingleGameOverallItemInfo> singleGameOverallItemInfos){
		super(Message.singleGameRankInfo);
		this.setSingleGameOverallItemInfos(singleGameOverallItemInfos);
		
	}

}
