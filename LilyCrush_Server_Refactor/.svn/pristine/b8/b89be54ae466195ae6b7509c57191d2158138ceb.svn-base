package model;
/*
 * 每次有消除的时候只要给他这个消除的时间（秒）就能自己判断出是否有连击并自行处理内部变量
 */
public class MultipleCrash {
	//连击次数
	private int number;
	//记录上一次消除的时间(秒)
	private int lastTime;
	private int interval;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getLastTime() {
		return lastTime;
	}
	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}
	/*
	 * 参数为时间间隔即连击时效
	 */
	public MultipleCrash(int interval){
		this.interval=interval;
		this.number=0;
	}
	/*
	 * 参数为消除的时间（秒）
	 */
	public boolean hasMultipleCrash(int second){
		boolean hasMul=false;
		int temp=second-this.lastTime;
		if(temp<0){
			temp=temp+60;
		}
		if(temp<=this.interval){
			this.number++;
			if(this.number>=4){
				hasMul=true;
				this.number=0;
				this.lastTime=second;
			}else {
				hasMul=false;
				this.lastTime=second;
			}
		}else {
			this.number=0;
			this.lastTime=second;
			hasMul=false;
		}
		return hasMul;
	}
	

}
