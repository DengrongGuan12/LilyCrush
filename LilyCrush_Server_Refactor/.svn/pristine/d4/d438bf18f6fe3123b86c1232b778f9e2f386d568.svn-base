package model.managers;

import java.util.ArrayList;

import model.Map;
import model.PositionsGroupOperation;
import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;
import common.gameInfo.Pro;
import common.gameInfo.ProA;
import common.gameInfo.ProB;
import enums.ProType;

public class PropManager {
	private ArrayList<Pro> pros = new ArrayList<Pro>();
	public ArrayList<Pro> getPros() {
		return pros;
	}

	public void setPros(ArrayList<Pro> pros) {
		this.pros = pros;
	}

	public PropManager() {
		super();
//		this.game = game;
	}

	public void addPro(Position position, ProType proType) {
		if (proType == ProType.ProA) {
			pros.add(new ProA(position));
		} 
		else if (proType == ProType.ProB) {
			pros.add(new ProB(position));
		}
	}
	
	/**
	 * 判断给定的消除元素是否为道具,如果是道具，则返回道具类；否则返回null
	 * 
	 * @param position
	 * @return
	 */
	public Pro isPro(Position position) {
		for (int i = 0; i < pros.size(); i++) {
			if (pros.get(i).getPosition().equal(position)) {
				return pros.get(i);
			}
		}
		return null;
	}

	/**
	 * 判断给定坐标是否为道具，如果是则将道具的坐标设定为toPosition
	 * 
	 * @param fromPosition
	 *            -给定道具坐标
	 * @param toPosition
	 *            -道具移动坐标
	 */
	public void changeProPositioin(Position fromPosition, Position toPosition) {
		Pro pro = isPro(fromPosition);
		if (pro != null) {
			pro.setPosition(toPosition);
		}
	}
	
	public void swapProPosition(Position p1, Position p2) {
		Pro pro1 = isPro(p1);
		Pro pro2 = isPro(p2);
		
		if (pro1 != null) {
			if (pro2 != null) {
				pro1.setPosition(p2);
				pro2.setPosition(p1);
			} else {
				pro1.setPosition(p2);
			}
		} 
		else {
			if (pro2 != null) {
				pro2.setPosition(p1);
			}
		}
	}

	/**
	 * 使用道具，如果给定元素为道具则返回消除元组并清除该道具，否则返回null
	 * 
	 * @param position
	 * @return PositionsGroup-新增消除元组
	 */
	public PositionsGroup usePro(Position position) {
		PositionsGroup positionsGroup = new PositionsGroup();

		for (int i = 0; i < pros.size(); i++) {
			if (pros.get(i).getPosition().equal(position)) {
				positionsGroup = pros.get(i).usingPro();
				pros.remove(i);
				return positionsGroup;
			}
		}
		return null;
	}

	/**
	 * 游戏中自动使用消除元组中的道具
	 * 
	 * @param elimGroup
	 * @return positionGroup-使用道具后的消除元组
	 */
	public PositionsGroup usePro(PositionsGroup elimGroup) {
		PositionsGroup positionsGroup = elimGroup.clone();
		while (true) {
			for (int i = 0; i < elimGroup.size(); i++) {
				positionsGroup.combine(usePro(elimGroup.get(i)));
			}

			// 如果elimGroup中没有道具，即没有产生新的消除元素，break；否则继续循环
			if (positionsGroup.equal(elimGroup)) {
				break;
			} else {
				elimGroup = positionsGroup.clone();
			}
		}
		return positionsGroup;
	}

	/**
	 * 游戏结束时自动使用所有道具
	 * 
	 * @return positionsGroup-消除单元组
	 */
	public PositionsGroup useAllPro() {
		PositionsGroup positionsGroup = new PositionsGroup();
		for (Pro pro : pros) {
			positionsGroup.combine(pro.usingPro());
		}
		return positionsGroup;
	}

	/*
	 * 生成道具A，参数是当前消除的位置,并返回对应生成道具的坐标
	 */
	private PositionsGroup generateProA(PositionsGroup elimGroup) {
		PositionsGroup positionsGroup = new PositionsGroup();

		PositionsGroupOperation.divideGroup(elimGroup);
		System.out.println("horizontallist.size:"
				+ PositionsGroupOperation.horizontalList.size());
		System.out.println("verticalList.size:"
				+ PositionsGroupOperation.verticalList.size());
		// 检查水平方向是否有生成道具A
		for (int i = 0; i < PositionsGroupOperation.horizontalList.size(); i++) {
			Position position = PositionsGroupOperation.hasCrossPoint(
					PositionsGroupOperation.horizontalList.get(i), true);
			if (position != null) {
				// 如果存在相交点，则相交点为生产道具A的坐标
				if (isPro(position) == null){
					pros.add(new ProA(position));
					positionsGroup.addPosition(position);
				}		
			} else {
				// 如果不存在相交点，则随机选取该行的消除位置为道具A的坐标
				if (PositionsGroupOperation.horizontalList.get(i).size() == 4) {
					position = PositionsGroupOperation.horizontalList.get(i).get(0);
					if (isPro(position) == null) {
						pros.add(new ProA(position));
						positionsGroup.addPosition(position);
					}
				}
			}
		}
		// 检查垂直方向是否有生成道具B
		for (int j = 0; j < PositionsGroupOperation.verticalList.size(); j++) {
			if (PositionsGroupOperation.verticalList.get(j).size() == 4) {
				// 随机选取该列的消除位置为道具A的坐标(由于相交点的情况之前已经考虑，这里不需重复考虑)
				Position position = PositionsGroupOperation.verticalList.get(j).get(0);
				if (isPro(position) == null) {
					pros.add(new ProA(position));
					positionsGroup.addPosition(position);
				}
			}
		}

		return positionsGroup;
	}

	/*
	 * 生成道具B，需要修改地图，并返回对应生成道具的坐标
	 */
	private PositionsGroup generateProB(PositionsGroup elimGroup,Map map) {
		PositionsGroup positionsGroup = new PositionsGroup();

		PositionsGroupOperation.divideGroup(elimGroup);
		// 检查水平方向是否有生成道具B
		for (int i = 0; i < PositionsGroupOperation.horizontalList.size(); i++) {
			if (PositionsGroupOperation.horizontalList.get(i).size() >= 5) {
				// 随机选取该行的消除位置为道具B的坐标
				Position position = PositionsGroupOperation.horizontalList.get(i).get(0);
				if (isPro(position) == null) {
					map.changestate(position, 5);
					pros.add(new ProB(position));
					positionsGroup.addPosition(position);
				}
			}
		}
		// 检查垂直方向是否有生成道具B
		for (int j = 0; j < PositionsGroupOperation.verticalList.size(); j++) {
			if (PositionsGroupOperation.verticalList.get(j).size() >= 5) {
				// 随机选取该列的消除位置为道具B的坐标
				Position position = PositionsGroupOperation.verticalList.get(j).get(0);
				if (isPro(position) == null) {
					map.changestate(position, 5);
					pros.add(new ProB(position));
					positionsGroup.addPosition(position);
				}
			}
		}

		return positionsGroup;
	}

	/*
	 * 生成道具，需要在这里判断生成何种道具,并返回生成道具的对应坐标
	 */
	public PositionsGroup generatePro(PositionsGroup positionsGroup,Map map) {
		PositionsGroup proGroup = new PositionsGroup();
		proGroup.combine(this.generateProA(positionsGroup));
		proGroup.combine(this.generateProB(positionsGroup,map));
		return proGroup;
	}

}
