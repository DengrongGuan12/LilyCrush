package dataService;

import java.rmi.RemoteException;

import javax.swing.table.DefaultTableModel;

import sqlOperation.SQLHelper;
import sqlOperation.Condition.ConditionItem;
import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;
import sqlOperation.Condition.UpdateCondition;

/**
 * 获取单人游戏历史最高记录
 *
 */
public class SingleOverallDataService {
private String tableName = "SingleMaxPoint";
SQLHelper sqlHelper = new SQLHelper();
	
public boolean insertInfo(SingleOverallItem item) {
	try {
		DatabaseRowInfo rowInfo = new DatabaseRowInfo();
		rowInfo.add(item.getId());
		rowInfo.add(item.getMaxPoint());
		rowInfo.add(item.getNumber());

		sqlHelper.insert(tableName, rowInfo);
		return true;
	} catch (RemoteException e) {
		e.printStackTrace();
		System.out
				.println("error in network at class SingleOverallDataService");
		return false;
	} 
}

public boolean deleteInfo(String id) {
	DatabaseCondition condition = new DatabaseCondition();
	condition.addItem(new ConditionItem("ID", id));

	try {
		sqlHelper.deleteInfo(tableName, condition);

		return true;
	} catch (RemoteException e) {
		e.printStackTrace();
		return false;
	}
}


public SingleOverallItem getItem(String id) {
	SingleOverallItem item = null;
	try {
		DatabaseCondition condition = new DatabaseCondition();
		condition.addItem(new ConditionItem("ID", id));
		
		DefaultTableModel table = sqlHelper.getTable(tableName,
				"maxPoint,count", condition);

		if (table.getRowCount() == 1) {
			int maxPoint = Integer.parseInt(table.getValueAt(0, 0).toString());
			int count = Integer.parseInt(table.getValueAt(0, 1).toString());

			item = new SingleOverallItem(id, maxPoint, count);
			
			return item;
		} else {
			System.out.println("singleOverallDataService no record");
			return null;
		}
	} catch (RemoteException e) {
		e.printStackTrace();
		System.out
				.println("error in network at class PersonalInfoDataService");
		return null;
	}
}

/**
 * 
 * 更新最高分，同时为局数自动加一
 */
public boolean updateMaxPoint(String id , int maxPoint){
	DatabaseCondition condition = new DatabaseCondition();
	condition.addItem(new ConditionItem("ID",id));
	
	UpdateCondition updateInfo = new UpdateCondition();
	updateInfo.addItem(new ConditionItem("maxPoint", maxPoint));
	
	
	try {
		sqlHelper.updateInfo(tableName, updateInfo, condition);
		updateCount(id);
		return true;
	} catch (RemoteException e) {
		e.printStackTrace();
		return false;
	}
}

/**
 * 
 * 总局数增加1
 */
public boolean updateCount(String id){
	DatabaseCondition condition = new DatabaseCondition();
	condition.addItem(new ConditionItem("ID",id));
	
	SingleOverallItem item = getItem(id);
	
	int count = item.getNumber()+1;
	UpdateCondition updateInfo = new UpdateCondition();
	updateInfo.addItem(new ConditionItem("count", count));
	
	try {
		sqlHelper.updateInfo(tableName, updateInfo, condition);
		return true;
	} catch (RemoteException e) {
		e.printStackTrace();
		return false;
	}
}

}