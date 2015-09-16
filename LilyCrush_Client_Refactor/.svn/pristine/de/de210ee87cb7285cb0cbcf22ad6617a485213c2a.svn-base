package businesslogic;

import java.util.ArrayList;

import client.MyClient;
import view.MainFrame;
import view.RankingPanel;
import common.Info;
import common.rankingInfo.MultiGameRankInfo;
import common.rankingInfo.MultiOverallItemInfo;
import common.rankingInfo.SingleGameHistoryInfo;
import common.rankingInfo.SingleGameHistoryItemInfo;
import common.rankingInfo.SingleGameOverallItemInfo;
import common.rankingInfo.SingleGameRankInfo;
import enums.Message;
import businesslogicService.RankingBLService;

public class RankingBL implements RankingBLService{
	private MainFrame mainFrame;
	private MyClient myClient;
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	public void setMyClient(MyClient myClient) {
		this.myClient = myClient;
	}
	private RankingPanel rankingPanel;
	public void setRankingPanel(RankingPanel rankingPanel){
		this.rankingPanel=rankingPanel;
		
	}
	public void getSingleHistoryData() {
		if (!this.myClient.write(new Info(Message.getSingleHistoryData))) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	@Override
	public void getSingleGameRankInfo() {
		if (!this.myClient.write(new Info(Message.getSingleGameRank))) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	@Override
	public void getMultiHistoryData() {
		if (!this.myClient.write(new Info(Message.getMultiGameRank))) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	@Override
	public void showSingleGameHistory(SingleGameHistoryInfo singleGameHistoryInfo) {
		ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems = new ArrayList<SingleGameHistoryItemInfo>();
		singleGameHistoryItems = singleGameHistoryInfo
				.getSingleGameHistoryItems();
		/*
		 * 需要提供显示单人游戏记录排行榜的界面接口，参数为ArrayList<SingleGameHistoryItem>，已经排好序
		 * public void
		 * showSingleGameHistory(ArrayList<SingleGameHistoryItem>
		 * singleGameHistoryItems){}
		 */
		this.rankingPanel.showPersonalList(singleGameHistoryItems);
		
	}
	@Override
	public void showSingleGameOverallItems(SingleGameRankInfo singleGameRankInfo) {
		ArrayList<SingleGameOverallItemInfo> singleGameOverallItemInfos = new ArrayList<SingleGameOverallItemInfo>();
		singleGameOverallItemInfos = singleGameRankInfo
				.getSingleGameOverallItemInfos();
		/*
		 * 提供一个显示单人游戏排行榜的界面接口，参数为包含昵称，头像，得分的arraylist public void
		 * showSingleGameRank(Arraylist<SingleGameOverallItemInfo>
		 * singleGameOverallItemInfos){}
		 */
		this.rankingPanel.showSingleList(singleGameOverallItemInfos);
		
		
	}
	@Override
	public void showMultiGameRank(MultiGameRankInfo multiGameRankInfo) {
		ArrayList<MultiOverallItemInfo> multiOverallItemInfos = new ArrayList<MultiOverallItemInfo>();
		multiOverallItemInfos = multiGameRankInfo
				.getMultiOverallItemInfos();
		this.rankingPanel.showMultiList(multiOverallItemInfos);
		
	}

}
