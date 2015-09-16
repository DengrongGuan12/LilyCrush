package businesslogicService;

import client.MyClient;
import view.MainFrame;
import view.RankingPanel;
import common.rankingInfo.MultiGameRankInfo;
import common.rankingInfo.SingleGameHistoryInfo;
import common.rankingInfo.SingleGameRankInfo;


public interface RankingBLService {
	/*
	 * ����鿴������Ϸ���а�ʱ����Ҫ���õĽӿڣ�����ֵΪ���������Ƿ�����
	 */
	public void getSingleHistoryData();
	
	/*
	 * ����鿴������Ϸ���а����Ҫ���õĽӿڣ�����ֵΪ�����Ƿ�����
	 */
	public void getSingleGameRankInfo();
	
	/*
	 * ����鿴������Ϸ���а�ʱ���õķ���
	 */
	public void getMultiHistoryData();
	
	public void showSingleGameHistory(SingleGameHistoryInfo singleGameHistoryInfo);
	public void setRankingPanel(RankingPanel rankingPanel);
	public void showSingleGameOverallItems(SingleGameRankInfo singleGameRankInfo);
	public void showMultiGameRank(MultiGameRankInfo multiGameRankInfo);
	public void setMainFrame(MainFrame mainFrame);
	public void setMyClient(MyClient myClient);
	
}