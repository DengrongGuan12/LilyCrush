package enums;


/**
 * ��Ʒ����
 *
 */
public enum Prop {
	C(100,"C���͵��ߵ�����" , 1),
	D(200,"D���͵��ߵ�����" , 2),
	E(300,"E���͵��ߵ�����" , 3);
	
	private int price;
	private String intro;
	private int i;
	
	Prop(int price,String intro,int i){
		this.price = price;
		this.intro = intro;
		this.i = i;
	}
	
	public int getPrice() {
	    return price;
	  }
	
	public String getIntro() {
	    return intro;
	  }
	public int getI(){
		return i;
	}
}
