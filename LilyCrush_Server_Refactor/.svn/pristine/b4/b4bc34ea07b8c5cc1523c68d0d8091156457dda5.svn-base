package model.managers;

import java.util.ArrayList;

import model.Room;

public class RoomsManager {
	private static RoomsManager roomsManager;
	private ArrayList<Room> rooms = new ArrayList<Room>();

	private RoomsManager() {

	}

	public static synchronized RoomsManager getInstance() {
		if (roomsManager == null) {
			roomsManager = new RoomsManager();
		}
		return roomsManager;
	}

	/*
	 * 寻找一个符合条件的房间（1.协作游戏 2.人数2-4）
	 */
	public Room findCooprtRoom() {
		Room room1=null;
		for (Room room : rooms) {
			int size = room.getPlayersNum();
			if (size >= 1 && size <= 3 ) {
				room1=room;
				break;
			}

		}
		return room1;
	}
	public Room getRoom(int roomID) {
		Room room =null;
		for (Room room1:rooms) {
			if(room1.getRoomID()==roomID){
				room=room1;
				break;
			}
		}
		return room;
	}
	
	public void removeRoom(int roomId){
		for(Room room:rooms){
			if(room.getRoomID()==roomId){
				rooms.remove(room);
				break;
			}
		}
		
	}
	public void addRoom(Room room){
		this.rooms.add(room);
	}

}
