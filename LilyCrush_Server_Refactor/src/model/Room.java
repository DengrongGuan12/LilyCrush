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
	 * ��ȡ������֮�����ҵ���Ϣ
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
	 * ��ȡ��������Ϣ
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
		char c = 'a';// ��ȷ����
		if (this.players.size() >= 4) {
			c = 'c';// ��Ϸ�����Ѿ�����
		} else {
			this.players.add(player);
			/*
			 * ����һ���µ���Һ�ԭ�����Կ�ʼ����Ϸ���ڲ��ܿ�ʼ����Ҫ������������Ϣ����ʼ����ֹ
			 */
			this.getRoomHostPlayer().write(new Info(Message.cannotStartCooperateGame));

		}
		return c;
	}
	/*
	 * �ж��Ƿ���Կ�ʼ��Ϸ������������ѯ���Ƿ�ʼ��Ϸ
	 */
	public void checkCanStart() {
		boolean temp = true;
		for (Player player : players) {
			temp = temp && player.isReady();
		}
		/*
		 * ����ѯ�ʹ�һ��֮��Ͳ���ѯ�ʣ���Ϊ�п��ܺ����ӽ��������û��׼����
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
		 * ����ѯ�ʹ�һ��֮��Ͳ���ѯ�ʣ���Ϊ�п��ܺ����ӽ��������û��׼����
		 */
		if (!(this.players.size() >= 2 && temp)) {
			this.getRoomHostPlayer().write(new Info(Message.cannotStartCooprateGame_exception));
		}else {
			/*
			 * ���������Ϸ���߼���ֿ�������Ϸ��ʼʱ���ٷ���
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
			System.out.println("ɾ�����䣡");
		}
	}
	/*
	 * �㲥����������Ϣ,ÿ�����µ���Ҽ����ʱ��Ҫ����
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
	 * ����˳�����
	 */
	public void removePlayer(Player player){
		for(Player player2:players){
			if(player2.getID()==player.getID()){
				players.remove(player2);
				break;
			}
		}
		if(players.size()==0){
			//ȫ���˳�
			RoomsManager roomsManager=RoomsManager.getInstance();
			roomsManager.removeRoom(this.roomID);
		}else {
			if(player.getID()==this.roomHostPlayer.getID()){
				//˵��������Ƿ���
				this.setRoomHostPlayer(players.get(0));
				this.roomHostPlayer.setReady(true);
				
			}
			this.broadcastPlayersInfo();
			if(players.size()==1){
				this.roomHostPlayer.write(new Info(Message.cannotStartCooperateGame));
			}
		}
		//���÷���ź���Ϸ��
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
