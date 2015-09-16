package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;

public class JudgeCrush {
	public static PositionsGroup PositionChanged(Position p1, Position p2,int states[][]) {
		PositionsGroup positionsGroup = new PositionsGroup();
		CrushState crashmap[] = new CrushState[2];
		crashmap[0] = new CrushState();
		crashmap[1] = new CrushState();
		Position positions[] = new Position[2];
		positions[0] = p1;
		positions[1] = p2;
		int x1 = p1.getX();
		int y1 = p1.getY();
		int x2 = p2.getX();
		int y2 = p2.getY();
		// 如果移动的是两个连续相同的图片无反应
		if ( states[x1][y1] !=  states[x2][y2]) {

			for (int i = 0; i < 2; i++) {
				int x = positions[i].getX();
				int y = positions[i].getY();
				// 竖直方向连续相同的数量
				int verticalSuc = 1;
				crashmap[i].addVerticalPosition(positions[i]);
				// 水平方向连续相同的数量
				int horizontalSuc = 1;
				crashmap[i].addhorizontalPosition(positions[i]);
				for (int j = 1; j <= 2; j++) {
					// 上
					if ((x - j) < 0) {
						break;
					} else {
						if ( states[x][y] ==  states[x - j][y]) {

							crashmap[i].addVerticalPosition(new Position(x - j,
									y));
							verticalSuc++;
						} else {
							break;
						}
					}

				}
				for (int j = 1; j <= 2; j++) {
					// 下
					if ((x + j) == Map.getRows()) {
						break;
					} else {
						if ( states[x][y] ==  states[x + j][y]) {
							crashmap[i].addVerticalPosition(new Position(x + j,
									y));
							verticalSuc++;
						} else {
							break;
						}
					}

				}
				if (verticalSuc < 3) {
					// 清空竖直方向上的位置组
					crashmap[i].clearVerticalPositions();
				}
				for (int j = 1; j <= 2; j++) {
					// 左
					if ((y - j) < 0) {
						break;
					} else {
						if ( states[x][y] ==  states[x][y - j]) {
							crashmap[i].addhorizontalPosition(new Position(x, y
									- j));
							horizontalSuc++;
						} else {
							break;
						}
					}

				}
				for (int j = 1; j <= 2; j++) {
					// 右
					if ((y + j) == Map.getColumns()) {
						break;
					} else {
						if ( states[x][y] ==  states[x][y + j]) {
							crashmap[i].addhorizontalPosition(new Position(x, y
									+ j));
							horizontalSuc++;
						} else {
							break;
						}
					}

				}
				if (horizontalSuc < 3) {
					// 清空水平方向上的位置组
					crashmap[i].clearHorizontalPositions();
				}
			}
			for (int i = 0; i < 2; i++) {
				positionsGroup.combine(crashmap[i].getHorizontalPositions());
				positionsGroup.combine(crashmap[i].getVerticalPositions());
			}

		}
		return positionsGroup;

	}
	public static PositionsGroup autoElim(int[][] states) {
		PositionsGroup positionsGroup = new PositionsGroup();
		ArrayList<Set<Point>> setList= new ArrayList<Set<Point>>();
		setList=findDoubleJointGrid(states);
		ArrayList<Set<Point>> b =  getJointSetList(setList);
		for (Set<Point> s : b) {
			for (Point p : s) {
				// System.out.print("("+p.x+" "+p.y+")");
				positionsGroup.addPosition(new Position(p.x, p.y));
			}
			// System.out.println();
		}
		return positionsGroup;
	}
	private static ArrayList<Set<Point>> findDoubleJointGrid(int states[][]){
		ArrayList<Set<Point>> setList= new ArrayList<Set<Point>>();
		for(int i=0;i<9;i++){
			if(i==8){
				for(int j=0;j<8;j++){
					if( states[i][j]== states[i][j+1]){
						Point p1=new Point(i,j);
						Point p2=new Point(i,j+1);
						Set<Point> s=new HashSet<Point>();
						s.add(p1);
						s.add(p2);
						setList.add(s);
					}
				}
			}else{
				for(int j=0;j<8;j++){
					if( states[i][j]== states[i][j+1]){
						Point p1=new Point(i,j);
						Point p2=new Point(i,j+1);
						Set<Point> s=new HashSet<Point>();
						s.add(p1);
						s.add(p2);
						setList.add(s);
					}
					if( states[i][j]== states[i+1][j]){
						Point p1=new Point(i,j);
						Point p2=new Point(i+1,j);					
						Set<Point> s=new HashSet<Point>();
						s.add(p1);
						s.add(p2);
						setList.add(s);
					}
				}
				
			}
		}
		for(int i=0;i<8;i++){
			if( states[i][8]== states[i+1][8]){				
				Point p1=new Point(i,8);
				Point p2=new Point(i+1,8);					
				Set<Point> s=new HashSet<Point>();
				s.add(p1);
				s.add(p2);
				setList.add(s);
			}
		}
		return setList;
	
	}
	private static ArrayList<Set<Point>> getJointSetList(ArrayList<Set<Point>> setList) {

		ArrayList<Set<Point>> jointSetList = new ArrayList<Set<Point>>();
		ArrayList<Set<Point>> copy = new ArrayList<Set<Point>>();
		copy.addAll(setList);
		for (int i = 0; i < setList.size() - 1; i++) {
			Set<Point> item = new HashSet<Point>();
			item.addAll(setList.get(i));
			for (int j = i + 1; j < setList.size(); j++) {
				boolean isContained = false;
				for (Point p : item) {
					if (copy.get(j).contains(p)) {
						isContained = true;
					}
				}
				if (isContained) {
					item.addAll(copy.get(j));
					boolean isIn = false;
					int index = 0;
					for (Point p : item) {
						for (Set<Point> s : jointSetList) {
							if (s.contains(p)) {
								isIn = true;
								index = jointSetList.indexOf(s);
							}
						}
					}
					if (isIn) {
						jointSetList.get(index).addAll(item);
					} else {
						jointSetList.add(item);
					}
				}
			}

		}
		/* 去掉不符合消除规则的集合 */
		ArrayList<Set<Point>> listToDel = new ArrayList<Set<Point>>();
		ArrayList<Set<Point>> listToAdd = new ArrayList<Set<Point>>();
		for (Set<Point> setItem : jointSetList) {
			if (setItem.size() == 3) {
				Iterator iterator = setItem.iterator();
				Point p1 = (Point) iterator.next();
				Point p2 = (Point) iterator.next();
				Point p3 = (Point) iterator.next();
				if ((p1.x == p2.x && p2.x == p3.x)
						|| (p1.y == p2.y && p2.y == p3.y)) {

				} else {
					listToDel.add(setItem);
				}

			}
			if (setItem.size() >= 4) {
				listToDel.add(setItem);
				listToAdd.add(checkSet(setItem));
			}
		}
		jointSetList.removeAll(listToDel);
		jointSetList.addAll(listToAdd);

		return jointSetList;

	}
	private static Set<Point> checkSet(Set<Point> target) {

		Set<Point> result = new HashSet<Point>();
		ArrayList<Point> xPoint = new ArrayList<Point>();
		ArrayList<Point> yPoint = new ArrayList<Point>();
		int[] valuesOfX = new int[target.size()];
		int[] valuesOfY = new int[target.size()];
		int index = 0;
		for (Point item : target) {
			valuesOfX[index] = item.x;
			valuesOfY[index] = item.y;
			index++;
		}
		int x = findMode(valuesOfX, target.size());
		int y = findMode(valuesOfY, target.size());
		for (Point item : target) {
			if (item.x == x) {
				xPoint.add(item);
			}
			if (item.y == y) {
				yPoint.add(item);
			}
		}

		if (xPoint.size() >= 3) {
			result.addAll(xPoint);
		}
		if (yPoint.size() >= 3) {
			result.addAll(yPoint);
		}

		return result;

	}
	private static int findMode(int[] values, int size) {
		int temp = values[0];
		for (int i = 0; i < size - 1; i++) {
			if (getFrequency(values, size, values[i]) < getFrequency(
					values, size, values[i + 1])) {
				temp = values[i + 1];
			}
		}
		return temp;
	}
	private static int getFrequency(int[] values, int size, int x) {
		int i, frequency = 0;
		for (i = 0; i < size; i++) {
			if (values[i] == x) {
				frequency++;
			}
		}
		return frequency;
	}

}
