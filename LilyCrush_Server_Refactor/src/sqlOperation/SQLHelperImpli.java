package sqlOperation;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;
import sqlOperation.Condition.UpdateCondition;

public interface SQLHelperImpli {
	public void deleteInfo(String TableName,DatabaseCondition Condition) throws RemoteException;
	public DefaultTableModel getTable(String TableName,String columns,DatabaseCondition condition) throws RemoteException;
	public void insert(String TableName,DatabaseRowInfo Info) throws RemoteException;
	public void updateInfo(String TableName,UpdateCondition updateInfo, DatabaseCondition conditionInfo) throws RemoteException;
	public int getAutoNumber(String tableName);		//获得刚刚插入的元素的自增的号码
	public void insertSelectedColumn(String tableName,ArrayList<String> columnNames , DatabaseRowInfo info) throws RemoteException;	//在有默认字段或者自增字段的地方插入记录
}
