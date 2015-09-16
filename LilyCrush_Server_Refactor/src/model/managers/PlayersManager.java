package model.managers;

import java.util.ArrayList;

import model.Player;
import model.Room;
import common.Info;
import common.friendInfo.AddFriendInfo;
import common.roomInfo.InviteFriendInfo;
import enums.Message;
/*
 * ά���������ӽ��������,��̬ģʽ
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
	 * name2Ϊ���ӵ���,����ֵΪ�Է��Ƿ�����
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
	 * ��name2���ͣ�name1ͬ����name2��Ϊ����
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
	 * ��name2���ͣ�name1�ܾ������Ϊ����
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
	 * ��name2���ͣ�name1��������μ�Э����Ϸ
	 */
	public char inviteFriendCooprate(String nickname,String name1,int gameID,String name2){
		char invite='a';//��ʾ������
		for(Player player:players){
			if(player.getName().equals(name2)){
				RoomsManager roomsManager=RoomsManager.getInstance();
				Room room=roomsManager.getRoom(gameID);
				if(room.hasPlayer(name2)){
					invite='b';//��ʾ�����Ѿ��ڸ÷�������
				}else {
					invite='c';//��������
					Info inviteFriendInfo=new InviteFriendInfo(nickname, name1,gameID);
					player.write(inviteFriendInfo);
				}
				break;
			}
		}
		return invite;
	}
	/*
	 * ��name2���ͣ�name1�ܾ�����Э����Ϸ
	 */
	public void refuseInvitedToCooperateGame(String nickname,String name1,String name2,int gameId){
		for(Player player:players){
			if(player.getName().equals(name2)){
				//�ж��������Ƿ���ԭ���ķ�����
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
	 * �ж�ĳ�û��Ƿ��Ѿ���¼
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
