package vos;

import java.util.ArrayList;

import enums.Direction;
import enums.DropMode;
import enums.VisualEffects;

//包括被消除的坐标（List）、新增块坐标、消除方向、新地图、道具A、道具B
public class MapRefreshInfoVO {
	private ArrayList<Location> crushList;
	private VisualEffects effect;
	private ArrayList<Location> newBlockList;
	private Direction direction;
	private Location blockDraged;
	private int point;		//此次消除产生的分数
	private DropMode dropMode;
	
	public MapRefreshInfoVO(ArrayList<Location> crushList,VisualEffects effect,ArrayList<Location> newList,Direction direction,Location blockDraged,int point,DropMode dropMode){
		this.crushList = crushList;
		this.effect = effect;
		this.newBlockList = newList;
		this.direction = direction;
		this.blockDraged = blockDraged;
		this.point = point;		//此次消除产生的分数
		this.dropMode = dropMode;
	}
	
	public ArrayList<Location> getCrushList() {
		return crushList;
	}

	public VisualEffects getEffect() {
		return effect;
	}

	public ArrayList<Location> getNewBlockList() {
		return newBlockList;
	}

	public Direction getDirection() {
		return direction;
	}

	public Location getBlockDraged() {
		return blockDraged;
	}

	public int getPoint() {
		return point;
	}

	public DropMode getDropMode() {
		return dropMode;
	}

}
