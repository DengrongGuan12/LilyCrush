package view;

import java.awt.*;

public class CrushUI extends Frame implements Runnable {
	Thread animation; // 声明线程
	int frameDelay = 100; // 设置图片显示时间间隔
	Image frames[];
	int numFrames;
	int currentFrame = 0;
	int screenWidth = 400; // 显示窗口宽
	int screenHeight = 400; // 显示窗口长

	public CrushUI() {
		super("Graphic Animation");
		setup();
		resize(screenWidth, screenHeight); // 设置窗口大小
		show();
		animation = new Thread(this); // 创建线程
		animation.start(); // 动画线程启动
	}

	public void setup() {
		Toolkit toolkit = getToolkit();
		frames = new Image[4]; // image 数组，准备存放图片
		frames[0] = toolkit.getImage("tupian1.png");// 装入图片
		frames[1] = toolkit.getImage("tupian2.png");
		frames[2] = toolkit.getImage("tupian3.png");
		frames[3] = toolkit.getImage("tupian4.png");
		numFrames = frames.length;
	}

	public void paint(Graphics g) {
		g.drawImage(frames[currentFrame], 10, 10, this);
	}

	public void run() { // 演示动画
		while (true) {
			repaint();
			try {
				Thread.sleep(frameDelay); // 线程休眠
			} catch (InterruptedException ex) {
			}
			currentFrame++; // 数组下标自增
			currentFrame %= numFrames; // 防止图片数组下标过界
		}
	}
}
