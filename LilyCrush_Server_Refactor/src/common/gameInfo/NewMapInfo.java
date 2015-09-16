package common.gameInfo;

import java.io.Serializable;

import common.Info;

import enums.Message;

public class NewMapInfo extends Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int states[][];

	public int[][] getStates() {
		return states;
	}

	public void setStates(int[][] states) {
		this.states = states;
	}
	public NewMapInfo(int states[][]){
		super(Message.newMap);
		this.setStates(states);
		
	}
	public void printStates(){
		for (int i = 0; i < this.states.length; i++) {
			for (int j = 0; j < this.states[i].length; j++) {
				System.out.print(this.states[i][j] + ",");
			}
			System.out.println();
		}
	}
	

}
