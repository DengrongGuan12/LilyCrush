package dataService;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.naming.CommunicationException;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import pos.SecurityPO;
import sqlOperation.SQLHelper;
import sqlOperation.SQLHelperImpli;
import sqlOperation.Condition.ConditionItem;
import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;
import sqlOperation.Condition.UpdateCondition;
import dataserviceImpli.SecurityDataServiceImpli;

public class SecurityDataService implements SecurityDataServiceImpli {

	final String tableName = "security";
	SQLHelperImpli sqlHelper = new SQLHelper();
	final String ID_STR = "ID";
	final String PSWD_STR = "pswd";
	
	//失败返回false
	@Override
	public boolean insertRecord(SecurityPO po) {
		try {
			DatabaseRowInfo rowInfo = new DatabaseRowInfo();
			rowInfo.add(po.getID());
			rowInfo.add(po.getPassword());

			sqlHelper.insert(tableName, rowInfo);
			return true;
		} 
		catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("error in network at class RleationDataService");
			return false;
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out
					.println("error in image operation at class RleationDataService");
			return false;
		}
	}

	@Override
	public boolean deleteRecord(String id) {
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem(ID_STR, id));
			sqlHelper.deleteInfo(tableName, condition);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePswd(SecurityPO po) {
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem(ID_STR, po.getID()));
			
			UpdateCondition updateCondition = new UpdateCondition();
			updateCondition.addItem(new ConditionItem(PSWD_STR , po.getPassword()));
			
			sqlHelper.updateInfo(tableName, updateCondition, condition);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	//账号不存在或有多条相同ID的记录则返回null
	@Override
	public String getPswd(String id) {
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem(ID_STR, id));

			DefaultTableModel table = sqlHelper.getTable(tableName,
					"pswd", condition);
			
			PersonalInfoDataService personalInfoService = new PersonalInfoDataService();
			if (table.getRowCount() == 1) {
				String password = table.getValueAt(0, 0).toString();
				return password;
			}
			else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("error in image operation at class RleationDataService");
			return null;
		}
	}

	@Override
	public boolean ifExist(String id) {
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem(ID_STR, id));

			DefaultTableModel table = sqlHelper.getTable(tableName,
					"pswd", condition);
			
			if (table.getRowCount() == 1) {
				return true;
			}
			else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("error in image operation at class RleationDataService");
			return true;
		}
	}

}
