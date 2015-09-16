package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import model.managers.ScoreManager;
import pos.historyPO.MultiGameHistoryItem;
import common.Info;
import common.gameInfo.FinalScoreInfo;
import common.gameInfo.MapRefreshInfo;
import common.gameInfo.NewMapInfo;
import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;
import common.gameInfo.Pro;
import common.gameInfo.PromptInfo;
import common.gameInfo.SwapInfo;
import common.gameInfo.TimeInfo;
import common.rankingInfo.SingleGameHistoryItemInfo;
import enums.DropMode;
import enums.Message;

public class Game {
	private int ID;
	public int getID(){
		return this.ID;
	}
	private boolean ifStart=false;

	public boolean isIfStart() {
		return ifStart;
	}

	public void setIfStart(boolean ifStart) {
		this.ifStart = ifStart;
	}

	private boolean ifPause=false;
	
	public boolean isIfPause() {
		return ifPause;
	}

	public void setIfPause(boolean ifPause) {
		this.ifPause = ifPause;
	}

	private ArrayList<Player> players = new ArrayList<Player>();

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	/*
	 * 连击处理
	 */
	private boolean ifSuper;

	public boolean isIfSuper() {
		return ifSuper;
	}

	public void setIfSuper(boolean ifSuper) {
		this.ifSuper = ifSuper;
	}

	private int mulCrashSec; // 连击时效
	private int mulTime; // 连击持续的时间,在每次产生连击的时候需要将它累加5
	private MultipleCrash multipleCrash;// 处理连击的类

	/*
	 * 提示处理
	 */
	private boolean hasPrompt;// 是否已经提示,在每次消除之后需要将此值设为false，在每次提示后需要将他设为true
	private int promptSec;// 提示时间
	private int promptTemp;// 提示的计时器，在每次消除之后需要将其还原

	/*
	 * 计时处理
	 */
	private int time;
	private Timer timer;

	/*
	 * 得分计算
	 */
	private ScoreManager scoreManager;

	/*
	 * 地图处理
	 */
	private Map map;

	/*
	 * 最大连击数
	 */
	private int maxCombo=0;
	public Game(int iD) {
		this.ID=iD;
	}

	public void init(int mulCrashSec, int promptSec, boolean hasPropD) {
		this.mulCrashSec = mulCrashSec;
		this.multipleCrash = new MultipleCrash(this.mulCrashSec);
		this.ifSuper = false;
		this.mulTime = 0;

		this.promptSec = promptSec;
		this.hasPrompt = false;
		this.promptTemp = this.promptSec;

		this.scoreManager = new ScoreManager(hasPropD);

		this.timer = new Timer();
		this.time = 0;
		System.out.println("初始化地图....");
		this.map = new Map(9, 9, 5);
		this.map.initial();
		System.out.println("初始化地图完成");

	}

	/*
	 * 广播地图信息,在初始化地图的时候调用
	 */
	public void broadcastMap() {
		Info newMapInfo = new NewMapInfo(this.map.getStates());
		this.broadcast(newMapInfo);
	}

	public void start() {
		this.broadcast(new Info(Message.GameStart));
		this.setIfStart(true);
		this.timer.schedule(new TimerLimit(), 0, 1000);

	}

	public void addPlayer(Player player) {
		this.players.add(player);

	}

	public int getPlayersNum() {
		return this.players.size();
	}

	public void end() {
		
		this.timer.cancel();
		this.useAllProp();
		
		// 广播结束
		this.broadcast(new Info(Message.gameOver));
		this.setIfStart(false);
		this.broadcast(new Info(Message.endSuperModel));
//		GamesManager gamesManager=GamesManager.getInstance();
//		gamesManager.removeGame(this.ID);
		

	}
	public void pause(){
		this.timer.cancel();
		this.setIfPause(true);
	}
	public void resume(){
		this.broadcast(new Info(Message.unlockMap));
		this.timer=new Timer();
		this.timer.schedule(new TimerLimit(), 0,1000);
	}
	private void useAllProp(){
		int prosSize=this.map.getPros().size();
		boolean ifCalculate=true;//标记位，判断是否已经结算过得分
		if(prosSize==0){
			if(ifCalculate){
				this.broadcast(new Info(Message.lockMap));
				this.scoreManager.calculateAllScore();
				int score=this.scoreManager.getScore();
				
				int coin=(int)(score/100);
				for(Player player:players){
					player.addCoins(coin);
				}
				
				Info finalScoreInfo=new FinalScoreInfo(score);
				this.broadcast(finalScoreInfo);
				
				if(this.players.size()==1){
					SingleGameHistoryItemInfo singleGameHistoryItem=new SingleGameHistoryItemInfo(new Date(), score, this.maxCombo);
					this.players.get(0).updateSingleGameHistory(singleGameHistoryItem);
				}else {
					ArrayList<String> coworker=new ArrayList<String>();
					for(Player player:this.players){
						coworker.add(player.getName());
					}
					MultiGameHistoryItem multiGameHistoryItem=new MultiGameHistoryItem(new Date(), score, this.maxCombo, coworker);
					this.players.get(0).updateMulGameHistory(multiGameHistoryItem);
				}
				ifCalculate=false;
			}
			
			
		}else {
			PositionsGroup positionsGroup = new PositionsGroup();
			for (int i = 0; i < prosSize; i++) {
				positionsGroup.addPosition(this.map.getPros().get(i).getPosition());
			}
			
			this.crush(positionsGroup);
		}
		
		
		
	}

	private void prompt() {
		// 广播提示
		PositionsGroup positionsGroup = JudgeAndHint.judgeIfDead(this.map.getStates());
		Info promptInfo = new PromptInfo(positionsGroup);
		this.broadcast(promptInfo);
		this.hasPrompt = true;
	}

	private void recoverPromptState() {
		this.hasPrompt = false;
		this.promptTemp = this.promptSec;
	}

	private void addSuperTime() {
		this.maxCombo++;
		this.mulTime += 5;
	}
	/*
	 * 向除了移动者之外的玩家发送移动请求
	 */
	public void broadcastSwapInfo(SwapInfo swapInfo,int playerId){
		for(Player player:players){
			if(player.getID()==playerId){
				continue;
			}else {
				player.write(swapInfo);
			}
		}
		
	}

	/*
	 * 使用道具的方法，参数为按钮点击的位置
	 */
	public void useProp(Position p) {
		if (this.map.isProp(p)) {
			System.out.println("使用道具");
			p.showPosition();
			PositionsGroup positionsGroup = new PositionsGroup();
			positionsGroup.addPosition(p);
			
		}

	}

	public void swap(Position p1, Position p2) {
		System.out.println("交换两个位置：");
		p1.showPosition();
		p2.showPosition();
		this.map.swapState(p1, p2);
		this.map.changeProsPosition(p1, p2);
		PositionsGroup positionsGroup = JudgeCrush.PositionChanged(p1, p2, this.map.getStates());
		if (positionsGroup.size() == 0) {
			this.broadcast(new Info(Message.noCrash));
			this.map.swapState(p1, p2);
			this.map.changeProsPosition(p1, p2);
		} else {
			this.crush(positionsGroup);
		}

	}

	public void autoCrash() {
		System.out.println("调用了自动消除方法！！！！---------");
		PositionsGroup positionsGroup = new PositionsGroup();
		positionsGroup = JudgeCrush.autoElim(this.map.getStates());
		if (positionsGroup.size() != 0) {
			this.crush(positionsGroup);
		}else{
			System.out.println("没有自动消除坐标！！！！-------------------");
			if(!ifStart){
				this.useAllProp();
			}else if(ifPause){
				this.broadcast(new Info(Message.lockMap));
			}else if(!ifPause&&ifStart){
				this.broadcast(new Info(Message.unlockMap));
			}
			
			
		}
	}

	private void broadcast(Info info) {
		for (Player player : players) {
			System.out.println("向玩家"+player.getName()+"发送："+info.getMessage());
			player.write(info);
		}
	}

	public void removePlayer(int playerID) {
		for (Player player : players) {
			if (player.getID() == playerID) {
				players.remove(player);
				System.out.println("成功删除一个玩家！");
				break;
			}
		}

	}

	public void destroySelf() {
		System.out.println("我销毁了自己！");
		this.timer.cancel();
	}

	class TimerLimit extends TimerTask {
		public void run() {
			if (time >= 60) {
				end();
			} else {
				time++;
				Info timeInfo=new TimeInfo(60-time);
				broadcast(timeInfo);
				if (mulTime <= 0) {
					if (ifSuper) {
						broadcast(new Info(Message.endSuperModel));
						ifSuper = false;
					}

				} else {
					if (!ifSuper) {
						broadcast(new Info(Message.startSuperModel));
						ifSuper = true;
					}
					mulTime--;
				}
				// 如果尚未提示
				if (!hasPrompt) {
					promptTemp--;
					if (promptTemp <= 0) {
						prompt();
					}
				}
			}

		}
	}
	private void crush(PositionsGroup positionsGroup){
		Calendar c = Calendar.getInstance();
		int second = c.get(Calendar.SECOND);
		if (this.multipleCrash.hasMultipleCrash(second)) {
			this.addSuperTime();
		}
		this.recoverPromptState();
		this.map.refresh(positionsGroup);
		scoreManager.addScore(this.ifSuper, this.map.getScoreGroup());

		System.out.println("需要消除的位置组为：");
		this.map.newElimGroup().showPositions();

		PositionsGroup newPositionsGroup = this.map.getNewPositionsGroup();
		DropMode dropMode = this.map.getDropMode();

		System.out.println("需要补块的位置组为：");
		newPositionsGroup.showPositions();

		System.out.println("新地图为：");
		this.map.printStates();

		ArrayList<Pro> pros = new ArrayList<Pro>();
		pros = this.map.getPros();
		System.out.println("所有道具的位置和类型：");
		for (Pro pro : pros) {
			System.out.println("类型：" + pro.getProType());
			System.out.print("位置：");
			pro.getPosition().showPosition();
		}
		int[][] newMap = this.map.getStates();
		// System.out.println(newMap);
		Info mapRefreshInfo = new MapRefreshInfo(this.map.newElimGroup(),
				newPositionsGroup, dropMode, newMap, pros,
				this.scoreManager.getScore());
		// this.broadcastMap();
		this.broadcast(mapRefreshInfo);
	}

}
