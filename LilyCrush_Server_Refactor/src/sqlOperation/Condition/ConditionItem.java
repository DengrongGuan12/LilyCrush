package sqlOperation.Condition;

import sqlOperation.ValueType;

public class ConditionItem {
	private String key;
	private int intValue;
	private String strValue;
	private ValueType type;
	
	public ConditionItem(String key,int value){
		this.key = key;
		intValue = value;
		type = ValueType.Int;
	}
	public ConditionItem(String key,String value){
		this.key = key;
		strValue = value;
		type = ValueType.Str;
	}
	
	public String getCondition(){
		String temp = key+"=";
		switch (type) {
		case Str:
			return temp+"'"+strValue+"'";
		case Int:
			return temp+intValue;
		default:
			return null;
		}
	}
}
