package businesslogicService;

import client.MyClient;
import view.MainFrame;
import view.RankingPanel;
import common.rankingInfo.MultiGameRankInfo;
import common.rankingInfo.SingleGameHistoryInfo;
import common.rankingInfo.SingleGameRankInfo;


public interface RankingBLService {
	/*
	 * 点击查看单人游戏排行榜时的需要调用的接口，返回值为网络链接是否正常
	 */
	public void getSingleHistoryData();
	
	/*
	 * 点击查看单人游戏排行榜的需要调用的接口，返回值为网络是否正常
	 */
	public void getSingleGameRankInfo();
	
	/*
	 * 点击查看多人游戏排行榜时调用的方法
	 */
	public void getMultiHistoryData();
	
	public void showSingleGameHistory(SingleGameHistoryInfo singleGameHistoryInfo);
	public void setRankingPanel(RankingPanel rankingPanel);
	public void showSingleGameOverallItems(SingleGameRankInfo singleGameRankInfo);
	public void showMultiGameRank(MultiGameRankInfo multiGameRankInfo);
	public void setMainFrame(MainFrame mainFrame);
	public void setMyClient(MyClient myClient);
	
}
