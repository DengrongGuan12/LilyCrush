package view.component;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * 可以显示背景图片的Panel 新建对象后，调用setImage方法设置背景图片即可
 * 
 */

public class BackGroundPanel extends AlphaPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9019459739887040408L;
	private Image image;

	public BackGroundPanel() {
		super();
		setOpaque(false);
		setLayout(null);
	}

	public void setImage(Image image) {
		this.image = image;
	}

	protected void paintComponent(Graphics g) {
		if (image != null) {
			int width = getWidth();// 获取组件大小
			int height = getHeight();
			g.drawImage(image, 0, 0, width, height, this);// 绘制图片与组件大小相同
		}
		super.paintComponent(g);// 执行超类方法
	}

}
