package model.managers;

import java.util.ArrayList;

import model.Player;
import model.Room;
import common.Info;
import common.friendInfo.AddFriendInfo;
import common.roomInfo.InviteFriendInfo;
import enums.Message;
/*
 * 维护所有链接进来的玩家,单态模式
 */
public class PlayersManager {
	private static PlayersManager playersManager;
	private ArrayList<Player> players=new ArrayList<Player>();
	
	public ArrayList<Player> getOnlinePlayers(){
		return players;
	}
	private PlayersManager(){
		
	}
	public void addPlayer(Player player){
		this.players.add(player);
	}
	public void removePlayer(int ID){
		for(Player player:players){
			if(player.getID()==ID){
				players.remove(player);
				break;
			}
		}
	}
	public static synchronized PlayersManager getInstance(){
		if(playersManager==null){
			playersManager=new PlayersManager();
		}
		return playersManager;
	}
	public boolean hasPlayer(String name){
		boolean inline=false;
		for(Player player:players){
			if(player.getName().equals(name)){
				inline=true;
				break;
			}
		}
		return inline;
		
	}
	/*
	 * name2为被加的人,返回值为对方是否在线
	 */
	public boolean becomeFriend(String nickname,String name1,String name2){
		boolean inline=false;
		for(Player player:players){
			if(player.getName().equals(name2)){
				inline=true;
				Info addFriendInfo=new AddFriendInfo(nickname,name1);
				player.write(addFriendInfo);
				break;
			}
		}
		return inline;
	}
	/*
	 * 向name2发送：name1同意与name2成为好友
	 */
	public void agreeToBecomeFriend(String nickname,String name1,String name2){
		for(Player player:players){
			if(player.getName().equals(name2)){
				Info addFriendInfo=new AddFriendInfo(nickname,name1);
				addFriendInfo.setMessage(Message.agreeToBecomeFriend);
				player.write(addFriendInfo);
				break;
				
			}
		}
		
	}
	public void notifyAddFriendFail(String name){
		for(Player player:players){
			if(player.getName().equals(name)){
				player.write(new Info(Message.addFriendFail));
				break;
			}
		}
		
	}
	/*
	 * 向name2发送：name1拒绝与你成为好友
	 */
	public void refuseToBecomeFriend(String nickname,String name1,String name2){
		for(Player player:players){
			if(player.getName().equals(name2)){
				Info addFriendInfo=new AddFriendInfo(nickname, name1);
				addFriendInfo.setMessage(Message.refuseToBecomeFriend);
				player.write(addFriendInfo);
				break;
			}
		}
	}
	/*
	 * 向name2发送：name1想邀请你参加协作游戏
	 */
	public char inviteFriendCooprate(String nickname,String name1,int gameID,String name2){
		char invite='a';//表示不在线
		for(Player player:players){
			if(player.getName().equals(name2)){
				RoomsManager roomsManager=RoomsManager.getInstance();
				Room room=roomsManager.getRoom(gameID);
				if(room.hasPlayer(name2)){
					invite='b';//表示好友已经在该房间里面
				}else {
					invite='c';//发出邀请
					Info inviteFriendInfo=new InviteFriendInfo(nickname, name1,gameID);
					player.write(inviteFriendInfo);
				}
				break;
			}
		}
		return invite;
	}
	/*
	 * 向name2发送：name1拒绝加入协作游戏
	 */
	public void refuseInvitedToCooperateGame(String nickname,String name1,String name2,int gameId){
		for(Player player:players){
			if(player.getName().equals(name2)){
				//判断邀请者是否还在原来的房间里
				if(player.getGameID()==gameId){
					Info inviteFriendInfo =new InviteFriendInfo(nickname, name1, 0);
					inviteFriendInfo.setMessage(Message.refuseInvitedToCooperateGame);
					player.write(inviteFriendInfo);
				}
				
				break;
				
			}
		}
	}
	/*
	 * 判断某用户是否已经登录
	 */
	public boolean hasLogin(String name){
		boolean login=false;
		for(Player player:players){
			if(player.getName().equals(name)){
				login=true;
				break;
			}
		}
		return login;
	}
	
}
