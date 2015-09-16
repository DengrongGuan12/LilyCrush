package vos;

//зјБъ
public class Location {
	private int x;//0~8
	private int y;//0~8
	
	public Location(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}
