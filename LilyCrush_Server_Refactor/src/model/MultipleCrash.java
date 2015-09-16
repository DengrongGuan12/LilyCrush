package model;
/*
 * ÿ����������ʱ��ֻҪ�������������ʱ�䣨�룩�����Լ��жϳ��Ƿ������������д����ڲ�����
 */
public class MultipleCrash {
	//��������
	private int number;
	//��¼��һ��������ʱ��(��)
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
	 * ����Ϊʱ����������ʱЧ
	 */
	public MultipleCrash(int interval){
		this.interval=interval;
		this.number=0;
	}
	/*
	 * ����Ϊ������ʱ�䣨�룩
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
