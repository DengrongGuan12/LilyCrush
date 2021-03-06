package businesslogic;

import client.MyClient;
import view.MainFrame;
import view.SingleGamePanel;
import common.Info;
import common.gameInfo.FinalScoreInfo;
import common.gameInfo.MapRefreshInfo;
import common.gameInfo.NewMapInfo;
import common.gameInfo.OneInitGameInfo;
import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;
import common.gameInfo.PromptInfo;
import common.gameInfo.SwapInfo;
import common.gameInfo.TimeInfo;
import common.gameInfo.UsePropInfo;
import enums.Direction;
import enums.Message;
import businesslogic.managers.PropManager;
import businesslogic.managers.SoundManager;
import businesslogicService.GameBLService;

public class GameBL implements GameBLService{
	private MyClient myClient;
	public void setMyClient(MyClient myClient) {
		this.myClient = myClient;
	}

	private MainFrame mainFrame;
	private SingleGamePanel singleGamePanel;
	private SoundManager soundManager;
	private Position position;
	private Direction direction;
	@Override
	public void setGamePanel(SingleGamePanel singleGamePanel) {
		this.singleGamePanel=singleGamePanel;
		
	}

	@Override
	public void pauseGame() {
		if(!this.myClient.write(new Info(Message.pauseSingleGame))){
			this.mainFrame.disconnected();
		}
		
	}
	@Override
	public void continueGame() {
		if(!this.myClient.write(new Info(Message.continueSingleGame))){
			this.mainFrame.disconnected();
		}
		
	}

	@Override
	public void exitGame() {
		if(!this.myClient.write(new Info(Message.exitGame))){
			this.mainFrame.disconnected();
		}else {
			this.soundManager.close();
		}
		
	}

	@Override
	public void swap(Position location, Direction direction) {
		this.direction=direction;
		this.position=location;
		int x = location.getX();
		int y = location.getY();
		switch (direction) {
		case up: {
			Info swapInfo = new SwapInfo(location, x - 1, y,direction);
			if (!this.myClient.write(swapInfo)) {
				/*
				 * 需要网络连接不正常的界面接口 public void disconnected(){}
				 */
				this.mainFrame.disconnected();
			}
			break;
		}

		case down: {
			Info swapInfo = new SwapInfo(location, x + 1, y,direction);
			if (!this.myClient.write(swapInfo)) {
				/*
				 * 需要网络连接不正常的界面接口 public void disconnected(){}
				 */
				this.mainFrame.disconnected();
			}
			break;
		}

		case right: {
			Info swapInfo = new SwapInfo(location, x, y + 1,direction);
			if (!this.myClient.write(swapInfo)) {
				/*
				 * 需要网络连接不正常的界面接口 public void disconnected(){}
				 */
				this.mainFrame.disconnected();
			}
			break;
		}

		case left: {
			Info swapInfo = new SwapInfo(location, x, y - 1,direction);
			if (!this.myClient.write(swapInfo)) {
				/*
				 * 需要网络连接不正常的界面接口 public void disconnected(){}
				 */
				this.mainFrame.disconnected();
			}
			break;
		}

		default:
			break;
		}

	}

	public void init() {
		PropManager propManager=PropManager.getInstance();
		Info oneInitGameInfo = new OneInitGameInfo(propManager.isHasC(),
				propManager.isHasD(), propManager.isHasE());
		if (!this.myClient.write(oneInitGameInfo)) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	public void startSingleGame() {
		PropManager propManager=PropManager.getInstance();
		Info oneInitGameInfo = new OneInitGameInfo(propManager.isHasC(),propManager.isHasD(),propManager.isHasE());
		this.myClient.write(oneInitGameInfo);
		if (!this.myClient.write(new Info(Message.stOneGame))) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	@Override
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame=mainFrame;
		
	}

	@Override
	public void judgeAutoCrash() {
		System.out.println("下落完成，需要判断是否有自动消除的地方！！");
		if (!this.myClient.write(new Info(Message.canAutoCrash))) {
			this.mainFrame.disconnected();
		}

	}

	@Override
	public void useProp(Position position) {
		Info usePropInfo = new UsePropInfo(position);
		if (!this.myClient.write(usePropInfo)) {
			this.mainFrame.disconnected();
		}

	}

	@Override
	public void endSuper() {
		this.singleGamePanel.superTimeOff();
		
	}
	public void startSuper(){
		this.singleGamePanel.superTimeOn();
	}

	@Override
	public void prompt(PromptInfo promptInfo) {
		PositionsGroup positionsGroup = promptInfo.getPositionsGroup();
		this.singleGamePanel.prompt(positionsGroup);
		
	}
	public void end(){
		this.soundManager.stop();
		this.singleGamePanel.gameOver();
	}

	@Override
	public void start() {
		this.soundManager=new SoundManager("sound//夜空中最亮的星.mp3");
		this.soundManager.play();
		
	}
	
	public void setFinalScore(FinalScoreInfo finalScoreInfo){
		int score=finalScoreInfo.getScore();
		this.singleGamePanel.setScore(score);
	}

	@Override
	public void setSwapInfo(SwapInfo swapInfo) {
		this.position=swapInfo.getP1();
		this.direction=swapInfo.getDirection();
		
	}

	@Override
	public void swapMap() {
		this.singleGamePanel.swapMap(this.position, this.direction);
		
	}

	@Override
	public void crash(MapRefreshInfo mapRefreshInfo) {
		this.singleGamePanel.crash(mapRefreshInfo);
		
	}
	public void initialMap(NewMapInfo newMapInfo){
		this.singleGamePanel.mapInitial(newMapInfo);
		this.singleGamePanel.fadeIn();
		
	}
	public void lockMap(){
		this.singleGamePanel.lock();
	}
	public void unlockMap(){
		this.singleGamePanel.unLock();
	}

	@Override
	public void setTime(TimeInfo timeInfo) {
		int time = timeInfo.getTime();
		this.singleGamePanel.setTime(Integer.toString(time));
		
	}
	

}
