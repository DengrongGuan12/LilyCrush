package view.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Transparency;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 可以将图片设置为按钮的Button类
 * 构造函数传递图片的路径即可
 *
 */

public class ClickButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img;
	MediaTracker mt;
	int w;
	int h;
	
	private ClickButton cb;
	private int targetX;// 按钮的x坐标
	private int targetY;
	private String type;// 动画类型
	private MoveRunnable moveRunnable;

	public ClickButton(String imagePath) {
		try {
			img = new ImageIcon(imagePath).getImage(); // 读取本地图片
			mt = new MediaTracker(this);// 为此按钮添加媒体跟踪器
			mt.addImage(img, 0);// 在跟踪器添加图片，下标为0
			mt.waitForAll(); // 等待加载
			w = img.getWidth(this);// 读取图片长度
			h = img.getHeight(this);// 读取图片宽度

			GraphicsConfiguration gc = new JFrame().getGraphicsConfiguration(); // 本地图形设备
			Image image = gc.createCompatibleImage(w, h,
					Transparency.TRANSLUCENT);// 建立透明画布
			Graphics2D g = (Graphics2D) image.getGraphics(); // 在画布上创建画笔

			Composite alpha = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, .5f); // 指定透明度为半透明90%
			g.setComposite(alpha);
			g.drawImage(img, 1, 1, this); // 注意是,将image画到g画笔所在的画布上
			g.setColor(Color.black);// 设置颜色为黑色
//			g.drawString(name, 25, 20);// 写字
			g.dispose(); // 释放内存

			Composite alpha2 = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, .7f);
			Image image1 = gc.createCompatibleImage(w, h,
					Transparency.TRANSLUCENT);
			g = (Graphics2D) image1.getGraphics();
			g.setComposite(alpha2);
			g.drawImage(img, 0, 0, this); // 改变图像起始位置,产生动态效果
//			g.setColor(Color.black);
//			g.drawString(name, 25, 20);
			g.dispose();
			
			Composite alpha3 = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, .9f);
			Image image2 = gc.createCompatibleImage(w, h,
					Transparency.TRANSLUCENT);
			g = (Graphics2D) image2.getGraphics();
			g.setComposite(alpha3);
			g.drawImage(img, 0, 0, this); // 改变图像起始位置,产生动态效果
//			g.setColor(Color.black);
//			g.drawString(name, 25, 20);
			g.dispose();

			this.setIgnoreRepaint(true);
//			this.setFocusable(false);// 设置没有焦点
			this.setBorder(null);// 设置不画按钮边框
			this.setContentAreaFilled(false);// 设置不画按钮背景
			this.setIcon(new ImageIcon(image1)); // 把刚才生成的半透明image变成ImageIcon,贴到按钮上去
			this.setRolloverIcon(new ImageIcon(image2));
			this.setPressedIcon(new ImageIcon(image));// 按下去的图标
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void move(int x,int y,String type){
		this.targetX = x;
		this.targetY = y;
		this.type = type;
		
		if(moveRunnable==null){
			moveRunnable = new MoveRunnable();
		}
		Thread thread = new Thread(moveRunnable);
		thread.start();
	}

	class MoveRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			move();
		}
		
		private synchronized void move(){
			if(type.equals("in")){
				cb.setLocation(1000, targetY);
				while(cb.getX()!=targetX){
					cb.setLocation(cb.getX()-1, targetY);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread.yield();
				}
			}else if(type.equals("out")){
				while(cb.getX()!=targetX-20){
					cb.setLocation(cb.getX()-1, targetY);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread.yield();
				}
				
				while(cb.getX()!=1000){
					cb.setLocation(cb.getX()+1, targetY);
					try {
						Thread.sleep(1);
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