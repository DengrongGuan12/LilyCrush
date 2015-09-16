package pos;

import java.util.Map;

public class EachMultiGameInfo {
	private int eachPoint;
	private int maxCombo;
	private Map<String, String> IDandNickname;
	
	public EachMultiGameInfo(int eachPoint , int maxCombo , Map<String, String> IDandNickname){
		this.eachPoint = eachPoint;
		this.maxCombo = maxCombo;
		this.IDandNickname = IDandNickname;
	}
	
	public int getEachPoint() {
		return eachPoint;
	}
	public int getMaxCombo() {
		return maxCombo;
	}
	public Map<String, String> getIDandNickname() {
		return IDandNickname;
	}
}
