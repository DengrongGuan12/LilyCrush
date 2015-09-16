package dataService;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.table.DefaultTableModel;



import pos.PersonalInfoPO;
import sqlOperation.SQLHelper;
import sqlOperation.SQLHelperImpli;
import sqlOperation.Condition.ConditionItem;
import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;
import sqlOperation.Condition.UpdateCondition;
import dataserviceImpli.PersonalInfoDataServiceImpli;
import enums.Avatar;

public class PersonalInfoDataService implements PersonalInfoDataServiceImpli {

	final String tableName = "PersonalInfo";
	SQLHelperImpli sqlHelper = new SQLHelper();
	final String ID_STR = "ID";
	final String NICKNAME_STR = "nickname";
	final String IMAGETYPE_STR = "imageType";
	final String INTRO_STR = "intro";
	final String COIN_STR = "coin";

	@Override
	public boolean insertInfo(PersonalInfoPO po) {
		try {
			DatabaseRowInfo rowInfo = new DatabaseRowInfo();
			rowInfo.add(po.getId());
			rowInfo.add(po.getNickname());
			rowInfo.add(po.getAvatar().toString());
			rowInfo.add(po.getBriefIntroduction());
			rowInfo.add(po.getCoin());

			sqlHelper.insert(tableName, rowInfo);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out
					.println("error in network at class PersonalInfoDataService");
			return false;
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out
					.println("error in image operation at class PersonalInfoDataService");
			return false;
		}
	}

	@Override
	public boolean deleteInfo(String id) {
		DatabaseCondition condition = new DatabaseCondition();
		condition.addItem(new ConditionItem(ID_STR, id));

		try {
			sqlHelper.deleteInfo(tableName, condition);

			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateInfo(PersonalInfoPO po) {
		try {
			
			System.out.println("change personal info");
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem(ID_STR, po.getId()));
			
			UpdateCondition updateCondition = new UpdateCondition();
			updateCondition.addItem(new ConditionItem(NICKNAME_STR, po.getNickname()));
			updateCondition.addItem(new ConditionItem(IMAGETYPE_STR, po.getAvatar().toString()));
			updateCondition.addItem(new ConditionItem(INTRO_STR, po.getBriefIntroduction()));
			
			sqlHelper.updateInfo(tableName, updateCondition, condition);
			
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out
					.println("error in network at class PersonalInfoDataService");
			return false;
		}
	}

	@Override
	public PersonalInfoPO getInfo(String id) {
		PersonalInfoPO po = null;
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem(ID_STR, id));
			
			
			DefaultTableModel table = sqlHelper.getTable(tableName,
					"nickname,imageType,intro,coin", condition);

			if (table.getRowCount() == 1) {
				String nickname = table.getValueAt(0, 0).toString();
				String image = table.getValueAt(0, 1).toString();
				String briefIntroduction = table.getValueAt(0, 2).toString();
				int coin = Integer.parseInt(table.getValueAt(0, 3).toString());

				Avatar type = Avatar.valueOf(image);

				po = new PersonalInfoPO(id, nickname, type, briefIntroduction,
						coin);
			} else {
				System.out.println("no record");
				return null;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out
					.println("error in network at class PersonalInfoDataService");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("error in image operation at class PersonalInfoDataService");
			return null;
		}
		return po;
	}

	@Override
	public boolean updateCoins(String id, int coins) {
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem(ID_STR,id));
			
			UpdateCondition updateCondition = new UpdateCondition();
			updateCondition.addItem(new ConditionItem(COIN_STR, coins));
			
			sqlHelper.updateInfo(tableName, updateCondition, condition);
			
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out
					.println("error in network at class PersonalInfoDataService");
			return false;
		}
	}

}
