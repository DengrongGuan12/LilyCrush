package dataserviceImpli;

import pos.PersonalInfoPO;


/**
 * ����sqlOperation
 *
 */
public interface PersonalInfoDataServiceImpli {
	public boolean insertInfo(PersonalInfoPO po);
	public boolean deleteInfo(String id);
	public boolean updateInfo(PersonalInfoPO po);
	public PersonalInfoPO getInfo(String id);
	public boolean updateCoins(String id,int coins);//���½������
}
