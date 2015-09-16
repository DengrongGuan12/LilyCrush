package sqlOperation.Condition;

import java.util.ArrayList;

public class DatabaseCondition {
	ArrayList<ConditionItem> itemList = new ArrayList<ConditionItem>();
	
	public void addItem(ConditionItem item){
		itemList.add(item);
	}
	public String getCondition(){
		String condition = "";
		if(itemList.size()==0){
			return condition;
		}
		
		condition+=itemList.get(0).getCondition();
		for(int i=1;i<itemList.size();i++){
			condition+=" and "+itemList.get(i).getCondition();
		}
		return condition;
	}
}
