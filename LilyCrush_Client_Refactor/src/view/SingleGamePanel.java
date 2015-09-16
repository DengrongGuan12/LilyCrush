package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import businesslogicService.GameBLService;
import common.gameInfo.MapRefreshInfo;
import common.gameInfo.NewMapInfo;
import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;
import common.gameInfo.Pro;
import view.component.*;
import UIMock.CheckIfCrushMock;
import enums.Direction;
import enums.DropMode;
import enums.ImageType;
import enums.ProType;
import enums.TimeCommand;
import enums.VisualEffects;
import view.component.ClickButton;
import viewService.MapService;
import viewService_Driver.SingleGameUI_Driver;
import vos.Location;
import vos.MapInitialVO;
import vos.MapRefreshInfoVO;

public class SingleGamePanel extends BackGroundPanel implements MapService {

	// TODO 消除判断的逻辑mock
	CheckIfCrushMock mock = new CheckIfCrushMock();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static SingleGameUI_Driver singleGameUI_Driver;
	static MapService singleGameUI;

	// private Location location;// 被拖动的图标的坐标
	// private Direction direction;// 被拖动的方向

	private ImageHelper imageHelper;

	private GameBLService gameBLService;

	private ClickButton backButton;

	private DragPicListener listener;

	private MainFrame mainFrame;

	private SingleGamePanel singleGamePanel;

	private JPanel gamePanel;

	private ImageType mapInfo[][];
	private CrushLabel map[][];
	private static int i = 0;

	private boolean lock;// 地图锁

	// 用于控制时间变化的变量
	private long count = 60 * 1000;// 60秒的游戏时间
	private boolean waitFlag = true; // 设置一标志，false暂停，true运行。
	private JLabel timeLabel;
	private JLabel scoreLabel;
	private ClickButton btnPause;
	private ClickButton btnContinue;

	private VisualEffects effect;

	private JFrame singleGameFrame;

	private SwapIcon swap;

	private JLabel promptLabels[][];

	private JLabel avatarFrameLabel;
	private JLabel superLabel;

	private boolean listenerLock;// 响应锁,true为锁上,false为没有锁上

	// 变量用于控制动画，只有一个动画结束之后另一个动画才能开始
	private static Boolean threadRuned = false;

	private static boolean get_runed() {
		return threadRuned;
	}

	private static void change_runed() {
		synchronized (threadRuned) {
			if (threadRuned == false) {
				threadRuned = true;
			} else {
				threadRuned = false;
			}
		}
	}

	private static Boolean releaseUsed = false;

	private static boolean get_released() {
		return releaseUsed;
	}

	private static void change_releaseUsed() {
		synchronized (releaseUsed) {
			if (releaseUsed == false) {
				releaseUsed = true;
			} else {
				releaseUsed = false;
			}
		}
	}

	public SingleGamePanel() {
		initialUI();
		singleGamePanel = this;
	}

	// public void startGame() {
	// singleGameUI_Driver = new SingleGameUI_Driver();
	// // singleGameUI_Driver.mapInitial_Drive(singleGamePanel);// 获取地图信息驱动
	// singleGameUI_Driver.timeStart_Dirve(singleGamePanel);// 启动游戏驱动
	// }

	private void initialUI() {
		ImageIcon gameBgIcon = new ImageIcon("images/game/gameview.png");
		this.setBounds(0, 0, 1000, 700);
		this.setImage(gameBgIcon.getImage());
		imageHelper = new ImageHelper();

		listener = new DragPicListener();

		ChangePanelListener changePanelListener = new ChangePanelListener();

		ImageIcon backButtonIcon = new ImageIcon("images/返回.png");
		backButton = new ClickButton("images/返回.png");
		backButton.setBounds(30, 30, backButtonIcon.getIconWidth(),
				backButtonIcon.getIconHeight());
		backButton.addActionListener(changePanelListener);
		this.add(backButton);

		ImageIcon avatarFrameIcon = new ImageIcon("images/game/头像框.png");
		avatarFrameLabel = new JLabel(avatarFrameIcon);
		avatarFrameLabel.setBounds(30, 100, avatarFrameIcon.getIconWidth(),
				avatarFrameIcon.getIconHeight());
		this.add(avatarFrameLabel);

		gamePanel = new JPanel();
		gamePanel.setBounds(322, 118, 540, 540);
		this.add(gamePanel);
		gamePanel.setOpaque(false);
		gamePanel.setLayout(null);
		
		ImageIcon superIcon = new ImageIcon("images/game/super.png");
		superLabel = new JLabel(superIcon);
		superLabel.setBounds(280, 0, superIcon.getIconWidth(),
				superIcon.getIconHeight());
		superLabel.setOpaque(false);
		this.add(superLabel);
		superLabel.setVisible(false);

		promptInitial();

		timeLabel = new JLabel("60");
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 30));
		timeLabel.setBounds(800, 17, 311, 67);
		timeLabel.setOpaque(false);
		this.add(timeLabel);

		scoreLabel = new JLabel("0");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 30));
		scoreLabel.setBounds(400, 17, 311, 67);
		scoreLabel.setOpaque(false);
		this.add(scoreLabel);

		TimeListener timeListener = new TimeListener();
		
		ImageIcon pauseIcon = new ImageIcon("images/game/暂停.png");
		btnPause = new ClickButton("images/game/暂停.png");
		btnPause.setBounds(0, 600, pauseIcon.getIconWidth(),
				pauseIcon.getIconHeight());
		btnPause.addMouseListener(timeListener);
		btnPause.setVisible(true);
		this.add(btnPause);

		ImageIcon continueIcon = new ImageIcon("images/game/开始.png");
		btnContinue = new ClickButton("images/game/开始.png");
		btnContinue.setBounds(0, 600, continueIcon.getIconWidth(),
				continueIcon.getIconHeight());
		btnContinue.addMouseListener(timeListener);
		btnContinue.setVisible(false);
		this.add(btnContinue);

		this.setVisible(false);

		singleGamePanel = this;

		// 不断刷新的线程
		Runnable r = new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(r);
		thread.start();
	}

	// private void initialUI() {
	// setBounds(0, 0, 1000, 700);
	// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// getContentPane().setLayout(null);
	//
	// gamePanel = new JPanel();
	// gamePanel.setBounds(300, 120, 530, 530);
	// getContentPane().add(gamePanel);
	// gamePanel.setLayout(null);
	//
	// setVisible(true);
	//
	//
	// //不断刷新的线程
	// Runnable r = new Runnable() {
	//
	// @Override
	// public void run() {
	// while(true){
	// repaint();
	// try {
	// Thread.sleep(30);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// };
	// Thread thread = new Thread(r);
	// thread.start();
	// }

	// 为了让提示框处于面板最上层
	private void promptInitial() {
		promptLabels = new JLabel[9][9];
		ImageIcon promptIcon = new ImageIcon("images/game/提示框.png");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				promptLabels[i][j] = new JLabel(promptIcon);
				promptLabels[i][j].setBounds(60 * j - 1, 60 * i - 2, 55, 55);
				promptLabels[i][j].setVisible(false);
				gamePanel.add(promptLabels[i][j]);
			}
		}
	}

	@Override
	public void mapInitial(NewMapInfo newMapInfo) {
		if(map!=null){
			for(int m=0;m<9;m++){
				for(int n=0;n<9;n++){
					gamePanel.remove(map[m][n]);
				}
			}
		}
		
		timeLabel.setText("60");
		scoreLabel.setText("0");
		btnPause.setVisible(true);
		btnContinue.setVisible(false);
		unLock();
		
		map = new CrushLabel[9][9];
		int temp[][] = newMapInfo.getStates();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				ImageType imageType = ImageType.get(temp[i][j]);
				map[i][j] = new CrushLabel(imageType);
				map[i][j].setBounds(60 * j, 60 * i, 50, 50);
				map[i][j].addMouseListener(listener);
				map[i][j].addMouseMotionListener(listener);
				gamePanel.add(map[i][j]);
			}
		}
	}

	/**
	 * 执行消除动画，并且用新块填充地图
	 */
	public void crash(MapRefreshInfo mapRefreshInfo) {
		// 将对应需要消除的块进行消除
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		lock();
		setScore(mapRefreshInfo.getScore());
		PositionsGroup crashPositionsGroup = mapRefreshInfo
				.getCrashPositionsGroup();
		PositionsGroup newPositionsGroup = mapRefreshInfo
				.getNewPositionsGroup();

		ArrayList<Position> crushList = crashPositionsGroup.getArrayList();
		effect = VisualEffects.normal;
		int newMap[][] = mapRefreshInfo.getNewMap();// 新地图,需要重新赋值

		for (Position temp : crushList) {
			map[temp.getX()][temp.getY()].crush(effect);
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 获取所有需要调整位置的块
		ArrayList<Position> newList = newPositionsGroup.getArrayList();
		System.out.println("输出新增块坐标:");
		for (Position temp : newList) {
			System.out.println("(" + temp.getX() + "," + temp.getY() + ")");
		}

		DropMode dropMode = mapRefreshInfo.getDropMode();
		ArrayList<Position> moveList = fillMap(crushList, newList, dropMode,
				newMap);
		ArrayList<Pro> prop = mapRefreshInfo.getPros();
		setProp(prop, newMap);

		printMoveList(moveList);

		// 移动所有需要调整位置的块
		CrushLabel lastMove = null;
		for (Position loc : moveList) {
			map[loc.getX()][loc.getY()].move(loc.getY() * 60, loc.getX() * 60,
					map[loc.getX()][loc.getY()]);
			lastMove = map[loc.getX()][loc.getY()];
		}
		// judgeNull();
		while (true) {
			if (checkLock() == false) {
//				unLock();
				gameBLService.judgeAutoCrash();
				break;
			}
		}
		// printMap();
	}

	private void printMoveList(ArrayList<Position> moveList) {
		System.out.println("输出要被移动的块的坐标-------------------------------------");
		for (Position temp : moveList) {
			System.out.println("(" + temp.getX() + "," + temp.getY() + ")");
		}
		System.out
				.println("----------------------------------------------------------");
	}

	private void setProp(ArrayList<Pro> prop, int newMap[][]) {
		ImageIcon propA0 = new ImageIcon("images/game/fire1.png");
		ImageIcon propA1 = new ImageIcon("images/game/fire2.png");
		ImageIcon propA2 = new ImageIcon("images/game/fire3.png");
		ImageIcon propA3 = new ImageIcon("images/game/fire4.png");
		ImageIcon propA4 = new ImageIcon("images/game/fire5.png");
		ImageIcon propB = new ImageIcon("images/game/手里剑.png");
		for (Pro temp : prop) {
			int x = temp.getPosition().getX();
			int y = temp.getPosition().getY();
			if (temp.getProType() == ProType.ProA) {
				if (newMap[x][y] == 0) {
					map[x][y].setIcon(propA0);
				} else if (newMap[x][y] == 1) {
					map[x][y].setIcon(propA1);
				} else if (newMap[x][y] == 2) {
					map[x][y].setIcon(propA2);
				} else if (newMap[x][y] == 3) {
					map[x][y].setIcon(propA3);
				} else if (newMap[x][y] == 4) {
					map[x][y].setIcon(propA4);
				}
			} else if (temp.getProType() == ProType.ProB) {
				map[x][y].setIcon(propB);
			}
			map[x][y].validate();
		}
		// repaint();
	}

	private void judgeNull() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == null) {
					System.out.println("存在空的label块！！！！！！！！！");
				}
			}
		}
	}

	private boolean checkLock() {
		lock = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j].getX() != j * 60 || map[i][j].getY() != i * 60) {
					lock = true;
					break;
				}
			}
		}
		return lock;
	}

	public void lock() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.listenerLock = true;
			}
		}
	}

	public void unLock() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.listenerLock = false;
			}
		}
	}

	private ArrayList<Position> fillMap(ArrayList<Position> crushList,
			ArrayList<Position> newList, DropMode dropMode, int newMap[][]) {
		ArrayList<Position> moveIconList = new ArrayList<Position>();// 储存需要调整位置的块
		if (dropMode == DropMode.horizontal) {
			for (int i = 0; i < 9; i++) {
				int num = getDropInRow(crushList, i);
				if (num > 0) {
					int min = getMinInRow(crushList, i);
					for (int j = 0; j < 9 - min; j++) {
						if (min + j + num > 8) {
							break;
						}
						gamePanel.remove(map[i][min + j]);
						ImageType imageType = ImageType.get(newMap[i][min + j]);
						map[i][min + j] = new CrushLabel(imageType);
						map[i][min + j].setBounds((min + j) * 60, i * 60, 50,
								50);
						// DragPicListener listener = new DragPicListener();
						map[i][min + j].addMouseListener(listener);
						map[i][min + j].addMouseMotionListener(listener);
						gamePanel.add(map[i][min + j]);
						map[i][min + j].validate();
						moveIconList.add(new Position(i, min + j));
					}
					for (Position newLocation : newList) {
						int column = newLocation.getY();
						if (newLocation.getX() == i) {
							gamePanel.remove(map[i][column]);
							ImageType imageType = ImageType
									.get(newMap[i][column]);
							map[i][column] = new CrushLabel(imageType);
							map[i][column].setBounds((column + num) * 60,
									i * 60, 50, 50);
							// DragPicListener listener = new DragPicListener();
							map[i][column].addMouseListener(listener);
							map[i][column].addMouseMotionListener(listener);
							gamePanel.add(map[i][column]);
							gamePanel.validate();
							moveIconList.add(new Position(i, column));
						}
					}
				}
			}
		} else if (dropMode == DropMode.vertical) {
			for (int i = 0; i < 9; i++) {
				int num = getDropInColumn(crushList, i);
				if (num > 0) {
					int max = getMaxInColumn(crushList, i);
					for (int j = 0; j < max; j++) {
						if (max - j - num < 0) {
							break;
						}
						gamePanel.remove(map[max - j][i]);
						ImageType imageType = ImageType.get(newMap[max - j][i]);
						map[max - j][i] = new CrushLabel(imageType);
						map[max - j][i].setBounds(i * 60, (max - j - num) * 60,
								50, 50);
						// DragPicListener listener = new DragPicListener();
						map[max - j][i].addMouseListener(listener);
						map[max - j][i].addMouseMotionListener(listener);
						gamePanel.add(map[max - j][i]);
						map[max - j][i].validate();
						moveIconList.add(new Position(max - j, i));
					}
					for (Position newLocation : newList) {
						int row = newLocation.getX();
						if (newLocation.getY() == i) {
							gamePanel.remove(map[row][i]);
							ImageType imageType = ImageType.get(newMap[row][i]);
							map[row][i] = new CrushLabel(imageType);
							map[row][i].setBounds(i * 60, (row - num) * 60, 50,
									50);
							// DragPicListener listener = new DragPicListener();
							map[row][i].addMouseListener(listener);
							map[row][i].addMouseMotionListener(listener);
							gamePanel.add(map[row][i]);
							map[row][i].validate();
							moveIconList.add(new Position(row, i));
						}
					}
				}
			}
		}
		return moveIconList;
	}

	/**
	 * 获取某列存在被消除块的个数
	 * 
	 * @param crushList
	 * @param column
	 * @return
	 */
	private int getDropInColumn(ArrayList<Position> crushList, int column) {
		int num = 0;
		for (Position temp : crushList) {
			if (temp.getY() == column) {
				num++;
			}
		}
		return num;
	}

	/**
	 * 获取存在消除块的某列的行最大值
	 * 
	 * @param crushList
	 * @param column
	 * @return
	 */
	private int getMaxInColumn(ArrayList<Position> crushList, int column) {
		int max = -1;
		for (Position temp : crushList) {
			if (temp.getY() == column) {
				if (temp.getX() > max) {
					max = temp.getX();
				}
			}
		}
		return max;
	}

	/**
	 * 获取某行存在被消除的块的个数
	 * 
	 * @param crushList
	 * @param row
	 * @return
	 */
	private int getDropInRow(ArrayList<Position> crushList, int row) {
		int num = 0;
		for (Position temp : crushList) {
			if (temp.getX() == row) {
				num++;
			}
		}
		return num;
	}

	/**
	 * 获取存在消除块的某行的列最小值
	 * 
	 * @param crushList
	 * @param row
	 * @return
	 */
	private int getMinInRow(ArrayList<Position> crushList, int row) {
		int min = 10;
		for (Position temp : crushList) {
			if (temp.getX() == row) {
				if (temp.getY() < min) {
					min = temp.getY();
				}
			}
		}
		return min;
	}

	class DragPicListener implements MouseInputListener {
		Point point = new Point(0, 0); // 坐标点
		Point newPoint;
		CrushLabel pic;

		CrushLabel swap1;
		CrushLabel swap2;

		boolean dragResult;// 是否能够被拖动

		Position location;
		Direction direction;

		// Location preLocation;// 被拖动的图标的坐标
		// Direction preDirection;// 被拖动的方向

		// 判断被拖动的是哪一个图标，并记录其坐标
		private void judgePic(MouseEvent e) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					promptLabels[i][j].setVisible(false);
					if (e.getSource() == map[i][j]) {
						pic = map[i][j];
						promptLabels[i][j].setVisible(true);
						location = new Position(i, j);
					}
				}
			}
		}

		// 交换被拖动的两个图标的坐标
		private void swapLocation() {
			CrushLabel temp = map[location.getX()][location.getY()];
			if (direction == Direction.left) {
				map[location.getX()][location.getY()] = map[location.getX()][location
						.getY() - 1];
				map[location.getX()][location.getY() - 1] = temp;
				// direction = Direction.right;
				// location = new Location(location.getX(), location.getY() -
				// 1);
			} else if (direction == Direction.right) {
				map[location.getX()][location.getY()] = map[location.getX()][location
						.getY() + 1];
				map[location.getX()][location.getY() + 1] = temp;
				// direction = Direction.left;
				// location = new Location(location.getX(), location.getY() +
				// 1);
			} else if (direction == Direction.up) {
				map[location.getX()][location.getY()] = map[location.getX() - 1][location
						.getY()];
				map[location.getX() - 1][location.getY()] = temp;
				// direction = Direction.down;
				// location = new Location(location.getX() - 1,
				// location.getY());
			} else {
				map[location.getX()][location.getY()] = map[location.getX() + 1][location
						.getY()];
				map[location.getX() + 1][location.getY()] = temp;
				// direction = Direction.up;
				// location = new Location(location.getX() + 1,
				// location.getY());
			}
		}

		public void mousePressed(MouseEvent e) {
			/**
			 * if(get_pressused() == false){
			 * 
			 * change_pressUsed();
			 */

			if (listenerLock == false) {
				judgePic(e);
				// System.out.println("图片的信息，是否为空:" + pic);
				// point = SwingUtilities.convertPoint(pic, e.getPoint(),
				// pic.getParent()); // 得到当前坐标点
				point = new Point(e.getX(), e.getY());
			}

			/**
			 * }
			 * */
		}

		public void mouseDragged(MouseEvent e) {
			/**
			 * if(get_Drugged() == false){ change_Drugged();
			 */

			// newPoint = SwingUtilities.convertPoint(pic, e.getPoint(),
			// pic.getParent());

			/**
			 * }
			 * */
		}

		// 实现拖动交换，判断不能消除再返回
		public void mouseReleased(MouseEvent e) {
			/**
			 * if(get_released() ==
			 * false&&get_Drugged()==true&&get_pressused()==
			 * true&&get_runed()==false){
			 */
			if (listenerLock == false) {
				newPoint = new Point(e.getX(), e.getY());

				// 一旦鼠标释放，拖动提示消失
				promptLabels[location.getX()][location.getY()]
						.setVisible(false);

				System.out.println(get_released() + ";" + get_runed());
				if (get_released() == false && get_runed() == false) {
					change_releaseUsed();

					System.out
							.println("按下坐标为(" + point.x + "," + point.y + ")");
					System.out.println("释放坐标为 (" + newPoint.x + ","
							+ newPoint.y + ")");

					dragResult = true;
					try {
						if (newPoint.x - point.x > 5
								&& (newPoint.x - point.x) > Math.abs(newPoint.y
										- point.y)) {
							// System.out.println("向右");
							if (location.getY() == 8) {
								dragResult = false;
							}
							swap1 = map[location.getX()][location.getY()];
							swap2 = map[location.getX()][location.getY() + 1];
							direction = direction.right;
						} else if (point.x - newPoint.x > 5
								&& (point.x - newPoint.x) > Math.abs(newPoint.y
										- point.y)) {
							// System.out.println("向左");
							if (location.getY() == 0) {
								dragResult = false;
							}
							swap1 = map[location.getX()][location.getY() - 1];
							swap2 = map[location.getX()][location.getY()];
							direction = direction.left;
						} else if (newPoint.y - point.y > 5
								&& (newPoint.y - point.y) > Math.abs(newPoint.x
										- point.x)) {
							// System.out.println("向下");
							if (location.getX() == 8) {
								dragResult = false;
							}
							swap1 = map[location.getX()][location.getY()];
							swap2 = map[location.getX() + 1][location.getY()];
							direction = direction.down;
						} else if (point.y - newPoint.y > 5
								&& (point.y - newPoint.y) > Math.abs(newPoint.x
										- point.x)) {
							// System.out.println("向上");
							if (location.getX() == 0) {
								dragResult = false;
							}
							swap1 = map[location.getX() - 1][location.getY()];
							swap2 = map[location.getX()][location.getY()];
							direction = direction.up;
						} else {
							// System.out.println("the points are (" + point.x +
							// ","
							// + point.y + ") and (" + newPoint.x + ","
							// + newPoint.y + ")"); // TODO get the point
							dragResult = false;
							gameBLService.useProp(location);
						}
					} catch (Exception exp) {
						System.out.println("mouse clicked");
					}

					System.out.println("dragResult的值为" + dragResult);

					// 没有拖动，则可能会出错
					if (point != null && newPoint != null) {

						// // 判断有没有触壁不能拖动
						// swap = new SwapIcon(swap1, swap2, direction);
						//
						// Thread thread = new Thread(swap);
						if (dragResult == true) {
							// thread.start();
							swapMap(location, direction);

							while (true) {
								if (get_runed() == false) {
									break;
								}
							}

							// while (true) {
							// if (checkLock()==false) {
							// break;
							// }
							// }

							gameBLService.swap(location, direction);

							// 判断能否消除
							// dragResult = gameBLService.drag(location,
							// direction);
							// dragResult = true;
							// TODO 这里调用逻辑来获得消除结果
							// dragResult = mock.check();
							// ArrayList<Location> crushList = mock.getLocation(
							// location, direction);
							// effect = mock.getEffect();

							// while (true) {
							// if (get_runed() == false) {
							// swapLocation();
							// break;
							// }
							// }

							// singleGameUI_Driver.mapModify_Drive(singleGamePanel);

							// swap.setCrushLocation(crushList);
							// 若不能消除则将两个图标再交换回来
							// if (dragResult == false) {
							// Thread newThread = new Thread(swap);
							// newThread.start();
							// swapLocation();
							// }
						}
					}
					/**
					 * 暂时不用的控制符 change_Drugged();
					 * 
					 * change_pressUsed();
					 */
					change_releaseUsed();
				}
			}

		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			// System.out.println("鼠标点击");
			// gameBLService.useProp(location);
		}

		public void mouseMoved(MouseEvent e) {
		}

	}

	private void printMap() {
		System.out.println("输出地图label信息--------------------------------------");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// System.out.print("(" + map[i][j].getX() + ","
				// + map[i][j].getY() + ") ");
				System.out.print(map[i][j].isValid() + " ");
			}
			System.out.println("");
		}
		System.out
				.println("---------------------------------------------------");
	}

	public void swapMap(Position location, Direction direction) {
		lock();
		CrushLabel swap1;
		CrushLabel swap2;
		if (direction == Direction.left) {
			swap1 = map[location.getX()][location.getY() - 1];
			swap2 = map[location.getX()][location.getY()];
		} else if (direction == Direction.right) {
			swap1 = map[location.getX()][location.getY()];
			swap2 = map[location.getX()][location.getY() + 1];
		} else if (direction == Direction.up) {
			swap1 = map[location.getX() - 1][location.getY()];
			swap2 = map[location.getX()][location.getY()];
		} else {
			swap1 = map[location.getX()][location.getY()];
			swap2 = map[location.getX() + 1][location.getY()];
		}

		while (true) {
			if (get_runed() == false) {
				break;
			}
		}

		System.out.println("调用前线程运行指示布尔值 : " + get_runed());
		swap = new SwapIcon(swap1, swap2, direction);
		Thread thread = new Thread(swap);
		thread.start();

		while (true) {
			if (get_runed() == false) {
				break;
			}
		}

		CrushLabel temp = map[location.getX()][location.getY()];
		if (direction == Direction.left) {
			map[location.getX()][location.getY()] = map[location.getX()][location
					.getY() - 1];
			map[location.getX()][location.getY() - 1] = temp;
			// direction = Direction.right;
			// location = new Location(location.getX(), location.getY() - 1);
		} else if (direction == Direction.right) {
			map[location.getX()][location.getY()] = map[location.getX()][location
					.getY() + 1];
			map[location.getX()][location.getY() + 1] = temp;
			// direction = Direction.left;
			// location = new Location(location.getX(), location.getY() + 1);
		} else if (direction == Direction.up) {
			map[location.getX()][location.getY()] = map[location.getX() - 1][location
					.getY()];
			map[location.getX() - 1][location.getY()] = temp;
			// direction = Direction.down;
			// location = new Location(location.getX() - 1, location.getY());
		} else {
			map[location.getX()][location.getY()] = map[location.getX() + 1][location
					.getY()];
			map[location.getX() + 1][location.getY()] = temp;
			// direction = Direction.up;
			// location = new Location(location.getX() + 1, location.getY());
		}
		unLock();
		// printMap();
	}

	// 交换线程类
	class SwapIcon implements Runnable {

		CrushLabel swap1;
		CrushLabel swap2;

		Direction direction;
		ArrayList<Location> crushList = new ArrayList<Location>();

		public SwapIcon(CrushLabel swap1, CrushLabel swap2, Direction direction) {
			this.swap1 = swap1;
			this.swap2 = swap2;
			this.direction = direction;
		}

		@Override
		public void run() {
			swap();
		}

		private void swap() {
			while (true) {
				if (get_runed() == false) {
					change_runed();
					break;
				}
			}

			System.out.println("跑线程中的线程值:" + get_runed());
			// 为了让交换实现多线程
			if (swap1.getX() > swap2.getX() || swap1.getY() > swap2.getY()) {
				CrushLabel temp = swap1;
				swap1 = swap2;
				swap2 = temp;
			}

			int first;// 第一个图标交换前x或y坐标
			int second;// 第二个图标交换前x或y坐标
			if (direction == Direction.right || direction == Direction.left) {
				first = swap1.getX();
				second = swap2.getX();
				while (swap2.getX() != first && swap1.getX() != second) {
					swap1.setLocation(swap1.getX() + 3, swap1.getY());
					swap2.setLocation(swap2.getX() - 3, swap2.getY());
					if (swap1.getX() >= second && swap2.getX() <= first) {
						swap1.setLocation(second, swap1.getY());
						swap2.setLocation(first, swap2.getY());
					}
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
					}
					Thread.yield();
				}
			} else {
				first = swap1.getY();
				second = swap2.getY();
				while (swap2.getY() != first && swap1.getY() != second) {
					swap1.setLocation(swap1.getX(), swap1.getY() + 3);
					swap2.setLocation(swap2.getX(), swap2.getY() - 3);
					if (swap1.getY() >= second && swap2.getY() <= first) {
						swap1.setLocation(swap1.getX(), second);
						swap2.setLocation(swap2.getX(), first);
					}
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
					}
					Thread.yield();
				}
			}
			change_runed();
			System.out.println("跑完后的线程值:" + get_runed());
		}

		// for (Location location : crushList) {
		// map[location.getX()][location.getY()].crush(effect);
		// }

		// public void setCrushLocation(ArrayList<Location> crushList) {
		// if (crushList != null) {
		// this.crushList = crushList;
		// }
		// }
	}

	public synchronized void pause() {
		try {
			wait();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public synchronized void awake() {
		notifyAll();// 用notify()只会唤醒一个等待线程
	}

	class TimeRunner implements Runnable {
		@Override
		public void run() {
			while (count > 0) {
				String timeMessage = "";
				int seconds = (int) (count / 1000);
				int ms = (int) (count % 1000);
				if (seconds < 10) {
					timeMessage += "0" + seconds + ":";
				} else {
					timeMessage += seconds + ":";
				}
				if (ms < 10) {
					timeMessage += "00" + ms;
				} else if (ms < 100) {
					timeMessage += "0" + ms;
				} else {
					timeMessage += ms;
				}
				timeLabel.setText(timeMessage);
				count--;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
				while (!waitFlag) {
					System.out.println(Thread.currentThread().getName()
							+ " is pausing!");
					pause();
					System.out.println(Thread.currentThread().getName()
							+ " is continuing!");
				}
			}
			timeLabel.setText("00:000");
		}
	}

	class TimeListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btnPause) {
				// gameBLService.暂停请求
				btnPause.setVisible(false);
				btnContinue.setVisible(true);
				gameBLService.pauseGame();
			} else if (e.getSource() == btnContinue) {
				// gameBLService.继续请求
				btnContinue.setVisible(false);
				btnPause.setVisible(true);
				gameBLService.continueGame();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

	}

	// @Override
	// public void timeModify(TimeCommand timeCommand) {
	// if (timeCommand == TimeCommand.start) {
	// if (!timeRunner.isAlive()) {
	// timeRunner.start();
	// }
	// } else if (timeCommand == TimeCommand.pause) {
	// waitFlag = false;
	// } else if (timeCommand == TimeCommand.proceed) {
	// waitFlag = true;
	// awake();
	// }
	// }

	class ChangePanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			singleGamePanel.fadeOut();
			if (e.getSource() == backButton) {
				gameBLService.exitGame();
				mainFrame.changePanel("OptionPanel");
			}
		}

	}

	// 设置MainFrame的引用，用于发送面板切换的信息
	public void setMainFrame(MainFrame mainFrame, GameBLService gameBLService) {
		this.mainFrame = mainFrame;
		this.gameBLService = gameBLService;
	}

	public void setTime(String time) {
		timeLabel.setText(time);
	}

	public void setScore(int score) {
		scoreLabel.setText(Integer.toString(score));
	}

	public void superTimeOn() {
//		System.out.println("Super Time~!!!");
		superLabel.setVisible(true);
	}

	public void superTimeOff() {
//		System.out.println("Off");
		superLabel.setVisible(false);
	}

	public void gameOver() {
		lock();
		JOptionPane.showMessageDialog(null, "游戏结束!", "系统消息",
				JOptionPane.INFORMATION_MESSAGE);
		// this.fadeOut();
		// mainFrame.changePanel("OptionPanel");
	}

	private void closePrompt() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				promptLabels[i][j].setVisible(false);
			}
		}
	}

	public void prompt(PositionsGroup positionsGroup) {
		closePrompt();
		for (int i = 0; i < positionsGroup.size(); i++) {
			int x = positionsGroup.get(i).getX();
			int y = positionsGroup.get(i).getY();
			promptLabels[x][y].setVisible(true);
		}
	}

	@Override
	public void mapModify(MapRefreshInfoVO vo) {
		// TODO Auto-generated method stub

	}
}
