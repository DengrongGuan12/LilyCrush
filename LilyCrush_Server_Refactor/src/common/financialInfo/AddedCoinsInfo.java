package common.financialInfo;

import common.Info;

import enums.Message;

public class AddedCoinsInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int coins;
	public int getCoins(){
		return this.coins;
	}
	public AddedCoinsInfo(int coins){
		super(Message.addedCoins);
		this.coins=coins;
	}

}
