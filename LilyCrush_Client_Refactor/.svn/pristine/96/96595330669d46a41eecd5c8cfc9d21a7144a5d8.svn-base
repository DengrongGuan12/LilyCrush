package view.component;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 * 游戏窗体，重写JFrame
 *
 */

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7513296153518497243L;
	private BackGroundPanel headPanel;
	private Point pressedPoint;
	private ClickButton closeButton;
	private BackGroundPanel borderPanel;

	public GameFrame() {
		super();
		setBounds(0, 0, 1040, 760);
		setUndecorated(true);
		getContentPane().setLayout(null);
		com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
		initialWindow();
	}

	public void initialWindow() {
		borderPanel = new BackGroundPanel();
		borderPanel.setBounds(0,0,1040,760);
		borderPanel.setAlpha(1.0f);
		Image border = new ImageIcon("images/window/阴影.png").getImage();
		borderPanel.setImage(border);
		this.getContentPane().add(borderPanel);
		
		headPanel = new BackGroundPanel();
		headPanel.setBounds(21, 21, 1000, 20);
		headPanel.setAlpha(1.0f);
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
                do_headPanel_mousePressed(e);     
            }
		});
		borderPanel.add(headPanel);

		ImageIcon imgicon = new ImageIcon("images/window/x.png");
		closeButton = new ClickButton("images/window/x.png");
		closeButton.setBounds(980, 0, imgicon.getIconWidth(),
				imgicon.getIconHeight());
		closeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		closeButton.setOpaque(false);
		headPanel.add(closeButton);
	}
	
	protected void do_headPanel_mousePressed(MouseEvent e) {     
        pressedPoint = e.getPoint();     
    }
	
	protected void do_headPanel_mouseDragged(MouseEvent e) {     
        Point point = e.getPoint();     
        Point locationPoint = getLocation();     
                    
        int x = locationPoint.x + point.x - pressedPoint.x;     
        int y = locationPoint.y + point.y - pressedPoint.y;     
                    
        setLocation(x, y);     
    }
}
