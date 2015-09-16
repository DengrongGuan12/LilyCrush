package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import common.gameInfo.NewMapInfo;
import view.component.*;
import UIMock.CheckIfCrushMock;
import businesslogicService.GameBLService;
import enums.Direction;
import enums.ImageType;
import enums.TimeCommand;
import enums.VisualEffects;
import viewService.MapService;
import vos.Location;
import vos.MapInitialVO;
import vos.MapRefreshInfoVO;

public class SingleGameUI extends JFrame implements MapService{

	//TODO 消除判断的逻辑mock
	CheckIfCrushMock mock = new CheckIfCrushMock();
	
	GameBLService singleGameUI;
	
	private VisualEffects effect;

	private ImageType mapInfo[][];
	private CrushLabel map[][];
	private static int i = 0;

	// 变量用于控制动画，只有一个动画结束之后另一个动画才能开始
	private static Boolean threadRuned = false;

	private static boolean get_runed() {
		return threadRuned;
	}

	private synchronized static void change_runed() {
		synchronized (threadRuned) {
			if (threadRuned == false) {
				threadRuned = true;
			} else {
				threadRuned = false;
			}
		}
	}

	private static Boolean releaseUsed = false;

	private static synchronized boolean get_released() {
		return releaseUsed;
	}

	private static synchronized void change_releaseUsed() {
		synchronized (releaseUsed) {
			if (releaseUsed == false) {
				releaseUsed = true;
			} else {
				releaseUsed = false;
			}
		}
	}

	/**
	 * 以下的是暂时用不到的控制符，对于pressed和drugged里面的语句不是很了解，不知道不控制会有什么影响，暂时没看出来
	 */
	/**
	 * private static Boolean press_used = false; private static boolean
	 * get_pressused(){ return press_used; } private static void
	 * change_pressUsed(){ synchronized(press_used){ if (press_used == false) {
	 * press_used = true; }else { press_used = false; } } }
	 * 
	 * 
	 * 
	 * private static Boolean druged = false;
	 * 
	 * private static boolean get_Drugged(){ return druged; } private static
	 * void change_Drugged(){ synchronized(druged){ if (druged == false) {
	 * druged = true; }else { druged = false; } } }
	 */

	private JFrame singleGameFrame;
	private JPanel gamePanel;

	public SingleGameUI() {
		initialUI();
	}
	
	
	private void initialUI() {
		setBounds(0, 0, 1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		gamePanel = new JPanel();
		gamePanel.setBounds(300, 120, 530, 530);
		getContentPane().add(gamePanel);
		gamePanel.setLayout(null);

		setVisible(true);
		
		
		//不断刷新的线程
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				while(true){
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

	@Override
	public void mapInitial(NewMapInfo newMapInfo) {
		map = new CrushLabel[9][9];
//		mapInfo = vo.getMap();
		for (int i = 0; i < mapInfo.length; i++) {
			for (int j = 0; j < mapInfo[i].length; j++) {
				map[i][j] = new CrushLabel(mapInfo[i][j]);
				map[i][j].setBounds(60 * i, 60 * j, 50, 50);
				DragPicListener listener = new DragPicListener();
				map[i][j].addMouseListener(listener);
				map[i][j].addMouseMotionListener(listener);
				gamePanel.add(map[i][j]);
			}
		}
	}

	@Override
	public void mapModify(MapRefreshInfoVO vo) {
		
	}

	class DragPicListener implements MouseInputListener {
		Point point = new Point(0, 0); // 坐标点
		Point newPoint;
		CrushLabel pic;

		CrushLabel swap1;
		CrushLabel swap2;

		boolean dragResult;// 是否能够被拖动

		Location location;// 被拖动的图标的坐标
		Direction direction;// 被拖动的方向

		// 判断被拖动的是哪一个图标，并记录其坐标
		private void judgePic(MouseEvent e) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (e.getSource() == map[i][j]) {
						pic = map[i][j];
						location = new Location(i, j);
					}
				}
			}
		}

		// 交换被拖动的两个图标的坐标
		private void swapLocation() {
			CrushLabel temp = map[location.getX()][location.getY()];
			if (direction == Direction.left) {
				map[location.getX()][location.getY()] = map[location.getX() - 1][location
						.getY()];
				map[location.getX() - 1][location.getY()] = temp;
				direction = Direction.right;
				location = new Location(location.getX() - 1, location.getY());
			} else if (direction == Direction.right) {
				map[location.getX()][location.getY()] = map[location.getX() + 1][location
						.getY()];
				map[location.getX() + 1][location.getY()] = temp;
				direction = Direction.left;
				location = new Location(location.getX() + 1, location.getY());
			} else if (direction == Direction.up) {
				map[location.getX()][location.getY()] = map[location.getX()][location
						.getY() - 1];
				map[location.getX()][location.getY() - 1] = temp;
				direction = Direction.down;
				location = new Location(location.getX(), location.getY() - 1);
			} else {
				map[location.getX()][location.getY()] = map[location.getX()][location
						.getY() + 1];
				map[location.getX()][location.getY() + 1] = temp;
				direction = Direction.up;
				location = new Location(location.getX(), location.getY() + 1);
			}
		}

		public void mousePressed(MouseEvent e) {
			/**
			 * if(get_pressused() == false){
			 * 
			 * change_pressUsed();
			 */

			judgePic(e);
			point = SwingUtilities.convertPoint(pic, e.getPoint(),
					pic.getParent()); // 得到当前坐标点

			/**
			 * }
			 * */
		}

		public void mouseDragged(MouseEvent e) {
			/**
			 * if(get_Drugged() == false){ change_Drugged();
			 */

			newPoint = SwingUtilities.convertPoint(pic, e.getPoint(),
					pic.getParent());

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
			System.out.println(get_released() +";"+ get_runed());
			if (get_released() == false && get_runed() == false) {
				change_releaseUsed();
				
//				System.out.println("the points are ("+newPoint.x+","+newPoint.y+")");
				
				dragResult = true;
				try {
					if (newPoint.x - point.x > 0
							&& (newPoint.x - point.x) > Math.abs(newPoint.y
									- point.y)) {
						// System.out.println("向右");
						if (location.getX() == 8) {
							dragResult = false;
						}
						swap1 = map[location.getX()][location.getY()];
						swap2 = map[location.getX() + 1][location.getY()];
						direction = direction.right;
					} else if (point.x - newPoint.x > 0
							&& (point.x - newPoint.x) > Math.abs(newPoint.y
									- point.y)) {
						// System.out.println("向左");
						if (location.getX() == 0) {
							dragResult = false;
						}
						swap1 = map[location.getX() - 1][location.getY()];
						swap2 = map[location.getX()][location.getY()];
						direction = direction.left;
					} else if (newPoint.y - point.y > 0
							&& (newPoint.y - point.y) > Math.abs(newPoint.x
									- point.x)) {
						// System.out.println("向下");
						if (location.getY() == 8) {
							dragResult = false;
						}
						swap1 = map[location.getX()][location.getY()];
						swap2 = map[location.getX()][location.getY() + 1];
						direction = direction.down;
					} else if (point.y - newPoint.y > 0
							&& (point.y - newPoint.y) > Math.abs(newPoint.x
									- point.x)) {
						// System.out.println("向上");
						if (location.getY() == 0) {
							dragResult = false;
						}
						swap1 = map[location.getX()][location.getY() - 1];
						swap2 = map[location.getX()][location.getY()];
						direction = direction.up;
					}else {
						System.out.println("the points are ("+point.x+","+point.y+") and ("+newPoint.x+","+newPoint.y+")");	//TODO get the point
					}
				} catch (Exception exp) {
					System.out.println("mouse clicked");
				}
				
				//没有拖动，则可能会出错
				if(point!=null && newPoint != null){
				
				// 判断有没有触壁不能拖动
				SwapIcon swap = new SwapIcon(swap1, swap2, direction);
				
				Thread thread = new Thread(swap);
				if (dragResult == true) {
					thread.start();
				}

				// 判断能否消除
				// dragResult = gameBLService.drag(location, direction);
//				dragResult = false;
				//TODO 这里调用逻辑来获得消除结果
				dragResult = mock.check();
				ArrayList<Location> crushList = mock.getLocation(location,direction);
				effect = mock.getEffect();
				
				swapLocation();

				swap.setCrushLocation(crushList);
				// 若不能消除则将两个图标再交换回来
				if (dragResult == false) {
					Thread newThread = new Thread(swap);
					newThread.start();
					swapLocation();
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

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}

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
		public void setCrushLocation(ArrayList<Location> crushList){
			if (crushList != null) {
				this.crushList = crushList;
			}
		}

		@Override
		public void run() {
			swap();
		}

		private  synchronized void swap() {
			if (get_runed() == false) {
				change_runed();

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
						swap1.setLocation(swap1.getX() + 1, swap1.getY());
						swap2.setLocation(swap2.getX() - 1, swap2.getY());
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
						swap1.setLocation(swap1.getX(), swap1.getY() + 1);
						swap2.setLocation(swap2.getX(), swap2.getY() - 1);
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
						}
						Thread.yield();
					}
				}
			}
			change_runed();
			
			for (Location location: crushList){
				map[location.getX()][location.getY()].crush(effect);
			}
			
		}
	}

//	@Override
//	public void timeModify(TimeCommand timeCommand) {
//		
//	}
}
