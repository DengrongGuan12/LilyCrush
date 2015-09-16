package common.gameInfo;

import common.Info;

import enums.Direction;
import enums.Message;

/*
 * 封装交换的坐标
 */
public class SwapInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Position p1;
	private Position p2;
	private Direction direction;
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Position getP1() {
		return p1;
	}
	public void setP1(Position p1) {
		this.p1 = p1;
	}
	public Position getP2() {
		return p2;
	}
	public void setP2(Position p2) {
		this.p2 = p2;
	}
	public SwapInfo(Position p1,int x2,int y2,Direction direction){
		super(Message.swapPositions);
		this.setP1(p1);
		p2=new Position(x2, y2);
		this.setDirection(direction);
	}

}
