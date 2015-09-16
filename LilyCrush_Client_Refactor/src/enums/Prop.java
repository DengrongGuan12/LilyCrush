package enums;


/**
 * 商品的类
 *
 */
public enum Prop {
	C(100,"C类型道具的作用" , 1),
	D(200,"D类型道具的作用" , 2),
	E(300,"E类型道具的作用" , 3);
	
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
