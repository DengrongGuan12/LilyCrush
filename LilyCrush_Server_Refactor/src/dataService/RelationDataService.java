package dataService;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;



import pos.FriendInfoPO;
import pos.PersonalInfoPO;
import pos.RelationPO;
import sqlOperation.SQLHelper;
import sqlOperation.SQLHelperImpli;
import sqlOperation.Condition.ConditionItem;
import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;
import dataserviceImpli.RelationDataServiceImpli;
import enums.Avatar;

public class RelationDataService implements RelationDataServiceImpli {

	final String tableName = "Friends";
	SQLHelperImpli sqlHelper = new SQLHelper();

	@Override
	public boolean insertRecord(RelationPO po) {
		try {
			DatabaseRowInfo rowInfo = new DatabaseRowInfo();
			rowInfo.add(po.getId1());
			rowInfo.add(po.getId2());

			sqlHelper.insert(tableName, rowInfo);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("error in network at class RleationDataService");
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out
					.println("error in image operation at class RleationDataService");
			return false;
		}
	}

	@Override
	public boolean deleteRecord(RelationPO po) {
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem("userID1", po.getId1()));
			condition.addItem(new ConditionItem("userID2", po.getId2()));
			sqlHelper.deleteInfo(tableName, condition);
			DatabaseCondition condition2 = new DatabaseCondition();
			condition2.addItem(new ConditionItem("userID1", po.getId2()));
			condition2.addItem(new ConditionItem("userID2", po.getId1()));
			sqlHelper.deleteInfo(tableName, condition2);
			
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ArrayList<FriendInfoPO> getFriendList(String id) {
		ArrayList<FriendInfoPO> list = new ArrayList<FriendInfoPO>();
		try {
			DatabaseCondition condition = new DatabaseCondition();
			condition.addItem(new ConditionItem("userID1", id));

			DefaultTableModel table = sqlHelper.getTable(tableName,
					"userID2", condition);
			
			PersonalInfoDataService personalInfoService = new PersonalInfoDataService();
			for (int i=0;i<table.getRowCount();i++) {
				String userID2 = table.getValueAt(i, 0).toString();
				PersonalInfoPO info = personalInfoService.getInfo(userID2);
				Avatar avatar = info.getAvatar();
				String nickname= info.getNickname();
				int coin = info.getCoin();
				String briefIntro=info.getBriefIntroduction();
				FriendInfoPO friendItem = new FriendInfoPO(userID2 , nickname , avatar , coin,briefIntro);
				list.add(friendItem);
			}
			
			condition = new DatabaseCondition();
			condition.addItem(new ConditionItem("userID2", id));

			table = sqlHelper.getTable(tableName,
					"userID1", condition);
			for (int i=0;i<table.getRowCount();i++) {
				String userID = table.getValueAt(i, 0).toString();
				PersonalInfoPO info = personalInfoService.getInfo(userID);
				Avatar avatar = info.getAvatar();
				String nickname= info.getNickname();
				int coin = info.getCoin();
				String briefIntro=info.getBriefIntroduction();
				FriendInfoPO friendItem = new FriendInfoPO(userID , nickname,avatar,coin,briefIntro);
				list.add(friendItem);
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("error in image operation at class RleationDataService");
			return null;
		}
	}


}
