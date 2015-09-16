package view.component;

/*
 * Donttai.java
 *
 * Created on __DATE__, __TIME__
 */

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.ImageHelper;

import enums.ImageType;
import enums.VisualEffects;

/**
 * 集成JLabel类，用于显示滑块，运行crush可实现消除的效果
 * 
 * @author shen
 */
public class CrushLabel extends JLabel {
	private int count = 0;
	private int length = 0;

	// 将图片先行放入内存以加快速度
	BufferedImage[] normalList;
	BufferedImage[] crossList;
	BufferedImage[] squareList;

	BufferedImage[] tempList;

	private CrushLabel crushLabel;
	
	private CrushRunnable crushRunnable;
	private MoveToLocation moveToLocation;
	
	private int locationX;
	private int locationY;
	
	private Thread moveThread;
	private Thread crushThread;

	/** Creates new form Donttai */
	public CrushLabel(ImageType type) {
		setIcon(new ImageIcon(ImageHelper.getBaseImg(type)));
		normalList = ImageHelper.getImgList(type, VisualEffects.normal);
		crossList = ImageHelper.getImgList(type, VisualEffects.crossBomb);
		squareList = ImageHelper.getImgList(type, VisualEffects.squareBomb);
		crushRunnable = new CrushRunnable();
		moveToLocation = new MoveToLocation();
	}

	public synchronized void move(int x,int y,CrushLabel crushLabel){
		this.locationX = x;
		this.locationY = y;
		this.crushLabel = crushLabel;
		moveThread = new Thread(moveToLocation);
		moveThread.start();
	}
	
	// 根据不同的消除效果选择不同的消除动画
	public synchronized void crush(VisualEffects effect) {
		switch (effect) {
		case normal:
			tempList = normalList;
			break;
		case squareBomb:
			tempList = squareList;
			break;
		case crossBomb:
			tempList = crossList;
			break;
		default:
			tempList = normalList;
			break;
		}

		if (tempList != null) {
			length = tempList.length;
			crushThread = new Thread(crushRunnable);
			crushThread.start();
		}
	}

	class CrushRunnable implements Runnable {

		@Override
		public void run() {
			while (true) {
				setIcon(new ImageIcon(tempList[count]));

				if (count < length - 1)
					count++;
				else
					break;

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

	}
	
	
	/**
	 * 掉落的Runnable
	 * @author Administrator
	 *
	 */
	class MoveToLocation implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			move();
		}
		
		private synchronized void move(){
//			try {
//				Thread.sleep(300);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			if(crushLabel.getX()==locationX){
				while(crushLabel.getY()!=locationY){
					crushLabel.setLocation(locationX, crushLabel.getY()+20);
					if(crushLabel.getY()>=locationY){
						crushLabel.setLocation(locationX, locationY);
					}
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread.yield();
				}
			}else if(crushLabel.getY()==locationY){
				while(crushLabel.getX()!=locationX){
					crushLabel.setLocation(crushLabel.getX()-20, locationY);
					if(crushLabel.getX()<=locationX){
						crushLabel.setLocation(locationX, locationY);
					}
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread.yield();
				}
			}
		}
		
	}

}