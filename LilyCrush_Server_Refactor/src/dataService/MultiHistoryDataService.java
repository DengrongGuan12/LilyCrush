package dataService;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import dataserviceImpli.MultiGameHistoryDataServiceImpli;

import pos.historyPO.MultiGameHistoryItem;
import sqlOperation.SQLHelper;
import sqlOperation.SQLHelperImpli;
import sqlOperation.Condition.ConditionItem;
import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;

public class MultiHistoryDataService implements MultiGameHistoryDataServiceImpli {
	final String itemTable = "MultiGameItem";
	final String relationTable = "MultiGameRelation";
	SQLHelperImpli sqlHelper = new SQLHelper();
	final String splitKey  = ";";
	MultiOverallDataService multiOverallDataService = new MultiOverallDataService();
	
	
	public static void main(String args[]){
		MultiHistoryDataService multiHistoryDataService = new MultiHistoryDataService();
		ArrayList< String> list = new ArrayList<>();
		list.add("user2");
		list.add("user1");
		multiHistoryDataService.insertRecord(new MultiGameHistoryItem(new Date(), 1000, 1823, list));
	}
	
	@Override
	public boolean insertRecord(MultiGameHistoryItem item) {
		try {
			DatabaseRowInfo rowInfo = new DatabaseRowInfo();

			ArrayList<String> columnList = new ArrayList<String>();
			columnList.add("date");
			columnList.add("coworker");
			columnList.add("point");
			columnList.add("combo");
			
			rowInfo.add(item.getDate());
			rowInfo.add(list2str(item.getCoworker()));
			rowInfo.add(item.getPoint());
			rowInfo.add(item.getCombo());
			
			sqlHelper.insertSelectedColumn(itemTable, columnList, rowInfo);
			
			int point = item.getPoint();
			ArrayList<String> coworker = item.getCoworker();
			
			int gameNumber = sqlHelper.getAutoNumber(itemTable);
			for(String id:item.getCoworker()){
				
			DatabaseRowInfo rowInfo2 = new DatabaseRowInfo();
			rowInfo2.add(id);
			rowInfo2.add(gameNumber);
			sqlHelper.insert(relationTable, rowInfo2);
			
			MultiOverallItem multiItem = multiOverallDataService.getItem(id);
			if (multiItem==null) {
				multiOverallDataService.insertInfo(new MultiOverallItem(id, point, 1, coworker));
			}
			else{
				int personalPoint = multiOverallDataService.getItem(id).getMaxPoint();
				multiOverallDataService.updateCount(id);
				if (item.getPoint()>personalPoint) {
					multiOverallDataService.updateMaxPoint(id, item.getPoint(), item.getCoworker());
				}
			}
		}
			
			
			
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("error in network at class RleationDataService");
			return false;
		}
	}

	@Override
	public ArrayList<MultiGameHistoryItem> getInfo(String id) {
		ArrayList<MultiGameHistoryItem> list = new ArrayList<MultiGameHistoryItem>();
		
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem("ID", id));
			try {
			 DefaultTableModel	table = sqlHelper.getTable(relationTable,
						"gameNumber", condition);
			 
			for (int i=0;i<table.getRowCount();i++) {
				int itemNumber = Integer.parseInt(table.getValueAt(i, 0).toString());
				DatabaseCondition condition2 = new DatabaseCondition();
				condition2.addItem(new ConditionItem("gameNumber", itemNumber));
				
				DefaultTableModel	table2 = sqlHelper.getTable(itemTable,
						"date,coworker,point,combo", condition2);
				if(table2.getRowCount() == 1){
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = formatter.parse(table2.getValueAt(0, 0).toString());
					
					String[] splitList = table2.getValueAt(0, 1).toString().split(splitKey);
					ArrayList<String> coworkerList = new ArrayList<String>();
					for(int j=0;j<splitList.length;j++)
						coworkerList.add(splitList[j]);
					
					int point = Integer.parseInt(table2.getValueAt(0, 2).toString());
					int combo = Integer.parseInt(table2.getValueAt(0,3).toString());
					
					MultiGameHistoryItem item  = new MultiGameHistoryItem(date,point,combo,coworkerList);
					list.add(item);
				}
				
			}
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return list;
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
