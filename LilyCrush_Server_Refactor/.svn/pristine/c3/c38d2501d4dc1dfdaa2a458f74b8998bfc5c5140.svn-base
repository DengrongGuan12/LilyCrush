package model;

import java.util.ArrayList;

import model.managers.PropManager;
import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;
import common.gameInfo.Pro;
import enums.DropMode;

public class Map {
	private static int rows;
	private static int columns;
	private DropMode dropMode = DropMode.vertical;

	private PositionsGroup newPositionsGroup;
	private PositionsGroup newElimGroup;
	private PositionsGroup scoreGroup;
	private PropManager propManager;

	public ArrayList<Pro> getPros() {
		return this.propManager.getPros();
	}
	
	public void setPropManager(PropManager propManager) {
		this.propManager = propManager;
	}

	public PositionsGroup useAllPro() {
		return this.propManager.useAllPro();
	}
	
	public boolean isProp(Position p){
		if(this.propManager.isPro(p) == null){
			return false;
		}else {
			return true;
		}
		
	}
	private void setDropMode() {
		int temp = (int) (Math.random() * 2);
		if (temp < 1) {
			this.dropMode = DropMode.vertical;
		} else {
			this.dropMode = DropMode.horizontal;
		}
	}
	
	public DropMode getDropMode(){
		return this.dropMode;
	}

	public static int getRows() {
		return rows;
	}

	public static void setRows(int row) {
		rows = row;
	}

	public static int getColumns() {
		return columns;
	}

	public static void setColumns(int column) {
		columns = column;
	}

	public PositionsGroup newElimGroup() {
		return this.newElimGroup;
	}
	
	public PositionsGroup getScoreGroup() {
		return this.scoreGroup;
	}
	

	public void setstates(int[][] states) {
		this.states = states;
	}

	public int getNumOfImages() {
		return numOfImages;
	}

	public void setNumOfImages(int numOfImages) {
		this.numOfImages = numOfImages;
	}

	private int states[][];
	public int[][] getStates() {
		return states;
	}

	private int numOfImages;

	public Map(int rows, int columns, int numOfImages) {
		Map.setRows(rows);
		Map.setColumns(columns);
		this.setNumOfImages(numOfImages);
		this.states = new int[rows][columns];
	}

	public void initial() {
		this.propManager = new PropManager();
		PositionsGroup positionsGroup = null;
		while (true) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					int temp = (int) (Math.random() * this.numOfImages);
					// 初始化的条件：1.不能自己消除,2.不能有死局
					// 条件1的判断：
					if (j >= 2) {
						if (states[i][j - 1] == states[i][j - 2]
								&& temp == states[i][j - 1]) {
							temp = (temp + 1) % this.numOfImages;

						}
					}
					if (i >= 2) {
						if (states[i - 1][j] == states[i - 2][j]
								&& states[i - 1][j] == temp) {
							temp = (temp + 1) % this.numOfImages;
						}
					}
					states[i][j] = temp;
					System.out.print(temp + ",");

				}
				System.out.println();
			}
			positionsGroup = JudgeAndHint.judgeIfDead(states);
			if (positionsGroup == null) {
				continue;
			} else {
				// positionsGroup.showPositions();
				break;
			}
		}

	}



	/*
	 * 改变一个位置的值
	 */
	public void changestate(Position p, int state) {
		this.states[p.getX()][p.getY()] = state;

	}

	// 交换两个坐标的值
	public void swapState(Position p1, Position p2) {
		int x1 = p1.getX();
		int y1 = p1.getY();
		int x2 = p2.getX();
		int y2 = p2.getY();
		int temp = this.states[x1][y1];
		this.states[x1][y1] = this.states[x2][y2];
		this.states[x2][y2] = temp;
	}

	/*
	 * 根据的消除的坐标生成一张的新的地图
	 */
	public void refresh(PositionsGroup elimGroup) {
		this.setDropMode();
//		this.dropMode = DropMode.vertical;
		
		//克隆初始消除元组
		PositionsGroup positionsGroup = elimGroup.clone();

		//使用消除元组中的道具产生新的消除元组
		elimGroup = propManager.usePro(elimGroup);
		
		this.scoreGroup = new PositionsGroup();
		this.scoreGroup = elimGroup.clone();
						
		//生成道具
		elimGroup.remove(propManager.generatePro(positionsGroup,this));
		
		this.newElimGroup = new PositionsGroup();
		this.newElimGroup = elimGroup.clone();
		
		if (this.dropMode == DropMode.vertical) {
			for (int i = 0; i < getColumns(); i++) { // i为列，j为行
				// System.out.println(i + "列");
				PositionsGroup tempGroup = new PositionsGroup(); // 记录该列中不消除的元素坐标(从下到上)
				PositionsGroup tempElimGroup = new PositionsGroup(); // 记录该列中消除的元素坐标（从下到上）
				int rowMax = this.getRowMax(i, elimGroup);
				// rowMax不为-1,表示该列中有消除的元素
				if (rowMax != -1) {
					// System.out.println("elim");
					tempElimGroup.addPosition(new Position(rowMax, i));
					for (int j = rowMax - 1; j >= 0; j--) {
						if (isInGroup(j, i, elimGroup)) {
							tempElimGroup.addPosition(new Position(j, i));
						} else {
							tempGroup.addPosition(new Position(j, i));
						}
					}
					// 重排地图
					int elimSize = tempElimGroup.size();
					int tempSize = tempGroup.size();
					if (elimSize <= tempSize) {
						for (int temp = 0; temp < elimSize; temp++) {
							moveMap((Position) tempGroup.get(temp),
									(Position) tempElimGroup.get(temp));
						}
						int label = 0;
						for (int temp = elimSize; temp < tempSize; temp++) {
							moveMap((Position) tempGroup.get(temp),
									(Position) tempGroup.get(label));
							label++;
						}
					} else {
						for (int temp = 0; temp < tempSize; temp++) {
							moveMap((Position) tempGroup.get(temp),
									(Position) tempElimGroup.get(temp));
						}
					}
					// 将地图顶部元素更换为-1，表示待填充
					setFillState(i, elimSize);
				}
			}
		} else if (dropMode == DropMode.horizontal) {
			for (int i = 0; i < getRows(); i++) { // i为行，j为列
				// System.out.println(i + "行");
				PositionsGroup tempGroup = new PositionsGroup(); // 记录该列中不消除的元素坐标(从右到左)
				PositionsGroup tempElimGroup = new PositionsGroup(); // 记录该列中消除的元素坐标（从右到左）
				int columnMin = this.getColumnMin(i, elimGroup);
				// System.out.println("columnMin" + columnMin);
				// columnMin不为-1,表示该行中有消除的元素
				if (columnMin != -1) {
					// System.out.println("elim");
					tempElimGroup.addPosition(new Position(i, columnMin));
					for (int j = columnMin + 1; j < getColumns(); j++) {
						if (isInGroup(i, j, elimGroup)) {
							tempElimGroup.addPosition(new Position(i, j));
						} else {
							tempGroup.addPosition(new Position(i, j));
						}
					}
					// 重排地图
					int elimSize = tempElimGroup.size();
					// System.out.println("elimSize" + elimSize);
					int tempSize = tempGroup.size();
					if (elimSize <= tempSize) {
						for (int temp = 0; temp < elimSize; temp++) {
							moveMap((Position) tempGroup.get(temp),
									(Position) tempElimGroup.get(temp));
						}
						int label = 0;
						for (int temp = elimSize; temp < tempSize; temp++) {
							moveMap((Position) tempGroup.get(temp),
									(Position) tempGroup.get(label));
							label++;
						}
					} else {
						for (int temp = 0; temp < tempSize; temp++) {
							moveMap((Position) tempGroup.get(temp),
									(Position) tempElimGroup.get(temp));
						}
					}
					// 将地图顶部元素更换为-1，表示待填充
					setFillState(i, elimSize);
				}
			}
		}
		// 填充待填充地图
		do {
			fillMap();
			PositionsGroup group = JudgeAndHint.judgeIfDead(states);
			if (group != null) {
				//group.showPositions();
				break;
			}
		} while (true);

	}

	/*
	 * 返回消除单元中行最大标号
	 * 
	 * @param rowMax - 行最大标号（rowMax为-1表示elimGroup中无对应行号） 用于垂直消除（从上到下）
	 */
	private int getRowMax(int yLabel, PositionsGroup elimGroup) {
		int rowMax = -1;
		for (int i = 0; i < elimGroup.size(); i++) {
			Position position = (Position) (elimGroup.get(i));
			int x = position.getX();
			int y = position.getY();
			if (y == yLabel) {
				if (rowMax < x || rowMax == -1) {
					rowMax = x;
				}
			}
		}
		return rowMax;
	} // end rowMax method

	/**
	 * 判断给定坐标（x, y）是否在elimGroup中
	 * 
	 * @param x
	 * @param y
	 * @param elimGroup
	 * @return result
	 */
	private boolean isInGroup(int x, int y, PositionsGroup elimGroup) {
		boolean result = false;
		for (int i = 0; i < elimGroup.size(); i++) {
			int tempX = ((Position) elimGroup.get(i)).getX();
			int tempY = ((Position) elimGroup.get(i)).getY();
			if (x == tempX && y == tempY) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 移动地图指定坐标的单元，从p1到p2,不改变p1的值
	 */
	private void moveMap(Position p1, Position p2) {
		int x1 = p1.getX();
		int x2 = p2.getX();
		int y1 = p1.getY();
		int y2 = p2.getY();
		this.states[x2][y2] = this.states[x1][y1];
		//若移动单元为道具，则移动对应道具的坐标
		propManager.changeProPositioin(p1, p2);
	}

	/**
	 * 设置地图元素待填充状态
	 * 
	 * @param label
	 * @param size
	 */
	private void setFillState(int label, int size) {
		if (this.dropMode == DropMode.vertical) {
			// label代表对应元素的纵坐标
			for (int i = 0; i < size; i++) {
				this.states[i][label] = -1;
			}
		} else if (this.dropMode == DropMode.horizontal) {
			// label代表对应元素的横坐标
			for (int i = getColumns() - 1; i >= getColumns() - size; i--) {
				this.states[label][i] = -1;
			}
		}
	}

	/*
	 * 返回消除单元中列最小标号
	 * 
	 * @param columnMin - 列最小标号（columnMin为-1表示elimGroup中无对应列号） 用于水平消除（从右到左）
	 */
	private int getColumnMin(int xLabel, PositionsGroup elimGroup) {
		int columnMin = -1;
		for (int i = 0; i < elimGroup.size(); i++) {
			int x = ((Position) elimGroup.get(i)).getX();
			int y = ((Position) elimGroup.get(i)).getY();
			if (x == xLabel) {
				if (columnMin > y || columnMin == -1) {
					columnMin = y;
				}
			}
		}
		return columnMin;
	}

	/**
	 * 将待填充地图填充好，即将处于待填充态的元素（-1）随机填充
	 */
	private void fillMap() {
		newPositionsGroup = new PositionsGroup();
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				// 将处于待填充态的元素随机填充
				if (this.states[i][j] == -1) {
					int value = (int) (Math.random() * getNumOfImages());
					this.states[i][j] = value;
					newPositionsGroup.addPosition(new Position(i, j, value));
				}
			}
		}
	}
	/*
	 * 执行过refresh之后调用，返回值为新增的位置组
	 */
	public PositionsGroup getNewPositionsGroup(){
		return this.newPositionsGroup;
	}

	public void printStates(){
		for (int i = 0; i < this.states.length; i++) {
			for (int j = 0; j < this.states[i].length; j++) {
				System.out.print(this.states[i][j] + ",");
			}
			System.out.println();
		}
	}
	public void changeProsPosition(Position p1,Position p2){
		this.propManager.swapProPosition(p1, p2);
	}
}
