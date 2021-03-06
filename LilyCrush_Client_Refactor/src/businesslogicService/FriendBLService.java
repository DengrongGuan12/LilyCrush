package businesslogicService;

import java.util.ArrayList;

import view.FriendsPanel;
import view.MainFrame;
import common.friendInfo.AddFriendInfo;
import common.friendInfo.Friend;
import common.friendInfo.FriendsListInfo;
import common.friendInfo.NotifyOnlineInfo;
import common.rankingInfo.SingleGameHistoryItemInfo;
import client.MyClient;

public interface FriendBLService {
	
	/*
	 * 获取好友列表或者刷新好友列表时需要调用的方法,返回值为网络连接是否正常
	 * 在好友操作时调用
	 */
	public void getFriendList();
	/*
	 * 获取好友具体信息
	 */
	public Friend getFriendInfo(String id);
	/*
	 * 删除好友时需要调用的方法，返回值为网络连接是否正常
	 */
	public void deleteFriend(String name);
	/*
	 * 添加好友时第一个需要调用的方法，返回值为是否已经是好友
	 */
	public boolean hasFriend(String name);
	/*
	 * 添加好友时第二个需要的调用的方法，返回值为网络连接是否正常
	 */
	public void addFriend(String name);
	/*
	 * 询问是否同意与对方成为好友时点击同意后调用的方法,参数为对方用户名,返回值为网络连接是否正常
	 */
	public void agreeToBecomeFriends(String name);
	/*
	 * 询问是否同意与对方成为好友时点击不同意后调用的方法,参数为对方用户名,返回值为网络连接是否正常
	 */
	public void refuseToBecomeFriend(String name);
	/*
	 * 获取好友的历史记录
	 */
	public ArrayList<SingleGameHistoryItemInfo> getFriendHistory(String id);
	
	/*
	 * 提供给逻辑的接口
	 */
	public void showFriendsList(FriendsListInfo friendsListInfo);
	public void setFriendsPanel(FriendsPanel friendsPanel);
	public void changeOnlineInfo(NotifyOnlineInfo notifyOnlineInfo);
	public void deleteFriendSuc();
	public void deleteFriendFail();
	public void friendToAddedOffline();
	public void askIfBecomeFriend(AddFriendInfo addFriendInfo);
	public void agreeToBecomeFriend(AddFriendInfo agreeToBecomeFriendInfo);
	public void addFriendFail();
	public void refuseToBecomeFriends(AddFriendInfo addFriendInfo);
	public void setMyClient(MyClient myClient);
	public void setMainFrame(MainFrame mainFrame);
}
