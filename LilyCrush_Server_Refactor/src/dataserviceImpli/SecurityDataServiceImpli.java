package dataserviceImpli;

import pos.SecurityPO;

public interface SecurityDataServiceImpli {
	public boolean insertRecord(SecurityPO po);
	public boolean deleteRecord(String id);
	public boolean updatePswd(SecurityPO po);
	public String getPswd(String id);
	public boolean ifExist(String id);
}
