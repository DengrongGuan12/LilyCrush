package model;

import java.util.ArrayList;

import model.managers.GamesManager;
import model.managers.RoomsManager;
import common.Info;
import common.roomInfo.ChangeReadyStateInfo;
import common.roomInfo.NewRoomInfo;
import common.settingInfo.PersonalInfo;
import enums.Message;

public class Room {
	public static int roomId=0;
	private int roomID;
	private ArrayList<Player> players=new ArrayList<Player>();
	private Player roomHostPlayer;

	public Player getRoomHostPlayer() {
		return roomHostPlayer;
	}

	public void setRoomHostPlayer(Player roomHostPlayer) {
		this.roomHostPlayer = roomHostPlayer;
	}
	
	public Room(int roomID){
		this.setRoomID(roomID);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getPlayersNum(){
		return this.players.size();
	}
	/*
	 * 获取除房主之外的玩家的信息
	 */
	public ArrayList<PersonalInfo> getPlayersInfo() {
		ArrayList<PersonalInfo> personalInfos = new ArrayList<PersonalInfo>();
		for (Player player : players) {
			if (player.getID() == this.getRoomHostPlayer().getID()) {
				continue;
			}
			PersonalInfo personalInfo = new PersonalInfo(player.getName(),player.getBriefIntroduction(),player.getNickname(),
					player.getImageType(), player.getCoins());
			personalInfos.add(personalInfo);

		}
		return personalInfos;
	}
	
	/*
	 * 获取房主的信息
	 */
	public PersonalInfo getRoomHostPlayerInfo() {
		PersonalInfo personalInfo = new PersonalInfo(
				this.roomHostPlayer.getName(),
				this.roomHostPlayer.getBriefIntroduction(),
				this.roomHostPlayer.getNickname(),
				this.roomHostPlayer.getImageType(),
				this.roomHostPlayer.getCoins());
		return personalInfo;
	}
	public char addPlayer(Player player){
		char c = 'a';// 正确加入
		if (this.players.size() >= 4) {
			c = 'c';// 游戏人数已经满了
		} else {
			this.players.add(player);
			/*
			 * 加入一个新的玩家后原来可以开始的游戏现在不能开始，需要给房主发送消息将开始键禁止
			 */
			this.getRoomHostPlayer().write(new Info(Message.cannotStartCooperateGame));

		}
		return c;
	}
	/*
	 * 判断是否可以开始游戏，若可以向房主询问是否开始游戏
	 */
	public void checkCanStart() {
		boolean temp = true;
		for (Player player : players) {
			temp = temp && player.isReady();
		}
		/*
		 * 不能询问过一次之后就不再询问，因为有可能后来加进来的玩家没有准备好
		 */
		if (this.players.size() >= 2 && temp) {
			this.getRoomHostPlayer().write(new Info(Message.canStartCooprtGame));
		}else{
			this.getRoomHostPlayer().write(new Info(Message.cannotStartCooperateGame));
		}

	}
	public void startGame(){
		boolean temp = true;
		for (Player player : players) {
			temp = temp && player.isReady();
		}
		/*
		 * 不能询问过一次之后就不再询问，因为有可能后来加进来的玩家没有准备好
		 */
		if (!(this.players.size() >= 2 && temp)) {
			this.getRoomHostPlayer().write(new Info(Message.cannotStartCooprateGame_exception));
		}else {
			/*
			 * 将房间和游戏的逻辑拆分开，当游戏开始时销毁房间
			 */
			this.broadcast(new Info(Message.roomFadeOut));
			Game game=new Game(this.roomID);
			game.setPlayers(players);
			game.init(1, 3, false);
			GamesManager gamesManager=GamesManager.getInstance();
			gamesManager.addGame(game);
			game.broadcastMap();
			game.start();
			System.out.println("game start");
			RoomsManager roomsManager=RoomsManager.getInstance();
			roomsManager.removeRoom(this.roomID);
			System.out.println("删除房间！");
		}
	}
	/*
	 * 广播房间的玩家信息,每次有新的玩家加入的时候都要调用
	 */
	public void broadcastPlayersInfo() {
		Info newRoomInfo = new NewRoomInfo(this.getPlayersInfo(),
				this.getRoomHostPlayerInfo());
		this.broadcast(newRoomInfo);
	}
	
	private void broadcast(Info info){
		for(Player player:this.players){
			player.write(info);
		}
	}
	
	/*
	 * 玩家退出房间
	 */
	public void removePlayer(Player player){
		for(Player player2:players){
			if(player2.getID()==player.getID()){
				players.remove(player2);
				break;
			}
		}
		if(players.size()==0){
			//全部退出
			RoomsManager roomsManager=RoomsManager.getInstance();
			roomsManager.removeRoom(this.roomID);
		}else {
			if(player.getID()==this.roomHostPlayer.getID()){
				//说明该玩家是房主
				this.setRoomHostPlayer(players.get(0));
				this.roomHostPlayer.setReady(true);
				
			}
			this.broadcastPlayersInfo();
			if(players.size()==1){
				this.roomHostPlayer.write(new Info(Message.cannotStartCooperateGame));
			}
		}
		//重置房间号和游戏号
		player.setGameID(0);
		player.setRoomID(0);
		
	}
	public boolean hasPlayer(String name){
		boolean hasPlayer=false;
		for(Player player:players){
			if(player.getName().equals(name)){
				hasPlayer=true;
				break;
			}
		}
		return hasPlayer;
	}
	
	public void someOneChangeState(String name){
		for(Player player:players){
			if(player.getName().equals(name)){
				continue;
			}
			Info changeReadyStateInfo=new ChangeReadyStateInfo(name);
			player.write(changeReadyStateInfo);
		}
	}


}
