package view.component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class HintFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BackGroundPanel hintBackGroundPanel;

	public HintFrame() {
		super();
		setUndecorated(true);
		getContentPane().setLayout(null);
		com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
		initialFrame();
	}

	private void initialFrame() {
		ImageIcon hintIcon = new ImageIcon("images/friends/ÌáÊ¾¿ò.png");
		hintBackGroundPanel = new BackGroundPanel();
		this.setBounds(310, 230, hintIcon.getIconWidth(),
				hintIcon.getIconHeight());
		hintBackGroundPanel.setBounds(0, 0, hintIcon.getIconWidth(),
				hintIcon.getIconHeight());
		hintBackGroundPanel.setAlpha(1.0f);
		hintBackGroundPanel.setImage(hintIcon.getImage());
		this.getContentPane().add(hintBackGroundPanel);
	}

}
