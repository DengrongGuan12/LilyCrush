package common.gameInfo;

import java.util.ArrayList;

import common.Info;

import enums.DropMode;
import enums.Message;

public class MapRefreshInfo extends Info {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PositionsGroup CrashPositionsGroup;
	private PositionsGroup newPositionsGroup;
	private DropMode dropMode;
	private int[][] newMap;
	private ArrayList<Pro> pros;
	private int score;

	public PositionsGroup getCrashPositionsGroup() {
		return CrashPositionsGroup;
	}

	public void setCrashPositionsGroup(PositionsGroup crashPositionsGroup) {
		CrashPositionsGroup = crashPositionsGroup;
	}

	public PositionsGroup getNewPositionsGroup() {
		return newPositionsGroup;
	}

	public void setNewPositionsGroup(PositionsGroup newPositionsGroup) {
		this.newPositionsGroup = newPositionsGroup;
	}

	public DropMode getDropMode() {
		return dropMode;
	}

	public void setDropMode(DropMode dropMode) {
		this.dropMode = dropMode;
	}

	public int[][] getNewMap() {
		return newMap;
	}

	public void setNewMap(int[][] newMap) {
		this.newMap = newMap;
	}

	public ArrayList<Pro> getPros() {
		return pros;
	}

	public void setPros(ArrayList<Pro> pros) {
		this.pros = pros;
	}

	public MapRefreshInfo(PositionsGroup crashPositionsGroup,
			PositionsGroup newPositionsGroup, DropMode dropMode,
			int newMap[][], ArrayList<Pro> pros,int score) {
		super(Message.crash);
		this.setCrashPositionsGroup(crashPositionsGroup);
		this.setNewPositionsGroup(newPositionsGroup);
		this.setDropMode(dropMode);
		this.setPros(pros);
		this.setNewMap(newMap);
		this.setScore(score);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
