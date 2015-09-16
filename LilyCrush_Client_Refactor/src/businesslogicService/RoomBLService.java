package businesslogicService;

import java.util.ArrayList;

import client.MyClient;
import view.MainFrame;
import view.RoomPanel;
import common.friendInfo.Friend;
import common.roomInfo.ChangeReadyStateInfo;
import common.roomInfo.FindCooprtGame;
import common.roomInfo.InviteFriendInfo;
import common.roomInfo.NewRoomInfo;

public interface RoomBLService {
	/*
	 * 点击协作游戏或者找到一个房间不想加入后需要调用的方法,返回值为网络连接是否正常
	 * 
	 */
	public void cooperationGame();
	/*
	 * 邀请好友时获得在线好友的列表
	 * 好友列表可能不是最新的
	 */
	public ArrayList<Friend> getOnlineFriendList();
	/*
	 * 邀请好友需要调用的方法，返回值为网络连接是否正常
	 */
	public void inviteFriend(String name);
	
	/*
	 * 询问是否同意被邀请进入游戏后点击同意后调用的方法，
	 * 参数为对方用户名，返回值为网络连接是否正常
	 */
	public void agreeInvitedToCooperateGame(String name);
	/*
	 * 询问是否同意被邀请进入游戏后点击拒绝后调用的方法，
	 * 参数为对方用户名，返回值为网络连接是否正常
	 */
	public void refuseInvitedToCooperateGame(String name);
	/*
	 * 点击加入协作房间的调用的方法
	 */
	public void joinCooprtGame();
	/*
	 * 点击重新选择房间调用的方法
	 */
	public void nojoinCooprtGame();
	/*
	 * 点击准备协作游戏后调用的方法，返回值为网络连接是否正常
	 */
	public void readyForCooprtGame();
	/*
	 * 点击取消准备协作游戏时调用的方法
	 */
	public void notReadyForCooprtGame();
	/*
	 * 点击创建房间后需要调用的方法，返回值为网络连接是否正常
	 */
	public void initCooprtGame() ;
	/*
	 * 点击开始协作游戏时调用的方法，返回值为网络连接是否正常
	 */
	public void startCooprtGame();
	
	/*
	 * 退出房间需要调用的方法
	 */
	public void exitRoom();
	/*
	 * 查看自己是否准备好的方法
	 */
	public boolean isIfReady();
	
	
	/*
	 * 提供给逻辑的接口
	 */
	public void setMainFrame(MainFrame mainFrame);
	public void setMyClient(MyClient myClient);
	public void roomFadeOut();
	public void setRoomPanel(RoomPanel roomPanel);
	public void askCreateARoom();
	public void askIfStartCooprtGame();
	public void findCooprateRoom(FindCooprtGame findCooprtGame);
	public void changeReadyState(ChangeReadyStateInfo changeReadyStateInfo);
	public void refreshRoom(NewRoomInfo newRoomInfo);
	public void friendToInvitedOffLine();
	public void askIfAgreeInvitedToCooperateGame(InviteFriendInfo inviteFriendInfo);
	public void cooperateGameHasStart();
	public void enoughPlayer();
	public void refuseInvitedToCooperateGame(InviteFriendInfo inviteFriendInfo);
	public void cannotStartGame();
	public void cannotStartGameHint();
}
