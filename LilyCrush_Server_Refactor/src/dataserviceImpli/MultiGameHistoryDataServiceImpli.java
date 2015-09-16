package dataserviceImpli;

import java.util.ArrayList;

import pos.historyPO.MultiGameHistoryItem;

public interface MultiGameHistoryDataServiceImpli {
	public boolean insertRecord(MultiGameHistoryItem po);
	public ArrayList<MultiGameHistoryItem> getInfo(String id);
}
