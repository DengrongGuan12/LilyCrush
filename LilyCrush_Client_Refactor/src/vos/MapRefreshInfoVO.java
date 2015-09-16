package vos;

import java.util.ArrayList;

import enums.Direction;
import enums.DropMode;
import enums.VisualEffects;

//���������������꣨List�������������ꡢ���������µ�ͼ������A������B
public class MapRefreshInfoVO {
	private ArrayList<Location> crushList;
	private VisualEffects effect;
	private ArrayList<Location> newBlockList;
	private Direction direction;
	private Location blockDraged;
	private int point;		//�˴����������ķ���
	private DropMode dropMode;
	
	public MapRefreshInfoVO(ArrayList<Location> crushList,VisualEffects effect,ArrayList<Location> newList,Direction direction,Location blockDraged,int point,DropMode dropMode){
		this.crushList = crushList;
		this.effect = effect;
		this.newBlockList = newList;
		this.direction = direction;
		this.blockDraged = blockDraged;
		this.point = point;		//�˴����������ķ���
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
