package common.gameInfo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
public class PositionsGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Position> positions;
	public PositionsGroup(){
		positions=new ArrayList<Position>();
	}
	public void addPosition(Position p){
		positions.add(p);
	}
	public void showPositions(){
		Iterator<Position> iterator=positions.iterator();
		while(iterator.hasNext()){
			iterator.next().showPosition();
			System.out.println();
		}
	}
	
	public Position get(int i) {
		return this.positions.get(i);
	}
	
	public int size(){
		return this.positions.size();
	}
	public boolean hasPosition(Position p){
		boolean has=false;
		for(Position temp:this.positions){
			if(temp.equal(p)){
				has=true;
				break;
			}
		}
		return has;
		
	}
	public PositionsGroup combine(PositionsGroup positionsGroup){
		PositionsGroup positionsGroup2=new PositionsGroup();
		for(Position p:this.positions){
			positionsGroup2.addPosition(p);
			
		}
		for(Position p:positionsGroup.positions){
			if(positionsGroup2.hasPosition(p)){
				continue;
			}else {
				positionsGroup2.addPosition(p);
			}
		}
		return positionsGroup2;
			
	}
	
	
	public ArrayList<Position> getArrayList(){
		return this.positions;
	}
	

}
