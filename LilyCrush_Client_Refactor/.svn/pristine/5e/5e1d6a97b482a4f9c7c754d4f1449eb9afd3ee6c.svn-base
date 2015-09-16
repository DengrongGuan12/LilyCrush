package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import businesslogicService.FriendBLService;
import businesslogicService.GameBLService;
import common.friendInfo.Friend;
import common.rankingInfo.SingleGameHistoryItemInfo;
import view.component.BackGroundPanel;
import view.component.ClickButton;
import view.component.HintFrame;
import view.component.TTextField;

public class FriendsPanel extends BackGroundPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FriendBLService friendBLService;

	private FriendsPanel friendsPanel;

	private MainFrame mainFrame;

	private ClickButton backButton;
	private BackGroundPanel friends;

	private JPanel leftPanel;
	private BackGroundPanel rightPanel;
	private JPanel friendsListPanel;
	private JScrollPane friendsListJsp;
	private JScrollPane friendInfoJsp;
	private ClickButton addFriendButton;
	private JLabel friendLabels[][];
	private ClickButton deleteFriendButton;
	private JTextField friendIdTextField;
	private ClickButton addButton;
	private ClickButton cancelButton;

	private HintFrame hintFrame;

	private ImageHelper imageHelper;

	private int numOfFriends;

	private GridBagLayout gridBagLayout;

	private ArrayList<Friend> friendList;

	private String[] friendsIdList;

	private Friend friendInfo;
	private JLabel friendAvatarLabel;
	private JLabel friendNickNameLabel;
	private JLabel friendCoinsLabel;
	private JLabel friendMaxComboLabel;
	private JLabel friendHighestScoreLabel;
	private JLabel avatarFrameLabel;

	public FriendsPanel() {
		initialUI();
		friendsPanel = this;
	}

	public void initialUI() {

		Font labelFont = new Font("微软雅黑", Font.PLAIN, 18);
		ChangePanelListener changePanelListener = new ChangePanelListener();
		ButtonListener buttonListener = new ButtonListener();

		friends = this;
		ImageIcon loginImageIcon = new ImageIcon("images/friends/好友界面.png");
		friends.setBounds(0, 0, 1000, 700);
		friends.setImage(loginImageIcon.getImage());

		hintFrame = new HintFrame();
		hintFrame.setVisible(false);

		friendIdTextField = new TTextField();
		friendIdTextField.setBounds(205, 100, 140, 20);
		hintFrame.hintBackGroundPanel.add(friendIdTextField);

		ImageIcon addIcon = new ImageIcon("images/friends/添加.png");
		addButton = new ClickButton("images/friends/添加.png");
		addButton.setBounds(80, 150, addIcon.getIconWidth(),
				addIcon.getIconHeight());
		addButton.addActionListener(buttonListener);
		hintFrame.hintBackGroundPanel.add(addButton);

		ImageIcon cancelIcon = new ImageIcon("images/friends/提示框取消.png");
		cancelButton = new ClickButton("images/friends/提示框取消.png");
		cancelButton.setBounds(230, 150, cancelIcon.getIconWidth(),
				cancelIcon.getIconHeight());
		cancelButton.addActionListener(buttonListener);
		hintFrame.hintBackGroundPanel.add(cancelButton);

		ImageIcon backButtonIcon = new ImageIcon("images/返回.png");
		backButton = new ClickButton("images/返回.png");
		backButton.setBounds(30, 30, backButtonIcon.getIconWidth(),
				backButtonIcon.getIconHeight());
		backButton.addActionListener(changePanelListener);
		friends.add(backButton);

		leftPanel = new JPanel();
		leftPanel.setBounds(175, 140, 240, 430);
		leftPanel.setOpaque(false);
		friends.add(leftPanel);
		leftPanel.setLayout(null);

		friendsListJsp = new JScrollPane();
		friendsListJsp.setBounds(0, 0, 240, 360);
		friendsListJsp.getViewport().setOpaque(false);
		friendsListJsp.setOpaque(false);
		friendsListJsp.setBorder(null);
		friendsListJsp
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		friendsListJsp
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		leftPanel.add(friendsListJsp);

		ImageIcon addFriendIcon = new ImageIcon("images/friends/加号.png");
		addFriendButton = new ClickButton("images/friends/加号.png");
		addFriendButton.setBounds(40, 375, addFriendIcon.getIconWidth(),
				addFriendIcon.getIconHeight());
		addFriendButton.addActionListener(buttonListener);
		leftPanel.add(addFriendButton);

		ImageIcon deleteFriendIcon = new ImageIcon("images/friends/垃圾桶.png");
		deleteFriendButton = new ClickButton("images/friends/垃圾桶.png");
		deleteFriendButton.setBounds(160, 375, deleteFriendIcon.getIconWidth(),
				deleteFriendIcon.getIconHeight());
		deleteFriendButton.addActionListener(buttonListener);
		leftPanel.add(deleteFriendButton);

		ImageIcon rightIcon = new ImageIcon("images/friends/好友信息.png");
		rightPanel = new BackGroundPanel();
		rightPanel.setBounds(585, 140, 240, 420);
		rightPanel.setImage(rightIcon.getImage());
		friends.add(rightPanel);

		ImageIcon avatarIcon = new ImageIcon("images/game/头像框.png");
		avatarFrameLabel = new JLabel(avatarIcon);
		avatarFrameLabel.setBounds(0, 0, avatarIcon.getIconWidth(),
				avatarIcon.getIconHeight());
		rightPanel.add(avatarFrameLabel);

		friendAvatarLabel = new JLabel();
		friendAvatarLabel.setOpaque(false);
		friendAvatarLabel.setBounds(20, 20, 100, 100);
		rightPanel.add(friendAvatarLabel);

		friendNickNameLabel = new JLabel();
		friendNickNameLabel.setBounds(80, 147, 100, 30);
		friendNickNameLabel.setOpaque(false);
		friendNickNameLabel.setForeground(Color.white);
		friendNickNameLabel.setFont(labelFont);
		rightPanel.add(friendNickNameLabel);

		friendCoinsLabel = new JLabel();
		friendCoinsLabel.setBounds(80, 190, 100, 30);
		friendCoinsLabel.setOpaque(false);
		friendCoinsLabel.setForeground(Color.white);
		friendCoinsLabel.setFont(labelFont);
		rightPanel.add(friendCoinsLabel);

		friendMaxComboLabel = new JLabel();
		friendMaxComboLabel.setBounds(15, 275, 100, 30);
		friendMaxComboLabel.setOpaque(false);
		friendMaxComboLabel.setForeground(Color.white);
		friendMaxComboLabel.setFont(labelFont);
		rightPanel.add(friendMaxComboLabel);

		friendHighestScoreLabel = new JLabel();
		friendHighestScoreLabel.setBounds(15, 355, 100, 30);
		friendHighestScoreLabel.setOpaque(false);
		friendHighestScoreLabel.setForeground(Color.white);
		friendHighestScoreLabel.setFont(labelFont);
		rightPanel.add(friendHighestScoreLabel);

		friendInfoJsp = new JScrollPane();
		friendInfoJsp.setBounds(0, 0, 240, 420);
		friendInfoJsp.getViewport().setOpaque(false);
		friendInfoJsp.setOpaque(false);
		friendInfoJsp.setBorder(null);
		friendInfoJsp
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		friendInfoJsp
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		rightPanel.add(friendInfoJsp);

		this.setVisible(false);
	}

	private String getFriendIdSelected() {
		String id = "";
		for (int i = 0; i < numOfFriends; i++) {
			if (friendLabels[i][3].isVisible() == true) {
				id = friendsIdList[i];
			}
		}
		return id;
	}

	public void addFriendMessageSend() {
		JOptionPane.showMessageDialog(null, "好友邀请已发送!", "系统消息",
				JOptionPane.INFORMATION_MESSAGE);
		hintFrame.setVisible(false);
	}

	public void deleteFriendSuc() {
		JOptionPane.showMessageDialog(null, "删除好友成功!", "系统消息",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void deleteFriendFail() {
		JOptionPane.showMessageDialog(null, "删除好友失败!", "系统消息",
				JOptionPane.ERROR_MESSAGE);
	}

	public void friendToAddedOffline() {
		JOptionPane.showMessageDialog(null, "玩家不在线，添加好友失败!", "系统消息",
				JOptionPane.ERROR_MESSAGE);
	}

	public void askIfBecomeFriend(String id, String nickname) {
		int temp = JOptionPane.showConfirmDialog(null, "玩家 " + nickname
				+ " 请求加你为好友", "系统消息", JOptionPane.YES_NO_OPTION);
		if (temp == JOptionPane.YES_OPTION) {
			friendBLService.agreeToBecomeFriends(id);
		} else {
			friendBLService.refuseToBecomeFriend(id);
		}
	}

	public void agreeToBecomeFriend(String id, String nickName) {
		JOptionPane.showMessageDialog(null, "玩家 " + nickName
				+ " 已经通过了你的好友请求，你们已经成为好友了！", "系统消息",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void addFriendFail() {
		JOptionPane.showMessageDialog(null, "添加好友失败，重复添加!", "系统消息",
				JOptionPane.ERROR_MESSAGE);
	}

	public void refuseToBecomeFriend(String nickname, String name) {
		JOptionPane.showMessageDialog(null, "玩家 " + nickname + " 拒绝了你的好友请求",
				"系统消息", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showFriendsList(ArrayList<Friend> friends) {
		// friendsListJsp.remove(friendsListPanel);
		double percentOfSize = 0.1;// 每行占据整个面板的比例值
		ClickedListener clickedListener = new ClickedListener();
		this.friendList = friends;

		gridBagLayout = new GridBagLayout();
		imageHelper = new ImageHelper();
		numOfFriends = friends.size();
		friendLabels = new JLabel[numOfFriends][4];
		friendsIdList = new String[numOfFriends];
		friendsListPanel = new JPanel();
		friendsListPanel.setOpaque(false);

		int i = 0;
		for (Friend friend : friends) {
			if (i == numOfFriends - 1) {// 若为最后一行，则设置比例值最大
				percentOfSize = 10;
			}

			friendsIdList[i] = friend.getName();// 获取好友列表中所有好友的id

			ImageIcon labelIcon = imageHelper.getAvatar(friend.getAvatar(), 20,
					20);
			friendLabels[i][0] = new JLabel(labelIcon);
			friendLabels[i][0].addMouseListener(clickedListener);

			friendLabels[i][1] = new JLabel(friend.getNickname());
			friendLabels[i][1].setForeground(Color.white);
			friendLabels[i][1].addMouseListener(clickedListener);

			boolean isOnline = friend.isInline();
			if (isOnline == true) {
				friendLabels[i][2] = new JLabel("online");
			} else {
				friendLabels[i][2] = new JLabel("offline");
			}
			friendLabels[i][2].setForeground(Color.white);
			friendLabels[i][2].addMouseListener(clickedListener);

			friendLabels[i][3] = new JLabel(new ImageIcon("images/success.png"));
			friendLabels[i][3].setVisible(false);

			friendsListPanel.add(friendLabels[i][0]);
			friendsListPanel.add(friendLabels[i][1]);
			friendsListPanel.add(friendLabels[i][2]);
			friendsListPanel.add(friendLabels[i][3]);

			gridBagLayout.setConstraints(friendLabels[i][0],
					new GridBagConstraints(0, i, 1, 1, 1, percentOfSize,
							GridBagConstraints.NORTH, 0, new Insets(10, 10, 0,
									10), 0, 0));
			gridBagLayout.setConstraints(friendLabels[i][1],
					new GridBagConstraints(1, i, 1, 1, 1, percentOfSize,
							GridBagConstraints.NORTH, 0, new Insets(10, 10, 0,
									10), 0, 0));
			gridBagLayout.setConstraints(friendLabels[i][2],
					new GridBagConstraints(2, i, 1, 1, 1, percentOfSize,
							GridBagConstraints.NORTH, 0, new Insets(10, 10, 0,
									10), 0, 0));
			gridBagLayout.setConstraints(friendLabels[i][3],
					new GridBagConstraints(3, i, 1, 1, 0.1, percentOfSize,
							GridBagConstraints.NORTH, 0,
							new Insets(10, 0, 0, 0), 0, 0));

			i++;
		}
		friendsListJsp.setViewportView(friendsListPanel);
		friendsListPanel.setLayout(gridBagLayout);
	}

	private void cleanRightPanel() {
		rightPanel.fadeOut();
		friendNickNameLabel.setText("");
		friendCoinsLabel.setText("");
		friendMaxComboLabel.setText("");
		friendHighestScoreLabel.setText("");
	}

	private void setFriendInfo() {
		ImageIcon friendAvatarIcon = imageHelper.getAvatar(
				friendInfo.getAvatar(), 100, 100);
		friendAvatarLabel.setIcon(friendAvatarIcon);

		friendNickNameLabel.setText(friendInfo.getNickname());

		friendCoinsLabel.setText(Integer.toString(friendInfo.getCoins()));

		try {
			SingleGameHistoryItemInfo tempItem = friendInfo
					.getSingleGameHistoryItems().get(0);
			friendMaxComboLabel.setText(Integer.toString(tempItem.getCombo()));

			friendHighestScoreLabel.setText(Integer.toString(tempItem
					.getPoint()));
		} catch (Exception e) {

		}

		rightPanel.fadeIn();
	}

	class ClickedListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (rightPanel.getAlpha() != 0.0f) {
				cleanRightPanel();
			}
			for (int i = 0; i < numOfFriends; i++) {
				friendLabels[i][3].setVisible(false);
				if (e.getSource() == friendLabels[i][0]
						|| e.getSource() == friendLabels[i][1]
						|| e.getSource() == friendLabels[i][2]) {
					friendLabels[i][3].setVisible(true);
					friendInfo = friendBLService
							.getFriendInfo(getFriendIdSelected());
					setFriendInfo();
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == addFriendButton) {
				hintFrame.setVisible(true);
			} else if (e.getSource() == addButton) {
				if (friendIdTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "玩家用户名不能为空!", "系统消息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (friendBLService.hasFriend(friendIdTextField.getText()) == false) {
						friendBLService.addFriend(friendIdTextField.getText());
					} else {
						JOptionPane.showMessageDialog(null, "该玩家已经是你的好友了!",
								"系统消息", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == cancelButton) {
				hintFrame.setVisible(false);
				friendIdTextField.setText("");
			} else if (e.getSource() == deleteFriendButton) {
				String id = getFriendIdSelected();
				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "没有选中好友，无法删除，请选择一个好友!",
							"系统消息", JOptionPane.ERROR_MESSAGE);
				} else {
					friendBLService.deleteFriend(id);
				}
			}
		}

	}

	class ChangePanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			friendsPanel.fadeOut();
			if (e.getSource() == backButton) {
				mainFrame.changePanel("OptionPanel");
			}
		}

	}

	// 设置MainFrame的引用，用于发送面板切换的信息
	public void setMainFrame(MainFrame mainFrame, FriendBLService friendBLService) {
		this.mainFrame = mainFrame;
		this.friendBLService = friendBLService;
	}
}
