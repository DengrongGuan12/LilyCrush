package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import businesslogic.FinancialBL;
import businesslogic.FriendBL;
import businesslogic.GameBL;
import businesslogic.LoginBL;
import businesslogic.RankingBL;
import businesslogic.RegisterBL;
import businesslogic.RoomBL;
import businesslogic.SettingBL;
import businesslogic.managers.GameManager;
import businesslogicService.FinancialBLService;
import businesslogicService.FriendBLService;
import businesslogicService.GameBLService;
import businesslogicService.LoginBLService;
import businesslogicService.RankingBLService;
import businesslogicService.RegisterBLService;
import businesslogicService.RoomBLService;
import businesslogicService.SettingBLService;

import view.component.BackGroundPanel;
import view.component.GameFrame;

public class MainFrame {

	private GameBLService gameBLService;
	private FriendBLService friendBLService;
	private FinancialBLService financialBLService;
	private LoginBLService loginBLService;
	private RegisterBLService registerBLService;
	private RankingBLService rankingBLService;
	private RoomBLService roomBLService;
	private SettingBLService settingBLService;
	private GameManager gameManager;

	private BackGroundPanel backGroundPanel;
	private GameFrame mainFrame;

	private SingleGamePanel singleGamePanel;
	private FriendsPanel friendsPanel;
	private MultiGamePanel multiGamePanel;
	private OptionPanel optionPanel;
	private RankingPanel rankingPanel;
	private SelectPropPanel selectPropPanel;
	private RoomPanel roomPanel;
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private SettingsPanel settingsPanel;
	private PurchasePanel purchasePanel;
	private SetIpPanel setIpPanel;

	private void initialFrame() {
		gameBLService = new GameBL();
		friendBLService = new FriendBL();
		financialBLService = new FinancialBL();
		loginBLService = new LoginBL();
		registerBLService = new RegisterBL();
		rankingBLService = new RankingBL();
		roomBLService = new RoomBL();
		settingBLService = new SettingBL();
		gameManager = new GameManager();

		System.out.println("new Game!!!");
		mainFrame = new GameFrame();

		backGroundPanel = new BackGroundPanel();
		backGroundPanel.setBounds(21, 41, 1000, 700);
		backGroundPanel.setAlpha(1.0f);
		Image img = new ImageIcon("images/blackboard3.jpg").getImage();
		backGroundPanel.setImage(img);
		mainFrame.getContentPane().add(backGroundPanel);

		singleGamePanel = new SingleGamePanel();
		singleGamePanel.setMainFrame(this, gameBLService);
		backGroundPanel.add(singleGamePanel);

		friendsPanel = new FriendsPanel();
		friendsPanel.setMainFrame(this, friendBLService);
		backGroundPanel.add(friendsPanel);

		multiGamePanel = new MultiGamePanel();
		multiGamePanel.setMainFrame(this);
		backGroundPanel.add(multiGamePanel);

		optionPanel = new OptionPanel();
		optionPanel.setMainFrame(this, gameBLService);
		backGroundPanel.add(optionPanel);

		rankingPanel = new RankingPanel();
		rankingPanel.setMainFrame(this, friendBLService);
		backGroundPanel.add(rankingPanel);

		selectPropPanel = new SelectPropPanel();
		selectPropPanel.setMainFrame(this);
		backGroundPanel.add(selectPropPanel);

		roomPanel = new RoomPanel();
		roomPanel.setMainFrame(this, roomBLService);
		backGroundPanel.add(roomPanel);

		loginPanel = new LoginPanel();
		loginPanel.setMainFrame(this, loginBLService);
		backGroundPanel.add(loginPanel);

		registerPanel = new RegisterPanel();
		registerPanel.setMainFrame(this, registerBLService);
		backGroundPanel.add(registerPanel);

		settingsPanel = new SettingsPanel();
		settingsPanel.setMainFrame(this, settingBLService);
		backGroundPanel.add(settingsPanel);

		purchasePanel = new PurchasePanel();
		purchasePanel.setMainFrame(this, financialBLService, gameBLService);
		backGroundPanel.add(purchasePanel);

		setIpPanel = new SetIpPanel();
		setIpPanel.setMainFrame(this, gameBLService,gameManager);
		backGroundPanel.add(setIpPanel);

		mainFrame.setVisible(true);

		gameBLService.setMainFrame(this);
	}

	public void changePanel(String panelName) {
		if (panelName.equals("FriendsPanel")) {
			friendsPanel.fadeIn();
			friendBLService.getFriendList();
		} else if (panelName.equals("MultiGamePanel")) {
			multiGamePanel.setVisible(true);
		} else if (panelName.equals("OptionPanel")) {
			optionPanel.fadeIn();
		} else if (panelName.equals("RankingPanel")) {
			rankingPanel.fadeIn();
			rankingBLService.getSingleHistoryData();
			rankingBLService.getMultiHistoryData();
			rankingBLService.getSingleGameRankInfo();
		} else if (panelName.equals("SelectPropPanel")) {
			purchasePanel.fadeIn();
			// selectPropPanel.setVisible(true);
		} else if (panelName.equals("SingleGamePanel")) {
			singleGamePanel.fadeIn();
			// gameBLService.startSingleGame();
		} else if (panelName.equals("RoomPanel")) {
			roomPanel.fadeIn();
			roomBLService.cooperationGame();
		} else if (panelName.equals("LoginPanel")) {
			loginPanel.fadeIn();
		} else if (panelName.equals("RegisterPanel")) {
			registerPanel.fadeIn();
		} else if (panelName.equals("SettingsPanel")) {
			settingsPanel.fadeIn();
			settingBLService.showPersonalInfo();
		} else if (panelName.equals("SetIpPanel")) {
			setIpPanel.fadeIn();
		}
	}

	public static void main(String[] args) {
		MainFrame m = new MainFrame();
		m.initialFrame();
		m.setIpPanel.fadeIn();
		// SingleGameUI_Driver singleGameUI_Driver = new SingleGameUI_Driver();
		// singleGameUI_Driver.mapInitial_Drive(m.getSingleGamePanel());
		// m.gameBLService.setMainFrame(m);
		// ArrayList<Friend> f = new ArrayList<Friend>();
		// for(int i=0;i<20;i++){
		// Friend f1 = new Friend("abc","abc",Avatar.hokain1,10,true);
		// f.add(f1);
		// }
		// m.friendsPanel.showFriendsList(f);
	}

	public SingleGamePanel getSingleGamePanel() {
		return singleGamePanel;
	}

	public FriendsPanel getFriendsPanel() {
		return friendsPanel;
	}

	public MultiGamePanel getMultiGamePanel() {
		return multiGamePanel;
	}

	public OptionPanel getOptionPanel() {
		return optionPanel;
	}

	public RankingPanel getRankingPanel() {
		return rankingPanel;
	}

	public SelectPropPanel getSelectPropPanel() {
		return selectPropPanel;
	}

	public RoomPanel getRoomPanel() {
		return roomPanel;
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public RegisterPanel getRegisterPanel() {
		return registerPanel;
	}

	public SettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	public PurchasePanel getPurchasePanel() {
		return purchasePanel;
	}

	public void disconnected() {
		JOptionPane.showMessageDialog(null, "网络连接不正常，请重试!", "系统消息",
				JOptionPane.ERROR_MESSAGE);
	}

	public void serverDown() {
		JOptionPane.showMessageDialog(null, "服务器崩溃!", "系统消息",
				JOptionPane.ERROR_MESSAGE);
	}

	public void wrongIp() {
		JOptionPane.showMessageDialog(null, "ip地址不正确，连接失败!", "系统消息",
				JOptionPane.ERROR_MESSAGE);
	}
}
