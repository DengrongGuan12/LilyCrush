package enums;

public enum Message {
	initOneGame,//初始化单人游戏
	 canStOneGame,//可以开始单人游戏
	 stOneGame,//开始单人游戏
	 swapPositions,//游戏进行中图标交换的位置
	 startSuperModel,//开始super模式
	 endSuperModel,//结束super模式
	 prompt,//提示
	 gameOver,//游戏结束
	 noCrash,//没有要消除的坐标
	 crash,//有要消除的坐标
	 newMap,//新的地图
	 CooperationGame,//协作游戏
	 cannotFindCooprtGame,//无法找到一个符合条件的协作游戏
	 createCooprtGame,//创建一个协作游戏
	 readyForCooprtGame,//准备好进行协作游戏
	 noReadyForCooprtGame,//取消准备
	 canStartCooprtGame,//可以开始协作游戏
	 startCooprtGame,//开始协作游戏
	 GameStart,//游戏开始
	 findCooprtGame,//找到合适的协作游戏
	 joinCooprtGame,//加入协作游戏
	
	 refreshRoomInfo,//更新房间中的玩家信息
	
	 login,//登录
	 loginSuc,//登录成功
	 loginFail,//登录失败
	 hasLogin,//已经登录
	 
	 register,//注册
	 registerSuc,//注册成功
	 registerFail,//注册失败，原因只有用户名已经存在
	
	 personalInfo,//个人信息
	
	 changePasswd,//修改密码
	 changePasswdSuc,//修改密码成功
	
	 getFriendsList,//获取好友列表
	 friendsList,//好友列表
	
	 deleteFriend,//删除好友
	 deleteFriendSuc,//删除好友成功
	 deleteFriendFail,//删除好友失败
	
	 addFriend,//增加好友
	 friendToAddedOffline,//增加的好友不在线
	 agreeToBecomeFriend,//同意与对方成为好友
	 addFriendFail,//由于重复添加数据库那边出错
	 refuseToBecomeFriend,//不同意成为好友
	
	 inviteFriendCooperate,//邀请好友进入协作游戏
	 friendToInvitedCooperateOffline,//被邀好友不在线
	 friendToInvitedCooperateInRoom,//被邀好友已经在该房间里面
	 agreeInvitedToCooperateGame,//被邀好友同意加入协作游戏
	 refuseInvitedToCooperateGame,//被邀好友拒绝加入协作游戏
	
	 cooperateGameHasStart,//协作游戏已经开始，不能加入，提示是否重新寻找房间
	 cooperateGameEnoughPlayer,//协作游戏人数已经满了
	
	 cannotStartCooperateGame,//把房主的开始按钮禁止
	 cannotStartCooprateGame_exception,//提示房主不能开始游戏
	 
	 getSingleHistoryData,//获取单人游戏的记录，用来显示在排行榜上
	 SingleHistoryData,//单人游戏记录
	 
	 getSingleGameRank,//获取单人游戏排行榜
	 singleGameRankInfo,//单人游戏排行榜
	 getMultiGameRank,//获取多人游戏记录信息
	 multiGameHistoryRank,//多人游戏排行榜
	 
	 changeAvatar,//修改头像
	 changeAvatarSuc,//修改头像成功
	 changeAvatarFail,//修改头像失败
	 
	 canAutoCrash,//在地图更新完之后可以进行自动消除的判断
	 
	 useProp,//使用道具
	 exitRoom,//退出房间（必然是在游戏开始之前）
	 
	 timeInfo,//时间信息
	
	 notifyOnlineInfo,//好友更新在线状态
	 lockMap,//在游戏结束没有消除时的消息
	 unlockMap,//解锁
	 
	 pauseSingleGame,//暂停
	 exitGame,//强制退出游戏
	 continueSingleGame,//继续单人游戏
	 
	 finalScore,//最终的得分
	 addedCoins,//增加的金币数
	 
	 roomFadeOut,//房间界面fadeout
	 
	 changeReadyState,//有人准备好或取消准备
	
}
