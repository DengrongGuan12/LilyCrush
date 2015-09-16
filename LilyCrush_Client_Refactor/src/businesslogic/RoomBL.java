package businesslogic;

import java.util.ArrayList;

import client.MyClient;
import view.MainFrame;
import view.RoomPanel;
import common.Info;
import common.friendInfo.Friend;
import common.roomInfo.ChangeReadyStateInfo;
import common.roomInfo.CooprtGameInitInfo;
import common.roomInfo.FindCooprtGame;
import common.roomInfo.InviteFriendInfo;
import common.roomInfo.JoinCooprtGame;
import common.roomInfo.NewRoomInfo;
import common.settingInfo.PersonalInfo;
import enums.Message;
import businesslogic.managers.FriendsManager;
import businesslogicService.RoomBLService;

public class RoomBL implements RoomBLService{
	private MyClient myClient;
	private MainFrame mainFrame;
	private boolean ifReady=false;
	public void setMyClient(MyClient myClient) {
		this.myClient = myClient;
	}
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	private int roomID;
	private RoomPanel roomPanel;
	public void setRoomPanel(RoomPanel roomPanel){
		this.roomPanel=roomPanel;
		
	}
	public void cooperationGame() {
		if (!this.myClient.write(new Info(Message.CooperationGame))) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	@Override
	public ArrayList<Friend> getOnlineFriendList() {
		ArrayList<Friend> friendList = new ArrayList<Friend>();
		FriendsManager friendsManager=FriendsManager.getInstance();
		for (Friend friend : friendsManager.getFriends()) {
			if (friend.isInline()) {
				friendList.add(friend);
			}
		}
		return friendList;
	}

	public void inviteFriend(String name) {
		Info inviteFriendInfo = new InviteFriendInfo("", name, 0);
		if (!this.myClient.write(inviteFriendInfo)) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();

		}
	}


	public void agreeInvitedToCooperateGame(String name) {
		Info inviteFriendInfo = new InviteFriendInfo("", name, this.roomID);
		inviteFriendInfo.setMessage(Message.agreeInvitedToCooperateGame);
		if (!this.myClient.write(inviteFriendInfo)) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	public void refuseInvitedToCooperateGame(String name) {
		Info inviteFriendInfo = new InviteFriendInfo("", name, this.roomID);
		inviteFriendInfo.setMessage(Message.refuseInvitedToCooperateGame);
		if (!this.myClient.write(inviteFriendInfo)) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	public void joinCooprtGame() {

		Info joinCooprtGameInfo = new JoinCooprtGame(this.roomID);
		if (!this.myClient.write(joinCooprtGameInfo)) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	public void nojoinCooprtGame() {
		this.cooperationGame();
	}


	public void readyForCooprtGame() {
		if (!this.myClient.write(new Info(Message.readyForCooprtGame))) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			
			this.mainFrame.disconnected();
		}else {
			this.ifReady=true;
		}

	}

	@Override
	public void notReadyForCooprtGame() {
		if (!this.myClient.write(new Info(Message.noReadyForCooprtGame))) {
			this.mainFrame.disconnected();
		}else {
			this.ifReady=false;
		}

	}

	public void initCooprtGame() {
		Info cooprtGameInitInfo = new CooprtGameInitInfo(false, false, false);
		if (!this.myClient.write(cooprtGameInitInfo)) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}

	}

	public void startCooprtGame() {
		if (!this.myClient.write(new Info(Message.startCooprtGame))) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}

	public void exitRoom() {
		if (!this.myClient.write(new Info(Message.exitRoom))) {
			this.mainFrame.disconnected();
		}

	}

	@Override
	public boolean isIfReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void roomFadeOut() {
		this.roomPanel.fadeOut();
		
	}
	@Override
	public void askCreateARoom() {
		this.roomPanel.askCreateARoom();
		
	}
	@Override
	public void askIfStartCooprtGame() {
		this.roomPanel.askIfStartCooprtGame();
		
	}
	@Override
	public void findCooprateRoom(FindCooprtGame findCooprtGame) {
		int gameId = findCooprtGame.getGameID();
		this.roomID=gameId;
		ArrayList<PersonalInfo> personalInfos = new ArrayList<PersonalInfo>();
		PersonalInfo roomHostPersonalInfo = findCooprtGame
				.getRoomHostPersonalInfo();
		personalInfos = findCooprtGame.getPersonalsInfo();
		this.roomPanel.showRoom(personalInfos,
				roomHostPersonalInfo);
		this.roomPanel.askIfJoin();
		
	}
	@Override
	public void changeReadyState(ChangeReadyStateInfo changeReadyStateInfo) {
		String name=changeReadyStateInfo.getName();
		this.roomPanel.changeState(name);
		
	}
	@Override
	public void refreshRoom(NewRoomInfo newRoomInfo) {
		ArrayList<PersonalInfo> personalInfos1 = new ArrayList<PersonalInfo>();
		personalInfos1 = newRoomInfo.getPersonalInfos();
		PersonalInfo hostInfo = newRoomInfo.getHostInfo();
		/*
		 * 界面需要实现一个更新房间里的玩家头像，昵称的接口 public void
		 * refreshRoom(ArrayList<PersonalInfo> personalInfos){}
		 */
		this.roomPanel.showRoom(personalInfos1, hostInfo);
		
	}
	@Override
	public void friendToInvitedOffLine() {
		this.roomPanel.friendToInvitedOffLine();
		
	}
	@Override
	public void askIfAgreeInvitedToCooperateGame(InviteFriendInfo inviteFriendInfo) {
		String name2 = inviteFriendInfo.getName();
		String nickname2 = inviteFriendInfo.getNickname();
		this.roomID = inviteFriendInfo.getGameID();
		/*
		 * 需要实现一个询问是否同意被邀请加入房间的界面接口，参数为邀请者的用户名和昵称 public void
		 * askIfAgreeInvitedToCooperateGame(String nickname,String name){}
		 */
		this.roomPanel.askIfAgreeInvitedToCooperateGame(
				name2, nickname2);
		
	}
	@Override
	public void cooperateGameHasStart() {
		/*
		 * 需要实现一个提示玩家准备加入的协作游戏在一段时间内已经开始，询问是否重新查找协作房间 public void
		 * cooperateGameHasStart(){}
		 */
		this.roomPanel.cooperateGameHasStart();
		
	}
	@Override
	public void enoughPlayer() {
		/*
		 * 需要实现一个提示玩家准备加入的协作游戏在一段时间内人已经满了，询问是否重新查找协作房间 public void
		 * cooperateGameEnoughPlayers(){}
		 */
		this.roomPanel.cooperateGameEnoughPlayers();
		
	}
	@Override
	public void refuseInvitedToCooperateGame(InviteFriendInfo inviteFriendInfo) {
		String name3 = inviteFriendInfo.getName();
		String nickname3 = inviteFriendInfo.getNickname();
		/*
		 * 需要实现一个提示玩家对方拒绝接受邀请的界面接口，参数为对方用户名和昵称 public void
		 * refusedInviteFriendToCooperateGame(String name,String nickname){}
		 */

		this.roomPanel.refusedInviteFriendToCooperateGame(
				name3, nickname3);
		
	}
	@Override
	public void cannotStartGame() {
		this.roomPanel.cannotStartCooperateGame();
		
	}
	@Override
	public void cannotStartGameHint() {
		this.roomPanel.cannotStartGameHint();
		
	}
	

}
