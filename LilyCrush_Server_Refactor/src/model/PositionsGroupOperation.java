package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;

public class PositionsGroupOperation {
	// ���ˮƽ������
	public static ArrayList<PositionsGroup> horizontalList;
	// ��Ŵ�ֱ������
	public static ArrayList<PositionsGroup> verticalList;
//	private static int rows = Map.getRows();
//	private static int columns = Map.getColumns();
	private static int rows = 9;
	private static int columns = 9;
	/**
	 * ������Ԫ��ָ�Ϊ����3����������ˮƽ����ֱ��
	 * 
	 * @param elimGroup
	 *            -����Ԫ��
	 */
	public static void divideGroup(PositionsGroup elimGroup) {
		horizontalList = new ArrayList<PositionsGroup>();
		verticalList = new ArrayList<PositionsGroup>();

		// ����ˮƽ����ָ�������
		for (int i = 0; i < rows; i++) {
			PositionsGroup horizontal = new PositionsGroup();
			for (int j = 0; j < elimGroup.size(); j++) {
				if (isInRow(i, elimGroup.get(j))) {
					horizontal.addPosition(elimGroup.get(j));
				}
			}
			if (horizontal.size() >= 3) {
				// ����ˮƽ����Ԫ����
				horizontal = arrangeGroup(horizontal, true).clone();
				// ˮƽ��������Ԫ�س���3��ʱ���λ����
				if (horizontal.size() >= 3) {
					horizontalList.add(horizontal);
				}
			}
		}

		// ���մ�ֱ����ָ�������
		for (int i = 0; i < columns; i++) {
			PositionsGroup vertical = new PositionsGroup();
			for (int j = 0; j < elimGroup.size(); j++) {
				if (isInColumn(i, elimGroup.get(j))) {
					vertical.addPosition(elimGroup.get(j));
				}
			}
			if (vertical.size() >= 3) {
				// ����ֱ����Ԫ����
				vertical = arrangeGroup(vertical, false).clone();
				// ��ֱ��������Ԫ�س���3��ʱ���λ����
				if (vertical.size() >= 3) {
					verticalList.add(vertical);
				}
			}
		}
		
//		System.out.println("horizontalList.size:" + horizontalList.size());
//		System.out.println("verticalList.size:"+ verticalList.size());
	}

	/**
	 * ��������Ԫ���飬��ɾ��������Ԫ��
	 * 
	 * @param elimGroup
	 *            -����Ԫ����
	 * @param direction
	 *            -����true��ʾˮƽ��false��ʾ��ֱ��
	 * @return
	 */
	private static PositionsGroup arrangeGroup(PositionsGroup elimGroup,
			boolean direction) {
		ArrayList<Integer> errorList = isSeries(elimGroup, direction);
		if (!errorList.isEmpty()) {
			if (direction) {
				// ����ˮƽ���������Ԫ��
				int x = elimGroup.get(0).getX();
				for (int i = 0; i < errorList.size(); i++) {
					elimGroup.remove(new Position(x, errorList.get(i)));
				}
			} else {
				// ������ֱ���������Ԫ��
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
	 * �жϸ���ˮƽ��ֱԪ�����Ƿ�����,�����ز�������Ԫ��
	 * 
	 * @param elimGroup
	 *            -����Ԫ��
	 * @param direction
	 *            -���� ��true��ʾˮƽ��false��ʾ��ֱ��
	 * @return errorList
	 */
	private static ArrayList<Integer> isSeries(PositionsGroup elimGroup,
			boolean direction) {
		// �洢��ʱ�������x�����y����
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		// �洢���������������x�����y����
		ArrayList<Integer> errorList = new ArrayList<Integer>();

		for (int i = 0; i < elimGroup.size(); i++) {
			if (direction) {
				// �ж�ˮƽ�����Ƿ�����
				tempList.add(elimGroup.get(i).getY());
			} else {
				// �жϴ�ֱ�����Ƿ�����
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
		// �ж�ǰn-2���Ƿ�����
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
		// �жϺ�2���Ƿ�����
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
	 * �ж�ˮƽ����ߴ�ֱ���Ƿ����Ӧ������ཻ�����꣬����з����ཻ�㣻���򷵻�null
	 * 
	 * @param direction
	 *            -����true��ʾˮƽ��false��ʾ��ֱ��
	 * @return Position
	 */
	public static Position hasCrossPoint(PositionsGroup elimGroup,
			boolean direction) {
		Position position = null;
		if (direction) {
			// �ж�ˮƽ�����Ƿ����ཻ��
			for (int i = 0; i < elimGroup.size(); i++) {
				for (int j = 0; j < verticalList.size(); j++) {
					if (verticalList.get(j).contains(elimGroup.get(i))) {
						position = elimGroup.get(i);
						break;
					}
				}
			}
		} else {
			// �жϴ�ֱ�����Ƿ����ཻ��
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
