package view;

import enums.Direction;
import view.component.*;

public class Swap {
	
	CrushLabel swap1;
	CrushLabel swap2;

	Direction direction;
	
	private boolean moveEnabled=false;

	public Swap(CrushLabel swap1, CrushLabel swap2, Direction direction) {
		this.swap1 = swap1;
		this.swap2 = swap2;
		this.direction = direction;
	}
	
	public synchronized void swap() {

			// 为了让交换实现多线程
			if (swap1.getX() > swap2.getX() || swap1.getY() > swap2.getY()) {
				CrushLabel temp = swap1;
				swap1 = swap2;
				swap2 = temp;
			}

			int first;// 第一个图标交换前x或y坐标
			int second;// 第二个图标交换前x或y坐标
			if (direction == Direction.right || direction == Direction.left) {
				first = swap1.getX();
				second = swap2.getX();
				while (swap2.getX() != first && swap1.getX() != second) {
					swap1.setLocation(swap1.getX() + 1, swap1.getY());
					swap2.setLocation(swap2.getX() - 1, swap2.getY());
					try {
						Thread.sleep(4);
					} catch (InterruptedException e) {
					}
					Thread.yield();
				}
			} else {
				first = swap1.getY();
				second = swap2.getY();
				while (swap2.getY() != first && swap1.getY() != second) {
					swap1.setLocation(swap1.getX(), swap1.getY() + 1);
					swap2.setLocation(swap2.getX(), swap2.getY() - 1);
					try {
						Thread.sleep(4);
					} catch (InterruptedException e) {
					}
					Thread.yield();
				}
		}
	}
	
	public synchronized void setMove(){
			moveEnabled = true;
			notify();
	}
	
	public synchronized void move(){
		while(moveEnabled == false){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		moveEnabled = false;
		swap();
	}
	
	}
}
