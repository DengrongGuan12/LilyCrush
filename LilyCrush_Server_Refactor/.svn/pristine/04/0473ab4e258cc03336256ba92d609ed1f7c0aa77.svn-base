package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.managers.GamesManager;
import model.managers.PlayersManager;
import model.managers.RoomsManager;
import pos.FriendInfoPO;
import pos.PersonalInfoPO;
import pos.RelationPO;
import pos.SecurityPO;
import pos.historyPO.MultiGameHistoryItem;
import common.Info;
import common.financialInfo.AddedCoinsInfo;
import common.friendInfo.AddFriendInfo;
import common.friendInfo.DeleteFriendInfo;
import common.friendInfo.Friend;
import common.friendInfo.FriendsListInfo;
import common.friendInfo.NotifyOnlineInfo;
import common.gameInfo.OneInitGameInfo;
import common.gameInfo.Position;
import common.gameInfo.SwapInfo;
import common.gameInfo.UsePropInfo;
import common.loginInfo.LoginInfo;
import common.rankingInfo.MultiGameRankInfo;
import common.rankingInfo.MultiOverallItemInfo;
import common.rankingInfo.SingleGameHistoryInfo;
import common.rankingInfo.SingleGameHistoryItemInfo;
import common.rankingInfo.SingleGameOverallItemInfo;
import common.rankingInfo.SingleGameRankInfo;
import common.registerInfo.RegInfo;
import common.roomInfo.FindCooprtGame;
import common.roomInfo.InviteFriendInfo;
import common.roomInfo.JoinCooprtGame;
import common.settingInfo.ChangeAvatarInfo;
import common.settingInfo.ChangePasswdInfo;
import common.settingInfo.PersonalInfo;
import dataService.MultiHistoryDataService;
import dataService.MultiOverallDataService;
import dataService.MultiOverallItem;
import dataService.PersonalInfoDataService;
import dataService.RelationDataService;
import dataService.SecurityDataService;
import dataService.SingleHistoryDataService;
import dataService.SingleOverallDataService;
import dataService.SingleOverallItem;
import dataserviceImpli.MultiGameHistoryDataServiceImpli;
import dataserviceImpli.PersonalInfoDataServiceImpli;
import dataserviceImpli.RelationDataServiceImpli;
import dataserviceImpli.SecurityDataServiceImpli;
import dataserviceImpli.SingleGameHistoryDataServiceImpli;
import enums.Avatar;
import enums.Message;

public class Player {
	private Channel channel;
	private int ID;
	private int GameID;
	private int RoomID;

	public int getRoomID() {
		return RoomID;
	}

	public void setRoomID(int roomID) {
		RoomID = roomID;
	}

	public int getGameID() {
		return GameID;
	}

	public void setGameID(int gameID) {
		GameID = gameID;
	}

	private boolean ready;
	private PersonalInfoDataServiceImpli personalInfoDataService;
	private RelationDataServiceImpli relationDataService;
	private SecurityDataServiceImpli securityDataService;
	private SingleGameHistoryDataServiceImpli singleGameHistoryDataService;
	private MultiGameHistoryDataServiceImpli multiGameHistoryDataService;
	private SingleOverallDataService singleOverallDataService;
	private MultiOverallDataService multiOverallDataService;

	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	public int getID() {
		return ID;
	}
	private String name = "";// 用户名

	public String getName() {
		return name;
	}

	private String passwd = "";// 密码

	public String getPasswd() {
		return passwd;
	}

	private String nickname = "";// 昵称

	public String getNickname() {
		return nickname;
	}

	private int coins = 0;// 金币数量

	public int getCoins() {
		return coins;
	}

	private Avatar imageType = Avatar.hokain1;

	public Avatar getImageType() {
		return imageType;
	}

	private String briefIntroduction = "";

	public String getBriefIntroduction() {
		return briefIntroduction;
	}


	public void write(Info info) {
		this.write(info);
	}

	public void addCoins(int coin) {
		this.coins += coin;
		this.personalInfoDataService.updateCoins(this.name, this.coins);
		Info addedCoinsInfo = new AddedCoinsInfo(coin);
		this.write(addedCoinsInfo);

	}

	public void login(LoginInfo loginInfo) {
		String name=loginInfo.getName();
		String passwd1 = this.securityDataService.getPswd(name);
		if ((passwd1 != null) && (passwd1.equals(passwd))) {
			PlayersManager playersManager = PlayersManager.getInstance();
			if (playersManager.hasLogin(name)) {
				this.write(new Info(Message.hasLogin));
			} else {
				this.updateFriendsOnlineInfo();
				PersonalInfoPO po = this.personalInfoDataService.getInfo(name);
				this.name=name;
				this.passwd=passwd1;
				this.coins=po.getCoin();
				this.nickname=po.getNickname();
				this.imageType=po.getAvatar();
				this.briefIntroduction=po.getBriefIntroduction();
				this.write(new Info(Message.loginSuc));
				Info personInfo = new PersonalInfo(this.name,
						this.briefIntroduction, this.nickname, this.imageType,
						this.coins);
				this.write(personInfo);
			}
		} else {
			this.write(new Info(Message.loginFail));
		}
	}

	public void register(RegInfo regInfo) {
		String name=regInfo.getName();
		String nickName=regInfo.getNickname();
		String passwd=regInfo.getPasswd();
		PersonalInfoPO po = new PersonalInfoPO(name,nickName, Avatar.hokain1,
				"no content!", 0);
		SecurityPO securityPO = new SecurityPO(name, passwd);
		if (!this.securityDataService.ifExist(name)
				&& this.personalInfoDataService.insertInfo(po)
				&& this.securityDataService.insertRecord(securityPO)) {
			this.passwd=passwd;
			this.nickname=nickName;
			this.coins=0;
			this.imageType=Avatar.hokain1;
			this.briefIntroduction="no content!";
			this.write(new Info(Message.registerSuc));
		} else {
			this.write(new Info(Message.registerFail));
		}
	}

	public void initOneGame(OneInitGameInfo oneInitGameInfo) {
		Room.roomId++;
		this.GameID = Room.roomId;
		Game game = new Game(this.GameID);
		game.addPlayer(this);
		game.init(oneInitGameInfo.getMulCrash(),
				oneInitGameInfo.getPromptTime(), oneInitGameInfo.isHasD());
		this.coins -= oneInitGameInfo.getCostCoins();
		this.personalInfoDataService.updateCoins(this.name, this.coins);
		GamesManager gamesManager = GamesManager.getInstance();
		gamesManager.addGame(game);
		game.broadcastMap();
		this.write(new Info(Message.canStOneGame));
	}

	public void startOneGame() {
		GamesManager gamesManager = GamesManager.getInstance();
		Game game = gamesManager.getGame(this.GameID);
		game.start();
	}

	public void pauseOneGame() {
		GamesManager gamesManager = GamesManager.getInstance();
		Game game = gamesManager.getGame(this.GameID);
		game.pause();
	}

	public void continueSingleGame() {
		GamesManager gamesManager = GamesManager.getInstance();
		Game game = gamesManager.getGame(this.GameID);
		game.resume();
	}

	public void exitGame() {
		GamesManager gamesManager = GamesManager.getInstance();
		Game game = gamesManager.getGame(this.GameID);
		if (game != null) {
			game.removePlayer(this.ID);
		}
		if (game.getPlayersNum() == 0) {
			game.destroySelf();
			gamesManager.removeGame(this.GameID);
		}
	}

	public void swapPosition(SwapInfo swapInfo) {
		GamesManager gamesManager = GamesManager.getInstance();
		Game game = gamesManager.getGame(this.GameID);
		game.broadcastSwapInfo(swapInfo, this.ID);
		game.swap(swapInfo.getP1(), swapInfo.getP2());
	}

	public void findRoom() {
		RoomsManager roomsManager = RoomsManager.getInstance();
		Room room = roomsManager.findCooprtRoom();
		if (room == null) {
			this.write(new Info(Message.cannotFindCooprtGame));
		} else {
			ArrayList<PersonalInfo> personalInfos = new ArrayList<PersonalInfo>();
			personalInfos = room.getPlayersInfo();
			PersonalInfo personalInfo = room.getRoomHostPlayerInfo();
			Info findCooprtGameInfo = new FindCooprtGame(room.getRoomID(),
					personalInfos, personalInfo);
			this.write(findCooprtGameInfo);
		}
	}

	public void createRoom() {
		Room.roomId++;
		this.RoomID = Room.roomId;
		this.GameID = this.RoomID;
		Room room = new Room(this.RoomID);
		room.setRoomHostPlayer(this);
		room.addPlayer(this);
		RoomsManager roomsManager = RoomsManager.getInstance();
		roomsManager.addRoom(room);
		this.setReady(true);
		room.broadcastPlayersInfo();
	}

	public void ready() {
		this.setReady(true);
		RoomsManager roomsManager = RoomsManager.getInstance();
		Room room = roomsManager.getRoom(this.RoomID);
		room.someOneChangeState(this.name);
		room.checkCanStart();
	}

	public void noready() {
		this.setReady(false);
		RoomsManager roomsManager = RoomsManager.getInstance();
		Room room = roomsManager.getRoom(this.RoomID);
		room.someOneChangeState(this.name);
		room.checkCanStart();
	}

	public void startCooperateGame() {
		RoomsManager roomsManager = RoomsManager.getInstance();
		Room room = roomsManager.getRoom(this.RoomID);
		room.startGame();
	}

	public void joinRoom(JoinCooprtGame joinCooprtGame) {
		this.RoomID = joinCooprtGame.getGameID();
		RoomsManager roomsManager = RoomsManager.getInstance();
		Room room = roomsManager.getRoom(this.RoomID);
		if (room == null) {
			this.write(new Info(Message.cooperateGameHasStart));
		} else {
			char c = room.addPlayer(this);
			if (c == 'a') {
				this.GameID = this.RoomID;
				room.broadcastPlayersInfo();
			} else {
				this.write(new Info(Message.cooperateGameEnoughPlayer));
			}
		}
	}

	public void addFriend(AddFriendInfo addFriendInfo) {
		String name = addFriendInfo.getName();
		PlayersManager playersManager = PlayersManager.getInstance();
		if (!playersManager.becomeFriend(this.nickname, this.name, name)) {
			this.write(new Info(Message.friendToAddedOffline));
		}
	}

	public void agreeToBecomeFriend(AddFriendInfo agreeToBecomeFriendInfo) {
		String name = agreeToBecomeFriendInfo.getName();
		PlayersManager playersManager = PlayersManager.getInstance();
		if (this.becomeFriend(name)) {

			playersManager.agreeToBecomeFriend(this.nickname, this.name, name);
		} else {
			this.write(new Info(Message.addFriendFail));
			playersManager.notifyAddFriendFail(name);

		}
	}

	public void refuseToBecomeFriend(AddFriendInfo addFriendInfo) {
		String name = addFriendInfo.getName();
		PlayersManager playersManager = PlayersManager.getInstance();
		playersManager.refuseToBecomeFriend(this.nickname, this.name, name);
	}

	public void inviteFriend(InviteFriendInfo inviteFriendInfo) {
		String name = inviteFriendInfo.getName();
		PlayersManager playersManager = PlayersManager.getInstance();
		char success = playersManager.inviteFriendCooprate(this.nickname,
				this.name, this.RoomID, name);
		if (success == 'a') {
			this.channel
					.write(new Info(Message.friendToInvitedCooperateOffline));
		} else if (success == 'b') {
			this.channel
					.write(new Info(Message.friendToInvitedCooperateInRoom));
		}
	}

	public void agreeInvitedToCooperateGame(InviteFriendInfo inviteFriendInfo) {
		this.RoomID = inviteFriendInfo.getGameID();
		RoomsManager roomsManager = RoomsManager.getInstance();
		Room room = roomsManager.getRoom(this.RoomID);
		if (room == null) {// 游戏已经开始
			this.write(new Info(Message.cooperateGameHasStart));
		} else {
			char c = room.addPlayer(this);
			if (c == 'a') {
				this.GameID = this.RoomID;
				room.broadcastPlayersInfo();
			} else {
				this.write(new Info(Message.cooperateGameEnoughPlayer));
			}
		}
	}

	public void refuseInvitedToCooperateGame(InviteFriendInfo inviteFriendInfo) {
		String name = inviteFriendInfo.getName();
		int roomId = inviteFriendInfo.getGameID();
		PlayersManager playersManager = PlayersManager.getInstance();
		playersManager.refuseInvitedToCooperateGame(this.nickname, this.name,
				name, roomId);

	}

	@SuppressWarnings("unchecked")
	public void getSingleHistoryData() {
		ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems = new ArrayList<SingleGameHistoryItemInfo>();
		singleGameHistoryItems = this.singleGameHistoryDataService
				.getInfo(this.name);
		Collections.sort(singleGameHistoryItems, new SortByPoint());
		Info singleGameHistoryInfo = new SingleGameHistoryInfo(
				singleGameHistoryItems);
		this.write(singleGameHistoryInfo);
	}

	@SuppressWarnings("unchecked")
	public void getSingleGameRank() {
		ArrayList<SingleGameOverallItemInfo> singleGameOverallItemInfos = new ArrayList<SingleGameOverallItemInfo>();
		SingleOverallItem singleOverallItem = this.singleOverallDataService
				.getItem(this.name);
		SingleGameOverallItemInfo singleGameOverallItemInfo = new SingleGameOverallItemInfo(
				singleOverallItem.getMaxPoint(), this.nickname, this.imageType,
				this.name);
		singleGameOverallItemInfos.add(singleGameOverallItemInfo);
		ArrayList<FriendInfoPO> pos = new ArrayList<FriendInfoPO>();
		pos = this.relationDataService.getFriendList(this.name);
		for (FriendInfoPO po : pos) {
			String id = po.getId();
			singleOverallItem = this.singleOverallDataService.getItem(id);
			singleGameOverallItemInfo = new SingleGameOverallItemInfo(
					singleOverallItem.getMaxPoint(), po.getNickname(),
					po.getImageType(), po.getId());
			singleGameOverallItemInfos.add(singleGameOverallItemInfo);
		}
		Collections.sort(singleGameOverallItemInfos, new SortByPoint());
		Info singleGameRankInfo = new SingleGameRankInfo(
				singleGameOverallItemInfos);
		this.write(singleGameRankInfo);
	}

	@SuppressWarnings("unchecked")
	public void getMultiGameRank() {
		ArrayList<MultiOverallItemInfo> multiOverallItemInfos = new ArrayList<MultiOverallItemInfo>();
		MultiOverallItem multiOverallItem = this.multiOverallDataService
				.getItem(this.name);
		MultiOverallItemInfo multiOverallItemInfo = new MultiOverallItemInfo(
				this.imageType, multiOverallItem.getCoworker(),
				multiOverallItem.getMaxPoint(), this.name, this.nickname);
		multiOverallItemInfos.add(multiOverallItemInfo);
		ArrayList<FriendInfoPO> pos = new ArrayList<FriendInfoPO>();
		pos = this.relationDataService.getFriendList(this.name);
		for (FriendInfoPO po : pos) {
			String idString = po.getId();
			multiOverallItem = this.multiOverallDataService.getItem(idString);
			multiOverallItemInfo = new MultiOverallItemInfo(po.getImageType(),
					multiOverallItem.getCoworker(),
					multiOverallItem.getMaxPoint(), po.getId(),
					po.getNickname());
			multiOverallItemInfos.add(multiOverallItemInfo);

		}
		Collections.sort(multiOverallItemInfos, new SortByPoint());
		Info multiGameRankInfo = new MultiGameRankInfo(multiOverallItemInfos);
		this.write(multiGameRankInfo);
	}

	private void updateFriendsOnlineInfo() {
		ArrayList<Player> playersList = new ArrayList<Player>();
		PlayersManager playersManager = PlayersManager.getInstance();
		playersList = playersManager.getOnlinePlayers();

		ArrayList<FriendInfoPO> friendList = relationDataService
				.getFriendList(this.name);

		for (Player player : playersList) {
			String id = player.getName();
			for (FriendInfoPO item : friendList) {
				if (item.getId().equals(id)) {
					Info notifyOnlineInfo = new NotifyOnlineInfo(this.name);
					player.write(notifyOnlineInfo);
				}
			}
		}
	}

	public void changePasswd(ChangePasswdInfo changePasswdInfo) {
		SecurityPO po = new SecurityPO(this.name,
				changePasswdInfo.getNewPasswd());
		this.securityDataService.updatePswd(po);
		this.write(new Info(Message.changePasswdSuc));
	}

	public void changeAvatar(ChangeAvatarInfo changeAvatarInfo) {
		Avatar avatar = changeAvatarInfo.getAvatar();

		PersonalInfoPO personalInfoPO = new PersonalInfoPO(this.name,
				this.nickname, avatar, this.briefIntroduction, this.coins);
		if (this.personalInfoDataService.updateInfo(personalInfoPO)) {
			this.imageType=avatar;
			this.write(new Info(Message.changeAvatarSuc));
		} else {
			this.write(new Info(Message.changeAvatarFail));
		}

	}

	public void autoCrush() {
		GamesManager gamesManager = GamesManager.getInstance();
		Game game = gamesManager.getGame(this.GameID);
		game.autoCrash();
	}

	public void useProp(UsePropInfo usePropInfo) {
		Position position = usePropInfo.getPosition();
		GamesManager gamesManager = GamesManager.getInstance();
		Game game = gamesManager.getGame(this.GameID);
		game.useProp(position);
	}

	public void exitRoom() {
		RoomsManager roomsManager = RoomsManager.getInstance();
		Room room = roomsManager.getRoom(this.RoomID);
		if (room != null) {
			room.removePlayer(this);
		}
	}

	public Player(int iD, Channel channel) {
		this.ID=iD;
		this.channel = channel;
		this.personalInfoDataService = new PersonalInfoDataService();
		this.relationDataService = new RelationDataService();
		this.securityDataService = new SecurityDataService();
		this.singleGameHistoryDataService = new SingleHistoryDataService();
		this.multiGameHistoryDataService = new MultiHistoryDataService();
		this.singleOverallDataService = new SingleOverallDataService();
		this.multiOverallDataService = new MultiOverallDataService();

	}

	public void offLine() {
		RoomsManager roomsManager = RoomsManager.getInstance();
		Room room = roomsManager.getRoom(this.RoomID);
		if (room != null) {
			room.removePlayer(this);
		}
		GamesManager gamesManager = GamesManager.getInstance();
		Game game = gamesManager.getGame(this.GameID);
		if (game != null) {
			game.removePlayer(this.ID);
			if (game.getPlayersNum() == 0) {
				game.destroySelf();
				gamesManager.removeGame(this.GameID);
			}
		}
		PlayersManager playersManager = PlayersManager.getInstance();
		playersManager.removePlayer(this.ID);
		this.updateFriendsOnlineInfo();
	}

	private class SortByPoint implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			SingleGameHistoryItemInfo singleGameHistoryItem1 = (SingleGameHistoryItemInfo) o1;
			SingleGameHistoryItemInfo singleGameHistoryItem2 = (SingleGameHistoryItemInfo) o2;
			if (singleGameHistoryItem1.getPoint() > singleGameHistoryItem2
					.getPoint()) {
				return 1;
			}
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Friend> getFriendsList() {
		ArrayList<Friend> friends = new ArrayList<Friend>();
		ArrayList<FriendInfoPO> friendInfoPOs = new ArrayList<FriendInfoPO>();
		friendInfoPOs = this.relationDataService.getFriendList(this.name);
		PlayersManager playersManager = PlayersManager.getInstance();
		boolean inline = false;
		for (FriendInfoPO friendInfoPO : friendInfoPOs) {
			String name = friendInfoPO.getId();
			if (playersManager.hasPlayer(name)) {
				inline = true;
			}
			ArrayList<SingleGameHistoryItemInfo> singleGameHistoryItems = new ArrayList<SingleGameHistoryItemInfo>();
			singleGameHistoryItems = this.singleGameHistoryDataService
					.getInfo(name);
			Collections.sort(singleGameHistoryItems, new SortByPoint());
			Friend friend = new Friend(name, friendInfoPO.getBriefIntro(),
					friendInfoPO.getNickname(), friendInfoPO.getImageType(),
					friendInfoPO.getCoins(), inline, singleGameHistoryItems);
			friends.add(friend);
			inline = false;
		}
		Info friendsListInfo = new FriendsListInfo(friends);
		this.write(friendsListInfo);

		return friends;
	}

	public void deleteFriend(DeleteFriendInfo deleteFriendInfo) {
		RelationPO po = new RelationPO(this.name, name);
		if (this.relationDataService.deleteRecord(po)) {
			this.write(new Info(Message.deleteFriendSuc));
		} else {
			this.write(new Info(Message.deleteFriendFail));
		}
	}

	private boolean becomeFriend(String name) {
		RelationPO po = new RelationPO(this.name, name);
		return this.relationDataService.insertRecord(po);
	}

	public void updateSingleGameHistory(
			SingleGameHistoryItemInfo singleGameHistoryItem) {
		this.singleGameHistoryDataService.insertRecord(singleGameHistoryItem,
				this.name);
	}

	public void updateMulGameHistory(MultiGameHistoryItem multiGameHistoryItem) {
		this.multiGameHistoryDataService.insertRecord(multiGameHistoryItem);
	}

}
