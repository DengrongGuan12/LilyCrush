package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import view.component.BackGroundPanel;
import view.component.ClickButton;
import viewService.RoomInterface;
import common.friendInfo.Friend;
import common.settingInfo.PersonalInfo;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import enums.Avatar;
import businesslogicService.GameBLService;
import businesslogicService.RoomBLService;
import PublicInfo.MyInfo;


public class RoomPanel extends BackGroundPanel implements RoomInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private RoomBLService roomBLService;

	private RoomPanel roomPanel;
	private JButton btnReady;

	private final String READY_IMG = "ready";
	private final String STANDBY_IMG = "standby";
	private final String START_IMG = "start";

	private ClickButton backButton;
	private JButton inviteBtn;
	
	private ArrayList<LabelPackage> playerList = new ArrayList<LabelPackage>(); // 除了房主的玩家列表
	LabelPackage hostLabelPkg; // 房主的标签集合

	private final String notReady = "未准备";
	private final String ready = "就绪";
	
	LabelPackage playerLabelPkg1;

	LabelPackage playerLabelPkg2;

	LabelPackage playerLabelPkg3;
	
	ListPanel listPanel;
	JScrollPane pane;

	public RoomPanel() {
		initialUI();
		
		loopRepaint();
	}
	
	public void setMainFrame(MainFrame mainFrame,RoomBLService roomBLService){
		this.mainFrame = mainFrame;
		this.roomBLService = roomBLService;
	}

	public void initialUI() {
//		removeAll();
		
		this.setBounds(0, 0, 1000, 700);
		this.setOpaque(false);
		this.setLayout(null);

		ImageIcon backButtonIcon = new ImageIcon("images/返回.png");
		backButton = new ClickButton("images/返回.png");
		backButton.setBounds(30, 30, backButtonIcon.getIconWidth(),
				backButtonIcon.getIconHeight());
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (hostLabelPkg.getId().equals(MyInfo.getID()) || !roomBLService.isIfReady()) {
					roomPanel.fadeOut();
					initLabels();
					mainFrame.changePanel("OptionPanel");
					roomBLService.exitRoom();
				}
				else {
					JOptionPane.showMessageDialog(null,
						       "请先取消准备再退出房间", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		this.add(backButton);
		
		btnReady = new JButton();
		btnReady.setBounds(657, 420, 229, 89);
		btnReady.setOpaque(false);
		
		 	pane = new JScrollPane();
			pane.setBorder(null);
			pane.setOpaque(true);
		    pane.setBounds(34, 226, 200, 600);
		    pane.setVisible(false);
		    this.add(pane);
		
		inviteBtn = new JButton("邀请好友");
		inviteBtn.setBounds(234, 626, 88, 30);
		inviteBtn.addActionListener(new InviteListener(this , pane , listPanel));
		this.add(inviteBtn);

		hostLabelPkg = new LabelPackage(true, 48, 86);
		
		 playerLabelPkg1 = new LabelPackage(false, 290, 130);

		 playerLabelPkg2 = new LabelPackage(false, 490, 130);

		 playerLabelPkg3 = new LabelPackage(false, 710, 130);

		playerList.add(playerLabelPkg1);
		playerList.add(playerLabelPkg2);
		playerList.add(playerLabelPkg3);

		this.setVisible(false);
		
		roomPanel = this;
	}

	//玩家的准备按钮监听
	class ReadyListener implements ActionListener {
		private JButton btn;
		LabelPackage pkg;
		private int i = 0;

		private ImageIcon readyIcon = ImageHelper.getGifIcon(READY_IMG);
		private ImageIcon standbyIcon = ImageHelper.getGifIcon(STANDBY_IMG);

		private ReadyListener(JButton btn, LabelPackage pkg) {
			this.btn = btn;
			this.pkg = pkg;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			i++;
			i = i % 2;
			if (i == 1) {
				btn.setIcon(standbyIcon);
				pkg.setReady();
				roomBLService.readyForCooprtGame();
			} else {
				btn.setIcon(readyIcon);
				pkg.cancleReady();
				roomBLService.notReadyForCooprtGame();
			}
		}
	}

	@Override
	public void askCreateARoom() {
		initLabels();
		int result = JOptionPane.showConfirmDialog(null, "没有合适的房间可供加入，是否创建房间？");
		if (result==0){
			this.roomBLService.initCooprtGame();
		}
		else if(result == 1){			//否时，继续查找房间
			this.roomBLService.cooperationGame();
		}
		else if(result == 2){			//取消，回到主界面
			roomPanel.fadeOut();
			this.mainFrame.changePanel("OptionPanel");
		}
	}

	
	@Override
	public void askIfReadyForCooprtGame() {
 
	}

	//房主可以点击“开始”按钮
	@Override
	public void askIfStartCooprtGame() {
		//检查本人是否是房主
		if (MyInfo.getID().equals(hostLabelPkg.getId())) {
			btnReady.setEnabled(true);
		}
	}
	
	private void initLabels(){
		playerLabelPkg1.initInfo();
		playerLabelPkg1.setLabel();
		playerLabelPkg2.initInfo();
		playerLabelPkg2.setLabel();
		playerLabelPkg3.initInfo();
		playerLabelPkg3.setLabel();
		hostLabelPkg.initInfo();
		hostLabelPkg.setLabel();
	}
	

	@Override
	public void showRoom(ArrayList<PersonalInfo> personalInfos,
			PersonalInfo hostInfo) {
		initLabels();
		
		String myID = MyInfo.getID();
		
		hostLabelPkg.enter(hostInfo.getId(), hostInfo.getNickname(),
				hostInfo.getAvatar(), hostInfo.getCoins());
		
		//TODO 神秘的ArrayList和内部类问题？
		if(personalInfos.size()>=1){
			PersonalInfo info = personalInfos.get(0);
			playerLabelPkg1.enter(info.getId(), info.getNickname(),
					info.getAvatar(), info.getCoins());
		}
		if(personalInfos.size()>=2){
			PersonalInfo info = personalInfos.get(1);
			playerLabelPkg2.enter(info.getId(), info.getNickname(),
					info.getAvatar(), info.getCoins());
		}
		if(personalInfos.size()>=3){
			PersonalInfo info = personalInfos.get(2);
			playerLabelPkg3.enter(info.getId(), info.getNickname(),
					info.getAvatar(), info.getCoins());
		}
		
//		for (int i = 0; i < personalInfos.size() && i < 3; i++) {
//			PersonalInfo info = personalInfos.get(i);
//			playerList.get(i).enter(info.getId(), info.getNickname(),
//					info.getAvatar(), info.getCoins());
//			
//			playerLabelPkg2.enter(info.getId(), info.getNickname(),
//					info.getAvatar(), info.getCoins());
//		}

		for( ActionListener al : btnReady.getActionListeners() ) {
			btnReady.removeActionListener( al );
	    }
		
		//本人成为房主的时候
		if (myID.equals(hostInfo.getId())) {
			btnReady.setIcon(ImageHelper.getGifIcon(START_IMG));
			//房主点击开始游戏之后
			btnReady.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					roomBLService.startCooprtGame();
					
				}
			});
			hostLabelPkg.setSelfColor();
			
			btnReady.setEnabled(false);
		} else {
			for (LabelPackage pkg : playerList) {
				if (pkg.getId().equals(myID)) {
					pkg.setSelfColor();
					btnReady.addActionListener(new ReadyListener(btnReady, pkg));
					break;
				}
			}
			btnReady.setIcon(ImageHelper.getGifIcon(READY_IMG));
			btnReady.setEnabled(true);
		}
		add(btnReady);
	}

	@Override
	public void refreshRoom(PersonalInfo host,
			ArrayList<PersonalInfo> personalInfos) {

	}


	class LabelPackage {
		private String id="";
		private String nickname;
		private String ifReady;
		private Avatar avatar;
		private JLabel imgLabel = new JLabel();
		private JLabel readyLabel = new JLabel();
		private JLabel nameLabel = new JLabel();
		private int width;
		private int height;
		Toolkit toolkit = getToolkit();
		private boolean ifHost;
		
		
		/**
		 * @param x
		 *            标签的横坐标位置
		 * @param y
		 *            标签的纵坐标位置
		 */
		public LabelPackage(boolean ifHost, int x, int y) {

			this.ifHost = ifHost;
			
			initInfo();

			imgLabel = new JLabel("");
			readyLabel = new JLabel("");
			nameLabel = new JLabel("");
			
			if (ifHost) {
				width = 150;
				height = 150;
			} else {
				width = 100;
				height = 100;
			}
			imgLabel.setSize(width, height);
			imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

			readyLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
			readyLabel.setForeground(Color.WHITE);
			readyLabel.setSize(width, 20);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

			nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
			nameLabel.setForeground(Color.WHITE);
			nameLabel.setSize(width, 20);
			readyLabel.setHorizontalAlignment(SwingConstants.CENTER);

			setLabel();

			setLocation(x, y);

			add(imgLabel);
			add(readyLabel);
			add(nameLabel);
		}

		public void setSelfColor() {
			imgLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		}

		private void initInfo() {
			nickname = "等待加入";
			id = "";
			ifReady = "";
			avatar = Avatar.defaultImg;
			//
//			if (!(imgLabel==null)) {
				imgLabel.setBorder(null);
//			}
		}

		private void setLabel() {
			
			if (!(avatar == Avatar.defaultImg)) {
				System.out.println( " player "+MyInfo.getID()+": "+"ifHost "+ifHost+" ; Avatar "+avatar );
			} 
			imgLabel.setIcon(ImageHelper.getAvatar(avatar, width, height));
			nameLabel.setText(nickname);
			readyLabel.setText(ifReady);
		}

		private void setLocation(int x, int y) {
			imgLabel.setLocation(x, y);
			nameLabel.setLocation(x, y + height + 10);
			readyLabel.setLocation(x, y + height + 30);
		}

		/**
		 * 
		 * 玩家进入房间
		 * 
		 * @param id
		 * @param nickname
		 * @param avatar
		 * @param coin
		 */
		public void enter(String id,String nickname, Avatar avatar, int coin) {
			this.id = id;
			this.nickname = nickname;
			this.avatar = avatar;
			if (! ifHost) {
				ifReady = notReady;
			}

			setLabel();
		}

		public void exit() {
			initInfo();
		}

		public String getId() {
			return id;
		}

		public void setReady() {
			if (!ifHost) {
				ifReady = ready;
				setLabel();
			}
		}
		
		public boolean getIfReady(){
			if(ifReady.equals(ready))
					return true;
			else 
				return false;
		}

		public void cancleReady() {
			if (!ifHost) {
				ifReady = notReady;
				setLabel();
			}
		}

	}

	private void loopRepaint() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}
			}
		});
		t.start();
	}
	
	/**
	 * 询问是否加入协作游戏
	 */
	public void askIfJoin(){
		int result = JOptionPane.showConfirmDialog(null, "是否加入游戏？");
		if (result==0){
			this.roomBLService.joinCooprtGame();
		}
		else {
			this.roomBLService.nojoinCooprtGame();
			this.mainFrame.changePanel("OptionPanel");
		}
	}

	@Override
	public void friendToInvitedOffLine() {
		JOptionPane.showMessageDialog(null,
			       "该好友尚未在线！", "提示", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void askIfAgreeInvitedToCooperateGame(String name , String nickname ) {
		int result = JOptionPane.showConfirmDialog(null, nickname+"邀请你加入多人游戏，是否同意？");
		if (result == 0) {
			roomBLService.agreeInvitedToCooperateGame(name);
		} else {
			roomBLService.refuseInvitedToCooperateGame(name);
		}
		
	}

	@Override
	public void cooperateGameHasStart() {
		int result = JOptionPane.showConfirmDialog(null, "所在房间已经开始游戏，是否重新查找房间？");
		if (result == 0) {
			this.roomBLService.cooperationGame();
		} else {
			mainFrame.changePanel("OptionPanel");
		}
		
	}

	@Override
	public void cooperateGameEnoughPlayers() {
		int result = JOptionPane.showConfirmDialog(null, "所在房间已经人满了，是否重新查找房间？");
		if (result == 0) {
			this.roomBLService.cooperationGame();
		} else {
			mainFrame.changePanel("OptionPanel");
		}
		
	}

	@Override
	public void refusedInviteFriendToCooperateGame(String name, String nickname) {
		JOptionPane.showMessageDialog(null,
			       nickname+" 拒绝了你的邀请", "提示", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void cannotStartGameHint() {
		JOptionPane.showMessageDialog(null,
			       "有人数变动而不能开始游戏", "提示", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void cannotStartCooperateGame() {
		if (MyInfo.getID().equals(hostLabelPkg.getId())) {
			btnReady.setEnabled(false);
		}
	}

	/**
	 * 好友列表的面板,用于放在JScrollpane中
	 * @author shen
	 *
	 */
	class ListPanel extends BackGroundPanel{
		public ListPanel(ArrayList<Friend> onlineList){
			setSize(200,400);
			setOpaque(false);
		    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		    setImage(ImageHelper.getInviteListBkg());
			 setList(onlineList);
		}
		public void setList(ArrayList<Friend> onlineList){
			removeAll();
			setSize(200,400);
		    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			  for(Friend item:onlineList){
				  add(new FriendItemPanel(item));
			  }
		}
	}
	
	/**
	 * 好友列表里面的一个item，点击有监听
	 *
	 */
	class FriendItemPanel extends JPanel{
		private JLabel nameLabel;
		private JLabel imgLabel;
		final String id;
		FriendItemPanel(Friend item){
			setOpaque(true);
			String nickname = item.getNickname();
			Avatar avatar = item.getAvatar();
			
			id = item.getId();
			
			setSize(200 , 200);
			
			imgLabel = new JLabel(ImageHelper.getAvatar(avatar, 30, 30));
			imgLabel.setHorizontalAlignment(SwingConstants.LEFT);
			
			nameLabel = new JLabel(nickname);
			nameLabel.setForeground(Color.BLACK);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			add(imgLabel);
			add(nameLabel);
			
			addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "确定邀请该好友？");
					if (result == 0) 
						roomBLService.inviteFriend(id);
				}
			});
		}
	};
	
	class InviteListener implements ActionListener{
		JPanel panel;
		JScrollPane pane;
		ListPanel listPanel;
		public InviteListener(JPanel panel , JScrollPane pane , ListPanel listPanel){
			this.panel = panel;
			this.pane = pane;
			this.listPanel= listPanel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
					ArrayList<Friend> friendList = roomBLService.getOnlineFriendList();
					 listPanel = new ListPanel(friendList);
					 pane.setViewportView(listPanel);
					 pane.setVisible(true);
		}
		
	}

	@Override
	public void changeState(String id) {
		if(playerLabelPkg1.getId().equals(id))
			changeStateHelper(playerLabelPkg1);
		else if (playerLabelPkg2.getId().equals(id)) 
			changeStateHelper(playerLabelPkg2);
		else if (playerLabelPkg3.getId().equals(id)) 
			changeStateHelper(playerLabelPkg3);
		
//		for(LabelPackage lbl:playerList){
//			if (lbl.getId().equals(id)) {
//				if(lbl.getIfReady() == true)
//					lbl.cancleReady();
//				else 
//					lbl.setReady();
//				
//				break;
//			}
//		}
	}
	
	private void changeStateHelper(LabelPackage lbl){
		if(lbl.getIfReady() == true)
			lbl.cancleReady();
		else if(lbl.getIfReady() == false)
			lbl.setReady();
	}
	
}
