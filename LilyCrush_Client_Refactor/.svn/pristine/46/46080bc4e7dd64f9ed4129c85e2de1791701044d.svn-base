package view.component;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ChooseFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BackGroundPanel headPanel;
	private Point pressedPoint;
	private BackGroundPanel mainPanel;
	private ClickButton closeButton;
	private JLabel infoLabel;
	private String info;

	public ChooseFrame(String info) {
		super();
		this.info = info;
		setBounds(0, 0, 400, 220);
		setUndecorated(true);
		getContentPane().setLayout(null);
		initialWindow();
	}

	public void initialWindow() {
		headPanel = new BackGroundPanel();
		headPanel.setBounds(0, 0, 400, 20);
		Image title = new ImageIcon("images/window/gray.jpg").getImage();
		headPanel.setImage(title);
		headPanel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				do_headPanel_mouseDragged(e);
			}
		});
		headPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				do_headPanel_mousePressed(e);
			}
		});
		this.getContentPane().add(headPanel);
		
		mainPanel = new BackGroundPanel();
		mainPanel.setBounds(0,20,400,200);
		Image backGround = new ImageIcon("images/window/wood.jpg").getImage();
		mainPanel.setImage(backGround);
		this.getContentPane().add(mainPanel);
		
		ImageIcon imgicon = new ImageIcon("images/window/x.png");
		closeButton = new ClickButton("images/window/x.png");
		closeButton.setBounds(380, 0, imgicon.getIconWidth(),
				imgicon.getIconHeight());
		closeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		closeButton.setOpaque(false);
		headPanel.add(closeButton);
		
		infoLabel = new JLabel(info);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(10, 20, 360, 70);
		infoLabel.setOpaque(false);
		mainPanel.add(infoLabel);
		
		this.setVisible(true);
	}

	protected void do_headPanel_mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		pressedPoint = e.getPoint();
	}

	protected void do_headPanel_mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根
		Point point = e.getPoint();
		Point locationPoint = getLocation();

		int x = locationPoint.x + point.x - pressedPoint.x;
		int y = locationPoint.y + point.y - pressedPoint.y;

		setLocation(x, y);
	}
}
