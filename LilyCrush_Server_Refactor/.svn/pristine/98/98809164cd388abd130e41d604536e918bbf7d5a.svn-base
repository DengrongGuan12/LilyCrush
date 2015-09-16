package dataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import sqlOperation.SQLHelper;
import sqlOperation.Condition.ConditionItem;
import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;
import sqlOperation.Condition.UpdateCondition;

public class MultiOverallDataService {
	private String tableName = "MultiMaxPoint";
	SQLHelper sqlHelper = new SQLHelper();
	private final String splitKey = ";";
		
	public boolean insertInfo(MultiOverallItem item) {
		try {
			DatabaseRowInfo rowInfo = new DatabaseRowInfo();
			rowInfo.add(item.getId());
			rowInfo.add(item.getMaxPoint());
			
			String coworkerString=list2str(item.getCoworker());
			
			rowInfo.add(coworkerString);
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


	public MultiOverallItem getItem(String id) {
		MultiOverallItem item = null;
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem("ID", id));
			
			DefaultTableModel table = sqlHelper.getTable(tableName,
					"maxPoint,coworker,count", condition);

			if (table.getRowCount() == 1) {
				int maxPoint = Integer.parseInt(table.getValueAt(0, 0).toString());
				
				String[] coworkerSplit = table.getValueAt(0, 1).toString().split(splitKey);
				ArrayList<String> coworkerList = new ArrayList<String>();
				for(int i=0;i<coworkerSplit.length;i++)
					coworkerList.add(coworkerSplit[i]);
				
				int count = Integer.parseInt(table.getValueAt(0, 2).toString());

				item = new MultiOverallItem(id, maxPoint, count,coworkerList);
				
				return item;
			} else {
				System.out.println("no record");
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
	public boolean updateMaxPoint(String id , int maxPoint , ArrayList<String> coworker){
		DatabaseCondition condition = new DatabaseCondition();
		condition.addItem(new ConditionItem("ID",id));
		
		String coworkerStr = list2str(coworker);
		
		UpdateCondition updateInfo = new UpdateCondition();
		updateInfo.addItem(new ConditionItem("maxPoint", maxPoint));
		updateInfo.addItem(new ConditionItem("coworker", coworkerStr));
		
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
		
		MultiOverallItem item = getItem(id);
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
	
	/**
	 * 将coworkerList转换为数据库的存储形式
	 */
	private String list2str(ArrayList<String> coworkerList){
		String coworkerString=coworkerList.get(0);
		for (int i = 1; i < coworkerList.size(); i++) {
			coworkerString+=splitKey+coworkerList.get(i);
		}
		return coworkerString;
	}
}
