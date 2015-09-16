package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import view.component.BackGroundPanel;
import view.component.ClickButton;

import javax.swing.JTabbedPane;

import businesslogicService.FriendBLService;
import businesslogicService.GameBLService;
import businesslogicService.RankingBLService;
import PublicInfo.MyInfo;
import common.rankingInfo.MultiOverallItemInfo;
import common.rankingInfo.SingleGameHistoryItemInfo;
import common.rankingInfo.SingleGameOverallItemInfo;
import common.settingInfo.PersonalInfo;
import enums.Avatar;

public class RankingPanel extends BackGroundPanel {
	private RankingPanel rankingPanel;

	private MainFrame mainFrame;

	MultiRankComponent multiRankComponent;
	SingleRankComponent singleRankComponent;
	PersonalRankComponent personalRankComponent;
	SingleIntroPanel singleIntro;
	MultiIntroPanel multiIntro;
	SingleIntroPanel personalIntro;

	// GameBLService gameBLService;

	private FriendBLService friendBLService;

	private ClickButton backButton;

	public RankingPanel() {
		initialUI();
		rankingPanel = this;
	}

	public void initialUI() {
		this.setBounds(0, 0, 1000, 700);
		this.setOpaque(false);
		this.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(false);
		this.add(tabbedPane);

		multiRankComponent = new MultiRankComponent();
		JScrollPane pane = new JScrollPane(multiRankComponent);
		pane.setViewportView(multiRankComponent);
		pane.getViewport().setOpaque(false);
		pane.setOpaque(false);
		pane.setBorder(null);
		pane.setBounds(30, 30, 250, 500);

		singleRankComponent = new SingleRankComponent();
		JScrollPane pane2 = new JScrollPane(singleRankComponent);
		pane2.getViewport().setOpaque(false);
		pane2.setOpaque(false);
		pane2.setViewportView(singleRankComponent);
		pane2.setBorder(null);
		pane2.setBounds(30, 30, 250, 500);

		personalRankComponent = new PersonalRankComponent();
		JScrollPane pane3 = new JScrollPane(personalRankComponent);
		pane3.getViewport().setOpaque(false);
		pane3.setOpaque(false);
		pane3.setViewportView(personalRankComponent);
		pane3.setBorder(null);
		pane3.setBounds(30, 30, 250, 500);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setOpaque(false);
		pane.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
		panel1.add(pane);

		multiIntro = new MultiIntroPanel();
		panel1.add(multiIntro);

		tabbedPane.setBounds(100, 100, 800, 800);
		tabbedPane.addTab("多人游戏", panel1);
		tabbedPane.getTabComponentAt(0);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setOpaque(false);
		panel2.add(pane2);

		singleIntro = new SingleIntroPanel();
		panel2.add(singleIntro);
		tabbedPane.addTab("单人游戏", panel2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setOpaque(false);
		panel3.add(pane3);

		personalIntro = new SingleIntroPanel();
		panel3.add(personalIntro);
		tabbedPane.addTab("个人历史", panel3);

		tabbedPane.setSelectedIndex(0);

		ChangePanelListener changePanelListener = new ChangePanelListener();

		ImageIcon backButtonIcon = new ImageIcon("images/返回.png");
		backButton = new ClickButton("images/返回.png");
		backButton.setBounds(30, 30, backButtonIcon.getIconWidth(),
				backButtonIcon.getIconHeight());
		backButton.addActionListener(changePanelListener);
		this.add(backButton);

		ArrayList<SingleGameOverallItemInfo> list = new ArrayList<SingleGameOverallItemInfo>();
		System.out.println("interesting!"); // TODO asas
		list.add(new SingleGameOverallItemInfo(100, "user1 nickname",
				Avatar.hokain1, "user1"));
		list.add(new SingleGameOverallItemInfo(100, "user1 nickname",
				Avatar.hokain1, "user1"));
		list.add(new SingleGameOverallItemInfo(100, "user1 nickname",
				Avatar.hokain1, "user1"));
		list.add(new SingleGameOverallItemInfo(100, "user1 nickname",
				Avatar.hokain1, "user1"));
		list.add(new SingleGameOverallItemInfo(100, "user1 nickname",
				Avatar.hokain1, "user1"));
		showSingleList(list);

		this.setVisible(false);
	}

	class ChangePanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			rankingPanel.fadeOut();
			if (e.getSource() == backButton) {
				mainFrame.changePanel("OptionPanel");
			}
		}
	}

	// 设置MainFrame的引用，用于发送面板切换的信息
	public void setMainFrame(MainFrame mainFrame,
			FriendBLService friendBLService) {
		this.mainFrame = mainFrame;
		this.friendBLService = friendBLService;
	}

	class RankComponent extends JPanel {
		protected int page = 0;

		public RankComponent() {
			setSize(200, 600);
			BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
	};

	class SingleRankComponent extends RankComponent {
		ArrayList<SingleGameOverallItemInfo> rankList;

		public SingleRankComponent() {
			super();
		}

		public void setList(ArrayList<SingleGameOverallItemInfo> rankList) {
			removeAll();
			for (SingleGameOverallItemInfo item : rankList) {
				add(new SingleRankItemPanel(item));
			}
			for (int i = page * 20; i < (page + 1) * 20; i++) {
				if (!(i < rankList.size())) {
					add(new BlankItemPanel());
				}
			}
		}

		public void showPage(int page) {
			removeAll();
			for (int i = page * 20; i < (page + 1) * 20; i++) {
				if (!(i < rankList.size())) {
					add(new BlankItemPanel());
				} else
					add(new SingleRankItemPanel(rankList.get(i)));
			}
		}
	}

	class MultiRankComponent extends RankComponent {
		public MultiRankComponent() {
			super();
		}

		private ArrayList<MultiOverallItemInfo> rankList;

		public void setList(ArrayList<MultiOverallItemInfo> rankList) {
			removeAll();

			for (MultiOverallItemInfo item : rankList) {
				add(new MultiRankItemPanel(item));
			}
			for (int i = 0; i < 20; i++) {
				if (!(i < rankList.size())) {
					add(new BlankItemPanel());
				}
			}
		}

		public void showPage(int page) {
			removeAll();
			for (int i = page * 20; i < (page + 1) * 20; i++) {
				if (!(i < rankList.size())) {
					add(new BlankItemPanel());
				} else
					add(new MultiRankItemPanel(rankList.get(i)));
			}
		}
	}

	class PersonalRankComponent extends RankComponent {
		public PersonalRankComponent() {
			super();
		}

		private ArrayList<SingleGameHistoryItemInfo> rankList;

		public void setList(ArrayList<SingleGameHistoryItemInfo> rankList) {
			removeAll();

			for (SingleGameHistoryItemInfo item : rankList) {
				add(new PersonalRankItemPanel(item));
			}
			for (int i = 0; i < 20; i++) {
				if (!(i < rankList.size())) {
					add(new BlankItemPanel());
				}
			}
			personalIntro.setIntro(MyInfo.getName(), MyInfo.getID(),
					MyInfo.getCoin(), MyInfo.getAvatar(), rankList);
		}

		public void showPage(int page) {
			removeAll();
			for (int i = page * 20; i < (page + 1) * 20; i++) {
				if (!(i < rankList.size())) {
					add(new BlankItemPanel());
				} else
					add(new PersonalRankItemPanel(rankList.get(i)));
			}
		}
	}

	class BlankItemPanel extends JPanel {
		public BlankItemPanel() {
			setSize(200, 30);
		}
	}

	class SingleRankItemPanel extends JPanel {
		private JLabel imgLabel;
		private JLabel nameLabel;
		private JLabel pointLabel;

		public SingleRankItemPanel(SingleGameOverallItemInfo item) {
			final Avatar avatar = item.getAvatar();
			final String nickname = item.getNickname();
			final int point = item.getPoint();

			final String id = item.getId();

			setSize(200, 30);

			imgLabel = new JLabel(ImageHelper.getAvatar(avatar, 30, 30));
			imgLabel.setHorizontalAlignment(SwingConstants.LEFT);

			nameLabel = new JLabel(nickname);
			nameLabel.setForeground(Color.WHITE);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

			pointLabel = new JLabel(point + "");
			pointLabel.setForeground(Color.WHITE);
			pointLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			add(imgLabel);
			add(nameLabel);
			add(pointLabel);

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
					ArrayList<SingleGameHistoryItemInfo> list = friendBLService
							.getFriendHistory(id);
					singleIntro.setIntro(nickname, id, point, avatar, list);
				}
			});
		}
	};

	class PersonalRankItemPanel extends JPanel {
		private JLabel imgLabel;
		private JLabel maxComboLabel;
		private JLabel pointLabel;

		public PersonalRankItemPanel(SingleGameHistoryItemInfo item) {
			Avatar avatar = MyInfo.getAvatar();
			int point = item.getPoint();
			int maxCombo = item.getCombo();

			setSize(200, 30);

			imgLabel = new JLabel(ImageHelper.getAvatar(avatar, 30, 30));
			imgLabel.setHorizontalAlignment(SwingConstants.LEFT);

			maxComboLabel = new JLabel(maxCombo + "");
			maxComboLabel.setForeground(Color.WHITE);
			maxComboLabel.setHorizontalAlignment(SwingConstants.CENTER);

			pointLabel = new JLabel(point + "");
			pointLabel.setForeground(Color.WHITE);
			pointLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			add(imgLabel);
			add(maxComboLabel);
			add(pointLabel);
		}
	};

	class MultiRankItemPanel extends JPanel {
		private JLabel imgLabel;
		private JLabel nameLabel;
		private JLabel pointLabel;

		public MultiRankItemPanel(MultiOverallItemInfo item) {
			final Avatar avatar = item.getFriendAvatar();
			final String nickname = item.getNickname();
			final String id = item.getId();
			final int point = item.getMaxPoint();
			final ArrayList<String> coworkList = item.getCoworkers();

			setSize(200, 30);

			imgLabel = new JLabel(ImageHelper.getAvatar(avatar, 30, 30));
			imgLabel.setHorizontalAlignment(SwingConstants.LEFT);

			nameLabel = new JLabel(nickname);
			nameLabel.setForeground(Color.WHITE);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

			pointLabel = new JLabel(point + "");
			pointLabel.setForeground(Color.WHITE);
			pointLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			add(imgLabel);
			add(nameLabel);
			add(pointLabel);

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
					multiIntro
							.setIntro(nickname, id, point, avatar, coworkList);
				}
			});
		}

	}

	/**
	 * 三个排行榜的列表
	 * 
	 * @param singleList
	 * @param multiList
	 * @param personalList
	 */
	public void showMultiList(ArrayList<MultiOverallItemInfo> list) {
		multiRankComponent.setList(list);
	}

	public void showSingleList(ArrayList<SingleGameOverallItemInfo> list) {
		singleRankComponent.setList(list);
	}

	public void showPersonalList(ArrayList<SingleGameHistoryItemInfo> list) {
		personalRankComponent.setList(list);
	}

	class SingleIntroPanel extends JPanel {
		JLabel AvatarLabel = new JLabel();
		JLabel idLabel = new JLabel();
		JLabel coinLabel = new JLabel();
		JLabel nameLabel = new JLabel();

		public SingleIntroPanel() {
			setBounds(340, 30, 380, 500);
			setLayout(null);
			setOpaque(false);

			AvatarLabel.setBounds(32, 17, 100, 100);
			add(AvatarLabel);

			nameLabel.setBounds(276, 59, 109, 16);
			add(nameLabel);

			JLabel lblHistorymax = new JLabel("HistoryMax:");
			lblHistorymax.setForeground(Color.WHITE);
			lblHistorymax.setEnabled(false);
			lblHistorymax.setBounds(32, 156, 100, 16);
			add(lblHistorymax);

			JLabel lblHistory = new JLabel("History:");
			lblHistory.setForeground(Color.WHITE);
			lblHistory.setEnabled(false);
			lblHistory.setBounds(32, 213, 61, 16);
			add(lblHistory);
		}

		/**
		 * 点击某个item之后，显示这个item的介绍
		 * 
		 * @param name
		 * @param id
		 * @param coin
		 * @param avatar
		 * @param historyList
		 */
		public void setIntro(String name, String id, int coin, Avatar avatar,
				ArrayList<SingleGameHistoryItemInfo> historyList) {
			AvatarLabel.setIcon(ImageHelper.getAvatar(avatar, 100, 100));
			nameLabel.setText(name);
			idLabel.setText(id);
			coinLabel.setText(coin + "");
		}
	}

	/**
	 * @author shen 多人游戏排行榜的详细介绍
	 * 
	 */
	class MultiIntroPanel extends JPanel {
		JLabel AvatarLabel = new JLabel();
		JLabel idLabel = new JLabel();
		JLabel coinLabel = new JLabel();
		JLabel nameLabel = new JLabel();
		JPanel coworkerPanel = new JPanel();

		public MultiIntroPanel() {
			setBounds(340, 30, 380, 500);
			setLayout(null);
			setOpaque(false);

			AvatarLabel.setBounds(32, 17, 100, 100);
			add(AvatarLabel);

			nameLabel.setBounds(276, 59, 109, 16);
			add(nameLabel);

			JLabel lblHistorymax = new JLabel("HistoryMax");
			lblHistorymax.setForeground(Color.WHITE);
			lblHistorymax.setEnabled(false);
			lblHistorymax.setBounds(32, 156, 100, 16);
			add(lblHistorymax);

			JLabel lblHistory = new JLabel("Coworker:");
			lblHistory.setForeground(Color.WHITE);
			lblHistory.setEnabled(false);
			lblHistory.setBounds(32, 213, 80, 16);
			add(lblHistory);

			coworkerPanel.setOpaque(false);

			/**
			 * @author shen 排行榜列表中一个记录的panel
			 */
			class PersonItem extends JPanel {
				private JLabel imgLabel;
				private JLabel maxComboLabel;
				private JLabel pointLabel;

				public PersonItem(SingleGameHistoryItemInfo item) {
					Avatar avatar = MyInfo.getAvatar();
					int point = item.getPoint();
					int maxCombo = item.getCombo();

					setSize(200, 30);

					imgLabel = new JLabel(ImageHelper.getAvatar(avatar, 30, 30));
					imgLabel.setHorizontalAlignment(SwingConstants.LEFT);

					maxComboLabel = new JLabel(maxCombo + "");
					maxComboLabel.setForeground(Color.WHITE);
					maxComboLabel.setHorizontalAlignment(SwingConstants.CENTER);

					pointLabel = new JLabel(point + "");
					pointLabel.setForeground(Color.WHITE);
					pointLabel.setHorizontalAlignment(SwingConstants.RIGHT);

					add(imgLabel);
					add(maxComboLabel);
					add(pointLabel);
				}
			}
			;
		}

		/**
		 * 点击某个item之后，显示这个item的介绍
		 * 
		 * @param name
		 * @param id
		 * @param coin
		 * @param avatar
		 * @param historyList
		 */
		public void setIntro(String name, String id, int coin, Avatar avatar,
				ArrayList<String> cowrokList) {
			AvatarLabel.setIcon(ImageHelper.getAvatar(avatar, 100, 100));
			nameLabel.setText(name);
			idLabel.setText(id);
			coinLabel.setText(coin + "");
		}

		/**
		 * @author shen 显示的协作者的panel
		 */
		class CoworkerPanel extends JPanel {
			public CoworkerPanel(ArrayList<String> coworker,
					GameBLService gameBLService) {
				setOpaque(false);
				setBounds(30, 30, 600, 600);
				ArrayList<PersonalInfo> infoList = new ArrayList<PersonalInfo>();
				// TODO 获得个人的历史记录

				setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				for (PersonalInfo info : infoList) {
					add(new WorkerAvatarLabel(info));
				}
				for (int i = 0; i < 3; i++) {
					if (i > infoList.size()) {
						JLabel label = new JLabel();
						label.setSize(200, 100);
						label.setOpaque(false);
						add(label);
					}
				}
			}

			/**
			 * @author shen 一个协作者头像包含很多可以查看的信息
			 */
			class WorkerAvatarLabel extends JLabel {
				public WorkerAvatarLabel(PersonalInfo info) {
					setSize(200, 200);
					setHorizontalTextPosition(JLabel.RIGHT);
					setVerticalTextPosition(JLabel.BOTTOM);
					setHorizontalTextPosition(JLabel.CENTER);
					setIcon(ImageHelper.getAvatar(info.getAvatar(), 50, 50));
					setText(info.getNickname());
					final PersonalInfo info2 = info;
					addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent arg0) {

						}

						@Override
						public void mousePressed(MouseEvent arg0) {
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
						}

						@Override
						public void mouseClicked(MouseEvent arg0) {
							JLabel label = new JLabel();
							label.setText(info2.getBriefIntroduction());
							JFrame frame = new JFrame();
							frame.setLocale(null);
							frame.setSize(300, 500);
							frame.add(label);
							frame.setVisible(true);
						}
					});
				}
			}
		}
	}

}
