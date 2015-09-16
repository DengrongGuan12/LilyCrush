package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;

public class PositionsGroupOperation {
	// 存放水平消除组
	public static ArrayList<PositionsGroup> horizontalList;
	// 存放垂直消除组
	public static ArrayList<PositionsGroup> verticalList;
//	private static int rows = Map.getRows();
//	private static int columns = Map.getColumns();
	private static int rows = 9;
	private static int columns = 9;
	/**
	 * 将消除元组分割为大于3个的连续的水平、垂直组
	 * 
	 * @param elimGroup
	 *            -消除元组
	 */
	public static void divideGroup(PositionsGroup elimGroup) {
		horizontalList = new ArrayList<PositionsGroup>();
		verticalList = new ArrayList<PositionsGroup>();

		// 按照水平方向分割消除组
		for (int i = 0; i < rows; i++) {
			PositionsGroup horizontal = new PositionsGroup();
			for (int j = 0; j < elimGroup.size(); j++) {
				if (isInRow(i, elimGroup.get(j))) {
					horizontal.addPosition(elimGroup.get(j));
				}
			}
			if (horizontal.size() >= 3) {
				// 整理水平方向元素组
				horizontal = arrangeGroup(horizontal, true).clone();
				// 水平方向消除元素超过3个时添加位置组
				if (horizontal.size() >= 3) {
					horizontalList.add(horizontal);
				}
			}
		}

		// 按照垂直方向分割消除组
		for (int i = 0; i < columns; i++) {
			PositionsGroup vertical = new PositionsGroup();
			for (int j = 0; j < elimGroup.size(); j++) {
				if (isInColumn(i, elimGroup.get(j))) {
					vertical.addPosition(elimGroup.get(j));
				}
			}
			if (vertical.size() >= 3) {
				// 整理垂直方向元素组
				vertical = arrangeGroup(vertical, false).clone();
				// 垂直方向消除元素超过3个时添加位置组
				if (vertical.size() >= 3) {
					verticalList.add(vertical);
				}
			}
		}
		
//		System.out.println("horizontalList.size:" + horizontalList.size());
//		System.out.println("verticalList.size:"+ verticalList.size());
	}

	/**
	 * 重新整理元素组，并删除非连续元素
	 * 
	 * @param elimGroup
	 *            -消除元素组
	 * @param direction
	 *            -方向（true表示水平，false表示垂直）
	 * @return
	 */
	private static PositionsGroup arrangeGroup(PositionsGroup elimGroup,
			boolean direction) {
		ArrayList<Integer> errorList = isSeries(elimGroup, direction);
		if (!errorList.isEmpty()) {
			if (direction) {
				// 消除水平方向非连续元素
				int x = elimGroup.get(0).getX();
				for (int i = 0; i < errorList.size(); i++) {
					elimGroup.remove(new Position(x, errorList.get(i)));
				}
			} else {
				// 消除垂直方向非连续元素
				int y = elimGroup.get(0).getY();
				//elimGroup.showPositions();
				for (int i = 0; i < errorList.size(); i++) {
					elimGroup.remove(new Position(errorList.get(i), y));
					//System.out.println(errorList.get(i) + " " + y);
				}
			}
		}
		elimGroup.showPositions();
		return elimGroup;
	}

	/**
	 * 判断给定水平或垂直元素组是否连续,并返回不连续的元素
	 * 
	 * @param elimGroup
	 *            -消除元素
	 * @param direction
	 *            -方向 （true表示水平，false表示垂直）
	 * @return errorList
	 */
	private static ArrayList<Integer> isSeries(PositionsGroup elimGroup,
			boolean direction) {
		// 存储临时消除点的x坐标或y坐标
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		// 存储不连续的消除点的x坐标或y坐标
		ArrayList<Integer> errorList = new ArrayList<Integer>();

		for (int i = 0; i < elimGroup.size(); i++) {
			if (direction) {
				// 判断水平方向是否连续
				tempList.add(elimGroup.get(i).getY());
			} else {
				// 判断垂直方向是否连续
				tempList.add(elimGroup.get(i).getX());
			}
		}
		
		Collections.sort(tempList, new Comparator<Integer>() {
			@Override
			public int compare(Integer arg1, Integer arg2) {
				return arg1.compareTo(arg2);
			}
		});
//		System.out.println(tempList);
		// 判断前n-2项是否连续
		for (int i = 0; i < tempList.size() - 2; i++) {
			if (tempList.get(i) == tempList.get(i + 1) - 1
					&& tempList.get(i + 1) == tempList.get(i + 2) - 1) {
				continue;
				
			} else {
				if (i >= 1){
					if (tempList.get(i - 1) == tempList.get(i) - 1
							&& tempList.get(i) == tempList.get(i+1) -1) {
						if (i >= 2) {
							if (tempList.get(i - 2) == tempList.get(i - 1) -1
									&& tempList.get(i - 1) == tempList.get(i) -1) {
								continue;
							}
						}
					} else {
						continue;
					}
				}
				errorList.add(i);
			}
		}
		// 判断后2项是否连续
		int temp = tempList.size() - 3;
		if (errorList.contains(tempList.get(temp))) {
			errorList.add(tempList.get(temp + 1));
			errorList.add(tempList.get(temp + 2));
		} else {
			if (temp - 1 > 0) {
				if (errorList.contains(tempList.get(temp - 1))) {
					if (tempList.get(temp) != tempList.get(temp + 1) - 1
							&& tempList.get(temp + 1) != tempList.get(temp + 2)) {
						errorList.add(tempList.get(temp + 1));
						errorList.add(tempList.get(temp + 2));
					}
				}
			}
		}
		System.out.println(errorList);

		return errorList;
	}

	private static boolean isInRow(int row, Position position) {
		return (row == position.getX());
	}

	private static boolean isInColumn(int column, Position position) {
		return (column == position.getY());
	}

	/**
	 * 判断水平组或者垂直组是否与对应组存在相交点坐标，如果有返回相交点；否则返回null
	 * 
	 * @param direction
	 *            -方向（true表示水平，false表示垂直）
	 * @return Position
	 */
	public static Position hasCrossPoint(PositionsGroup elimGroup,
			boolean direction) {
		Position position = null;
		if (direction) {
			// 判断水平方向是否有相交点
			for (int i = 0; i < elimGroup.size(); i++) {
				for (int j = 0; j < verticalList.size(); j++) {
					if (verticalList.get(j).contains(elimGroup.get(i))) {
						position = elimGroup.get(i);
						break;
					}
				}
			}
		} else {
			// 判断垂直方向是否有相交点
			for (int i = 0; i < elimGroup.size(); i++) {
				for (int j = 0; j < horizontalList.size(); j++) {
					if (horizontalList.get(j).contains(elimGroup.get(i))) {
						position = elimGroup.get(i);
						break;
					}
				}
			}
		}
		return position;
	}

}
