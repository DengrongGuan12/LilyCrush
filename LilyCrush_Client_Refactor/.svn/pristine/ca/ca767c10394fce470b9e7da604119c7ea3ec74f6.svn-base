package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MultiGamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiGamePanel multiGamePanel;
	private MainFrame mainFrame;

	private JButton backButton;

	public MultiGamePanel() {
		initialUI();
		multiGamePanel = this;
	}

	private void initialUI() {
		this.setBounds(0, 0, 1000, 700);
		this.setOpaque(false);
		this.setLayout(null);
		
		ChangePanelListener changePanelListener = new ChangePanelListener();
		
		backButton = new JButton("返回");
		backButton.setBounds(30, 30,100,20);
		backButton.addActionListener(changePanelListener);
		backButton.setOpaque(false);
		this.add(backButton);
		
		this.setVisible(false);
	}

	class ChangePanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			multiGamePanel.setVisible(false);
			if (e.getSource() == backButton) {
				mainFrame.changePanel("RoomPanel");
			} 
		}

	}

	// 设置MainFrame的引用，用于发送面板切换的信息
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
