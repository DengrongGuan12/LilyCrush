package viewService;

import java.util.ArrayList;

import common.settingInfo.PersonalInfo;
import enums.Avatar;
import vos.PersonalInfoVO;

public interface RoomInterface {
	
//	  需要实现一个提示没有合适的房间询问是否要创建一个房间
	  public void askCreateARoom();
	 
//	  需要实现一个询问是否准备好协作游戏的方法    TODO 是否已经准备好不是应该界面调用controller来设置比较好么
	  public void askIfReadyForCooprtGame();
	 
//	  向房主询问是否开始游戏的方法，可以用按钮是否可以点击来实现  TODO 同上
	  public void askIfStartCooprtGame();
	 
//	  界面需要实现一个更新房间里的玩家头像，昵称的接口
	  public void refreshRoom(PersonalInfo host,ArrayList<PersonalInfo> personalInfos);
	  
// 界面需要实现一个房间里的玩家头像，昵称的接口,参数为已经加入的玩家的信息，包括昵称，金币数，头像的图片类型,同时需要询问是否要加入房间
	void showRoom(ArrayList<PersonalInfo> personalInfos,
			PersonalInfo hostInfo);
	
	 //需要一个显示被邀请好友不在线的提醒
	 public void friendToInvitedOffLine();

	 //需要实现一个询问是否同意被邀请加入房间的界面接口，参数为邀请者的用户名和昵称
	 public void askIfAgreeInvitedToCooperateGame(String ID ,String nickname);
	 
	 // 需要实现一个提示玩家准备加入的协作游戏在一段时间内已经开始，询问是否重新查找协作房间
	  public void cooperateGameHasStart();
	
	 // 需要实现一个提示玩家准备加入的协作游戏在一段时间内人已经满了，询问是否重新查找协作房间
	  public void cooperateGameEnoughPlayers();
	
	 // 需要实现一个提示玩家对方拒绝接受邀请的界面接口，参数为对方用户名和昵称
	 public void refusedInviteFriendToCooperateGame(String name,String nickname);
	
	 // 需要提供一个将房主可以开始的按钮禁止的方法
	  public void cannotStartCooperateGame();

	  //因人数变动原因而无法开始游戏
	  public void cannotStartGameHint();
	  
	  //有人修改准备状态的时候
	  public void changeState(String id);
}
