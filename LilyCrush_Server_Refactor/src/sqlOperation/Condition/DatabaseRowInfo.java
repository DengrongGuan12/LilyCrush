package sqlOperation.Condition;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DatabaseRowInfo {
	private String rowInfo="";
	
	public void add(String value){
		if(rowInfo.equals(""))
			rowInfo = "'"+value+"'";
		else
			rowInfo+=",'"+value+"'";
	}
	public void add(int value){
		if(rowInfo.equals(""))
			rowInfo = value+"";
		else
			rowInfo+=","+value;
	}
	public void add(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = format.format(new Date().getTime());
		if(rowInfo.equals(""))
			rowInfo = "'"+dateString+"'";
		else
			rowInfo+=",'"+dateString+"'";
		
		System.out.println(rowInfo);
	}
	
	public String getRowInfo(){
		return rowInfo;
	}
	
}
