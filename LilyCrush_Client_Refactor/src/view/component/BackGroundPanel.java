package view.component;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * ������ʾ����ͼƬ��Panel �½�����󣬵���setImage�������ñ���ͼƬ����
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
			int width = getWidth();// ��ȡ�����С
			int height = getHeight();
			g.drawImage(image, 0, 0, width, height, this);// ����ͼƬ�������С��ͬ
		}
		super.paintComponent(g);// ִ�г��෽��
	}

}
