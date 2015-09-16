package businesslogicService;
import client.MyClient;
import common.gameInfo.FinalScoreInfo;
import common.gameInfo.MapRefreshInfo;
import common.gameInfo.NewMapInfo;
import common.gameInfo.Position;
import common.gameInfo.PromptInfo;
import common.gameInfo.SwapInfo;
import common.gameInfo.TimeInfo;
import view.MainFrame;
import view.SingleGamePanel;
import enums.Direction;

public interface GameBLService {
	public void setGamePanel(SingleGamePanel singleGamePanel);
	public void setMyClient(MyClient myClient);
	/*
	 * 暂停游戏需要调用的方法
	 */
	public void pauseGame();
	/*
	 * 暂停之后继续游戏的方法
	 */
	public void continueGame();
	/*
	 * 在游戏进行中退出游戏需要调用的方法
	 */
	public void exitGame();
	/*
	 * 游戏中拖动图标需要调用的方法
	 */
	public void swap(Position location, Direction direction);

	/*
	 * 点击准备进行单人游戏需要调用的方法，返回值为网络连接是否正常
	 */
	public void init();
	
	/*
	 * 点击开始游戏后需要调用的方法，返回值为网络连接是否正常
	 */
	public void startSingleGame() ;	
	/*
	 * setMainFrame(MainFrame mainframe)
	 */
	public void setMainFrame(MainFrame mainFrame);
	/*
	 * 在地图更新完成之后调用判断自动消除的方法
	 */
	public void judgeAutoCrash();
	
	/*
	 * 点击图标使用道具需要调用的方法
	 */
	public void useProp(Position position);
	/*
	 * 开始super模式
	 */
	public void startSuper();
	/*
	 * 结束super模式
	 */
	public void endSuper();
	/*
	 * 提示
	 */
	public void prompt(PromptInfo promptInfo);
	/*
	 * 游戏结束
	 */
	public void end();
	/*
	 * 游戏开始
	 */
	public void start();
	/*
	 * 设置最终得分
	 */
	public void setFinalScore(FinalScoreInfo finalScoreInfo);
	/*
	 * 设置当一个玩家拖动时向其他玩家发出的拖动消息
	 */
	public void setSwapInfo(SwapInfo swapInfo);
	/*
	 * 移动地图
	 */
	public void swapMap();
	
	/*
	 * 有消除
	 */
	public void crash(MapRefreshInfo mapRefreshInfo);
	/*
	 * 新地图
	 */
	public void initialMap(NewMapInfo newMapInfo);
	/*
	 * 锁住地图
	 */
	public void lockMap();
	/*
	 * 解锁地图
	 */
	public void unlockMap();
	public void setTime(TimeInfo timeInfo);
	
}
