package pos.historyPO;
import java.util.Date;


/**
 * һ����Ϸ����ʷ��¼
 *
 */
public class SingleGameHistoryItem{
	private Date date;
	private int eachPoint;
	private int maxCombo;
	
	public SingleGameHistoryItem(Date date , int point,int maxCombo){
		this.date = date;
		this.eachPoint = point;
		this.maxCombo = maxCombo;
	}
	
	public Date getDate() {
		return date;
	}
	public int getPoint() {
		return eachPoint;
	}
	public int getCombo(){
		return maxCombo;
	}
}