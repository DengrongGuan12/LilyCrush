package UIMock;

import java.util.ArrayList;

import enums.Direction;
import enums.VisualEffects;

import vos.Location;

public class CheckIfCrushMock {
	
	
	//返回是否能够消除
	public boolean check(){
		return true;
	}
	
	public ArrayList<Location> getLocation(Location l,Direction dire){
		ArrayList<Location> list = new ArrayList<Location>();
		list.add(l);
		if(l.getX()>0){
			list.add(new Location(l.getX()-1, l.getY()));
		}
		Location l3 = new Location(l.getX()+1, l.getY());
		
		list.add(l3);
		
		return list;
	}
	
	public VisualEffects getEffect(){
		return VisualEffects.normal;
	}
}
