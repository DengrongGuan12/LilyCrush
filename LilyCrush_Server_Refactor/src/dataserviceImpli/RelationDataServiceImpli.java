package dataserviceImpli;

import java.util.ArrayList;

import pos.FriendInfoPO;
import pos.RelationPO;

public interface RelationDataServiceImpli {
	public boolean insertRecord(RelationPO po);
	public boolean deleteRecord(RelationPO po);
	public ArrayList<FriendInfoPO> getFriendList(String id);
}
