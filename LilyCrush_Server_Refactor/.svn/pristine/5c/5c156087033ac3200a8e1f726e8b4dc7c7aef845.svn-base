package dataService;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import common.rankingInfo.SingleGameHistoryItemInfo;

import sqlOperation.SQLHelper;
import sqlOperation.SQLHelperImpli;
import sqlOperation.Condition.ConditionItem;
import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;
import dataserviceImpli.SingleGameHistoryDataServiceImpli;

public class SingleHistoryDataService implements SingleGameHistoryDataServiceImpli{

	final String historyItemTable = "SingleHistoryItem";
	SQLHelperImpli sqlHelper = new SQLHelper();
	
	SingleOverallDataService singleOverallDataService = new SingleOverallDataService();
	
	@Override
	public boolean insertRecord(SingleGameHistoryItemInfo po , String id) {
		try {
			DatabaseRowInfo rowInfo = new DatabaseRowInfo();
			rowInfo.add(id);
			
			rowInfo.add(po.getDate());
			
			rowInfo.add(po.getPoint());
			rowInfo.add(po.getCombo());
			
			sqlHelper.insert(historyItemTable, rowInfo);
			
			SingleOverallItem overallItem = singleOverallDataService.getItem(id);
			
			//若第一次游戏则创建记录
			if (overallItem==null) {
				singleOverallDataService.insertInfo(new SingleOverallItem(id, po.getPoint(), 1));
			}
			else{
				singleOverallDataService.updateCount(id);	//更新总局数
		
				if(overallItem.getMaxPoint()<po.getPoint()){
					singleOverallDataService.updateMaxPoint(id, po.getPoint());
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
	public ArrayList<SingleGameHistoryItemInfo> getInfo(String id) {
		ArrayList<SingleGameHistoryItemInfo> list = new ArrayList<SingleGameHistoryItemInfo>();
		
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem("ID", id));
			try {
			 DefaultTableModel	table = sqlHelper.getTable(historyItemTable,
						"date,point,combo", condition);
			
			//TODO 先根据ID找到DATE，再根据ID和date找到记录
			for (int i=0;i<table.getRowCount();i++) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = formatter.parse(table.getValueAt(i, 0).toString());
				int point = Integer.parseInt(table.getValueAt(i, 1).toString());
				int combo = Integer.parseInt(table.getValueAt(i, 2).toString());
				
				SingleGameHistoryItemInfo item = new SingleGameHistoryItemInfo(date , point , combo);
				list.add(item);
			}
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return list;
	}
}
