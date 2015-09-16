package dataserviceImpli;

import pos.PersonalInfoPO;


/**
 * 调用sqlOperation
 *
 */
public interface PersonalInfoDataServiceImpli {
	public boolean insertInfo(PersonalInfoPO po);
	public boolean deleteInfo(String id);
	public boolean updateInfo(PersonalInfoPO po);
	public PersonalInfoPO getInfo(String id);
	public boolean updateCoins(String id,int coins);//更新金币数量
}
