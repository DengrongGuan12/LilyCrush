package businesslogic.managers;

import enums.Prop;

public class PropManager {
	private static PropManager propManager;
	private int coins;
	private boolean hasC=false;
	private boolean hasD=false;
	private boolean hasE=false;
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public boolean isHasC() {
		return hasC;
	}
	public void setHasC(boolean hasC) {
		this.hasC = hasC;
	}
	public boolean isHasD() {
		return hasD;
	}
	public void setHasD(boolean hasD) {
		this.hasD = hasD;
	}
	public boolean isHasE() {
		return hasE;
	}
	public void setHasE(boolean hasE) {
		this.hasE = hasE;
	}
	public boolean chooseProp(Prop p){
		boolean canChoose = false;
		switch (p) {
		case C:
			if(this.coins >= p.getPrice()){
				canChoose = true;
				hasC=true;
				this.coins-=p.getPrice();
			}
			break;
		case D:
			if(this.coins >= p.getPrice()){
				canChoose = true;
				hasD=true;
				this.coins-=p.getPrice();
			}
			break;
		case E:
			if(this.coins >= p.getPrice()){
				canChoose = true;
				hasE=true;
				this.coins-=p.getPrice();
			}
			break;
		default:
			break;
		}
		System.out.println("choose coins:"+this.coins);
		return canChoose;
	}
	public void noChooseProp(Prop p){
		switch (p) {
		case C:
			hasC=false;
			this.coins+=p.getPrice();
			break;
		case D:
			hasD=false;
			this.coins+=p.getPrice();
			break;
		case E:
			hasE=false;
			this.coins+=p.getPrice();
			break;
		default:
			break;
		}
		System.out.println("nochoose coins:"+this.coins);
	}
	public void recoverCoins(){
		if(hasC){
			this.coins+=100;
			hasC=false;
		}
		if(hasD){
			this.coins+=200;
			hasD=false;
		}
		if(hasE){
			this.coins+=300;
			hasE=false;
		}
	}
	private PropManager(){
		
	}
	public synchronized static PropManager getInstance(){
		if(propManager==null){
			propManager=new PropManager();
		}
		return propManager;
	}
	
}
