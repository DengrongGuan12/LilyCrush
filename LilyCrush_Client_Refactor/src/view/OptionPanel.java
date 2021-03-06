package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import businesslogicService.GameBLService;

import view.component.BackGroundPanel;
import view.component.ClickButton;

public class OptionPanel extends BackGroundPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainFrame mainFrame;

	private GameBLService gameBLService;
	
	private BackGroundPanel back;

	private ClickButton singleGameButton;
	private ClickButton multiGameButton;
	private ClickButton friendsButton;
	private ClickButton rankButton;
	private ClickButton settingsButton;
	private ClickButton exitButton;

	private OptionPanel optionPanel;

	public OptionPanel() {
		initialUI();
		optionPanel = this;
	}

	private void initialUI() {
		back = this;
		ImageIcon backGround = new ImageIcon("images/OptionPanel/lilycrush.png");
		back.setBounds(0, 0, 1000, 700);
		back.setImage(backGround.getImage());

		ChangePanelListener changePanelListener = new ChangePanelListener();

		ImageIcon singleGameIcon = new ImageIcon("images/OptionPanel/单人游戏.png");
		singleGameButton = new ClickButton("images/OptionPanel/单人游戏.png");
		singleGameButton.setBounds(650, 50, singleGameIcon.getIconWidth(),
				singleGameIcon.getIconHeight());
		singleGameButton.addActionListener(changePanelListener);
		back.add(singleGameButton);

		ImageIcon multiGameIcon = new ImageIcon("images/OptionPanel/多人游戏.png");
		multiGameButton = new ClickButton("images/OptionPanel/多人游戏.png");
		multiGameButton.setBounds(650, 170, multiGameIcon.getIconWidth(),
				multiGameIcon.getIconHeight());
		multiGameButton.addActionListener(changePanelListener);
		back.add(multiGameButton);

		ImageIcon friendsIcon = new ImageIcon("images/OptionPanel/我的好友.png");
		friendsButton = new ClickButton("images/OptionPanel/我的好友.png");
		friendsButton.setBounds(40, 450, friendsIcon.getIconWidth(),
				friendsIcon.getIconHeight());
		friendsButton.addActionListener(changePanelListener);
		back.add(friendsButton);

		ImageIcon rankIcon = new ImageIcon("images/OptionPanel/排行榜.png");
		rankButton = new ClickButton("images/OptionPanel/排行榜.png");
		rankButton.setBounds(40, 570, rankIcon.getIconWidth(),
				rankIcon.getIconHeight());
		rankButton.addActionListener(changePanelListener);
		back.add(rankButton);

		ImageIcon settingsIcon = new ImageIcon("images/OptionPanel/设置.png");
		settingsButton = new ClickButton("images/OptionPanel/设置.png");
		settingsButton.setBounds(550, 600, settingsIcon.getIconWidth(),
				settingsIcon.getIconHeight());
		settingsButton.addActionListener(changePanelListener);
		back.add(settingsButton);

		ImageIcon exitIcon = new ImageIcon("images/OptionPanel/退出.png");
		exitButton = new ClickButton("images/OptionPanel/退出.png");
		exitButton.setBounds(750, 600, exitIcon.getIconWidth(),
				exitIcon.getIconHeight());
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
		back.add(exitButton);

		this.setVisible(false);
	}

	class ChangePanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			optionPanel.fadeOut();
			if (e.getSource() == singleGameButton) {
				mainFrame.changePanel("SelectPropPanel");
			} else if (e.getSource() == multiGameButton) {
				mainFrame.changePanel("RoomPanel");
			} else if (e.getSource() == friendsButton) {
				mainFrame.changePanel("FriendsPanel");
			} else if (e.getSource() == rankButton) {
				mainFrame.changePanel("RankingPanel");
			}else if(e.getSource()==settingsButton){
				mainFrame.changePanel("SettingsPanel");
			}
		}

	}

	// 设置MainFrame的引用，用于发送面板切换的信息
	public void setMainFrame(MainFrame mainFrame,GameBLService gameBLService) {
		this.mainFrame = mainFrame;
		this.gameBLService = gameBLService;
	}
}
