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
	 * ���Э����Ϸ�����ҵ�һ�����䲻��������Ҫ���õķ���,����ֵΪ���������Ƿ�����
	 * 
	 */
	public void cooperationGame();
	/*
	 * �������ʱ������ߺ��ѵ��б�
	 * �����б����ܲ������µ�
	 */
	public ArrayList<Friend> getOnlineFriendList();
	/*
	 * ���������Ҫ���õķ���������ֵΪ���������Ƿ�����
	 */
	public void inviteFriend(String name);
	
	/*
	 * ѯ���Ƿ�ͬ�ⱻ���������Ϸ����ͬ�����õķ�����
	 * ����Ϊ�Է��û���������ֵΪ���������Ƿ�����
	 */
	public void agreeInvitedToCooperateGame(String name);
	/*
	 * ѯ���Ƿ�ͬ�ⱻ���������Ϸ�����ܾ�����õķ�����
	 * ����Ϊ�Է��û���������ֵΪ���������Ƿ�����
	 */
	public void refuseInvitedToCooperateGame(String name);
	/*
	 * �������Э������ĵ��õķ���
	 */
	public void joinCooprtGame();
	/*
	 * �������ѡ�񷿼���õķ���
	 */
	public void nojoinCooprtGame();
	/*
	 * ���׼��Э����Ϸ����õķ���������ֵΪ���������Ƿ�����
	 */
	public void readyForCooprtGame();
	/*
	 * ���ȡ��׼��Э����Ϸʱ���õķ���
	 */
	public void notReadyForCooprtGame();
	/*
	 * ��������������Ҫ���õķ���������ֵΪ���������Ƿ�����
	 */
	public void initCooprtGame() ;
	/*
	 * �����ʼЭ����Ϸʱ���õķ���������ֵΪ���������Ƿ�����
	 */
	public void startCooprtGame();
	
	/*
	 * �˳�������Ҫ���õķ���
	 */
	public void exitRoom();
	/*
	 * �鿴�Լ��Ƿ�׼���õķ���
	 */
	public boolean isIfReady();
	
	
	/*
	 * �ṩ���߼��Ľӿ�
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