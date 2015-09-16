package sqlOperation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;




import common.rankingInfo.SingleGameHistoryItemInfo;

import dataserviceImpli.MultiGameHistoryDataServiceImpli;
import dataserviceImpli.PersonalInfoDataServiceImpli;
import dataService.MultiHistoryDataService;
import dataService.PersonalInfoDataService;
import dataService.RelationDataService;
import dataService.SingleHistoryDataService;
import dataService.SingleOverallDataService;
import dataService.SingleOverallItem;
import enums.Avatar;
import pos.FriendInfoPO;
import pos.PersonalInfoPO;
import pos.RelationPO;
import pos.historyPO.MultiGameHistoryItem;

public class Test {

	/**
	 * @param args
	 * @throws RemoteException
	 * 
	 */
	public static void main(String[] args) throws RemoteException {
		//测试personalInfoDataservice
//		PersonalInfoDataService service = new PersonalInfoDataService();
//			PersonalInfoPO po = new PersonalInfoPO("1234", "nickYang", type,
//					"myBriefIntro", 100);
//			
//			service.insertInfo(po);
//			PersonalInfoPO po2 = service.getInfo("1234");
//			System.out.println(po2.getBriefIntroduction());
			
		
		
		
//			//测试RelationDataService
//			RelationDataService relationService = new RelationDataService();
//			relationService.insertRecord(new RelationPO("1234", "1234"));
//			ArrayList<FriendInfoPO> list = relationService.getFriendList("1234");
//			System.out.println(list.get(0).getNickname());
		
//		//测试SingleHistoryDataService
//		SingleHistoryDataService singleHistoryDataService = new SingleHistoryDataService();
//		singleHistoryDataService.insertRecord(new SingleGameHistoryItem(new Date(),100, 200), "1234");
//		System.out.println(singleHistoryDataService.getInfo("1234").get(0).getPoint());
		
//		//测试SingleOverallDataService
//		SingleOverallDataService singleOverallDataService = new SingleOverallDataService();
////		singleOverallDataService.insertInfo(new SingleOverallItem("1234", 10000,20));
//		singleOverallDataService.updateMaxPoint("1234", 20000);
//		System.out.println(singleOverallDataService.getItem("1234").getMaxPoint() +"   "+singleOverallDataService.getItem("1234").getNumber());
//		System.out.println(singleOverallDataService.getItem("1236")==null);		//不存在的ID返回空

		
		//测试MultiHistoryDataService
		MultiGameHistoryDataServiceImpli multiHistory = new MultiHistoryDataService();
		ArrayList<String> coworkerList =  new ArrayList<String>();
		coworkerList.add("3456");
		coworkerList.add("1243");
		coworkerList.add("1234");
		multiHistory.insertRecord(new MultiGameHistoryItem(new Date(), 30000, 120, coworkerList));
		System.out.println(multiHistory.getInfo("1234").get(0).getCoworker());
		
	}
	
	/**
	 * 重置MultiGameItem中的自增字段初始值为1
	 * @throws SQLException 
	 */
	public void reset() throws SQLException{
		LinkToMySQL linkToMySQL;
		linkToMySQL = new LinkToMySQL();
		Statement statement = linkToMySQL.connection.createStatement();
		try {
			// 执行SQL语句
			statement = linkToMySQL.connection.createStatement();
			 statement.executeUpdate("truncate "+"MultiGameItem");
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}
}
