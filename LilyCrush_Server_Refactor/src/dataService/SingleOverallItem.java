package dataService;

/**
 * ���ڲ�ѯ���а�ʱ�Ĳ������
 *
 */
public class SingleOverallItem {
	private String id;
	private int maxPoint;
	private int number;
	
	public SingleOverallItem(String id , int maxPoint,int number){
		this.id = id;
		this.maxPoint = maxPoint;
		this.number = number;
	}
	
	public String getId() {
		return id;
	}
	public int getMaxPoint() {
		return maxPoint;
	}
	public int getNumber() {
		return number;
	}
}
