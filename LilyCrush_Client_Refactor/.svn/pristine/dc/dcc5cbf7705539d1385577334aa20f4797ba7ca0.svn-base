package businesslogic.managers;

import java.util.ArrayList;

import common.friendInfo.Friend;

public class FriendsManager {
	private ArrayList<Friend> friends=new ArrayList<Friend>();
	private String deleteFriendName;
	
	private static FriendsManager friendsManager;
	private FriendsManager(){
		
	}
	public synchronized static FriendsManager getInstance(){
		if(friendsManager==null){
			friendsManager=new FriendsManager();
		}
		return friendsManager;
	}
	public String getDeleteFriendName() {
		return deleteFriendName;
	}

	public void setDeleteFriendName(String deleteFriendName) {
		this.deleteFriendName = deleteFriendName;
	}

	public ArrayList<Friend> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<Friend> friends) {
		this.friends = friends;
	}
	
	public void deleteFriend(){
		for(Friend friend:friends){
			if(friend.getName().equals(this.deleteFriendName)){
				friends.remove(friend);
				break;
			}
		}
	}
	public boolean hasFriend(String name){
		boolean has=false;
		for(Friend friend:friends){
			if(friend.getName().equals(name)){
				has=true;
				break;
			}
		}
		return has;
	}
	public void updateOnlineInfo(String id){
		for(Friend friend:this.friends){
			if(friend.getId().equals(id)){
				if(friend.isInline()){
					friend.setInline(false);
				}else {
					friend.setInline(true);
				}
				
			}
		}
	}
	public Friend getFriend(String id){
		Friend friend = null;
		for(Friend temp:this.friends){
			if(temp.getId().equals(id)){
				friend = temp;
				break;
			}
		}
		return friend;
	}
	
	

}
