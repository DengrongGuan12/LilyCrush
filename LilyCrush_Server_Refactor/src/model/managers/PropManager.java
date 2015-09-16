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
	 * �жϸ���������Ԫ���Ƿ�Ϊ����,����ǵ��ߣ��򷵻ص����ࣻ���򷵻�null
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
	 * �жϸ��������Ƿ�Ϊ���ߣ�������򽫵��ߵ������趨ΪtoPosition
	 * 
	 * @param fromPosition
	 *            -������������
	 * @param toPosition
	 *            -�����ƶ�����
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
	 * ʹ�õ��ߣ��������Ԫ��Ϊ�����򷵻�����Ԫ�鲢����õ��ߣ����򷵻�null
	 * 
	 * @param position
	 * @return PositionsGroup-��������Ԫ��
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
	 * ��Ϸ���Զ�ʹ������Ԫ���еĵ���
	 * 
	 * @param elimGroup
	 * @return positionGroup-ʹ�õ��ߺ������Ԫ��
	 */
	public PositionsGroup usePro(PositionsGroup elimGroup) {
		PositionsGroup positionsGroup = elimGroup.clone();
		while (true) {
			for (int i = 0; i < elimGroup.size(); i++) {
				positionsGroup.combine(usePro(elimGroup.get(i)));
			}

			// ���elimGroup��û�е��ߣ���û�в����µ�����Ԫ�أ�break���������ѭ��
			if (positionsGroup.equal(elimGroup)) {
				break;
			} else {
				elimGroup = positionsGroup.clone();
			}
		}
		return positionsGroup;
	}

	/**
	 * ��Ϸ����ʱ�Զ�ʹ�����е���
	 * 
	 * @return positionsGroup-������Ԫ��
	 */
	public PositionsGroup useAllPro() {
		PositionsGroup positionsGroup = new PositionsGroup();
		for (Pro pro : pros) {
			positionsGroup.combine(pro.usingPro());
		}
		return positionsGroup;
	}

	/*
	 * ���ɵ���A�������ǵ�ǰ������λ��,�����ض�Ӧ���ɵ��ߵ�����
	 */
	private PositionsGroup generateProA(PositionsGroup elimGroup) {
		PositionsGroup positionsGroup = new PositionsGroup();

		PositionsGroupOperation.divideGroup(elimGroup);
		System.out.println("horizontallist.size:"
				+ PositionsGroupOperation.horizontalList.size());
		System.out.println("verticalList.size:"
				+ PositionsGroupOperation.verticalList.size());
		// ���ˮƽ�����Ƿ������ɵ���A
		for (int i = 0; i < PositionsGroupOperation.horizontalList.size(); i++) {
			Position position = PositionsGroupOperation.hasCrossPoint(
					PositionsGroupOperation.horizontalList.get(i), true);
			if (position != null) {
				// ��������ཻ�㣬���ཻ��Ϊ��������A������
				if (isPro(position) == null){
					pros.add(new ProA(position));
					positionsGroup.addPosition(position);
				}		
			} else {
				// ����������ཻ�㣬�����ѡȡ���е�����λ��Ϊ����A������
				if (PositionsGroupOperation.horizontalList.get(i).size() == 4) {
					position = PositionsGroupOperation.horizontalList.get(i).get(0);
					if (isPro(position) == null) {
						pros.add(new ProA(position));
						positionsGroup.addPosition(position);
					}
				}
			}
		}
		// ��鴹ֱ�����Ƿ������ɵ���B
		for (int j = 0; j < PositionsGroupOperation.verticalList.size(); j++) {
			if (PositionsGroupOperation.verticalList.get(j).size() == 4) {
				// ���ѡȡ���е�����λ��Ϊ����A������(�����ཻ������֮ǰ�Ѿ����ǣ����ﲻ���ظ�����)
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
	 * ���ɵ���B����Ҫ�޸ĵ�ͼ�������ض�Ӧ���ɵ��ߵ�����
	 */
	private PositionsGroup generateProB(PositionsGroup elimGroup,Map map) {
		PositionsGroup positionsGroup = new PositionsGroup();

		PositionsGroupOperation.divideGroup(elimGroup);
		// ���ˮƽ�����Ƿ������ɵ���B
		for (int i = 0; i < PositionsGroupOperation.horizontalList.size(); i++) {
			if (PositionsGroupOperation.horizontalList.get(i).size() >= 5) {
				// ���ѡȡ���е�����λ��Ϊ����B������
				Position position = PositionsGroupOperation.horizontalList.get(i).get(0);
				if (isPro(position) == null) {
					map.changestate(position, 5);
					pros.add(new ProB(position));
					positionsGroup.addPosition(position);
				}
			}
		}
		// ��鴹ֱ�����Ƿ������ɵ���B
		for (int j = 0; j < PositionsGroupOperation.verticalList.size(); j++) {
			if (PositionsGroupOperation.verticalList.get(j).size() >= 5) {
				// ���ѡȡ���е�����λ��Ϊ����B������
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
	 * ���ɵ��ߣ���Ҫ�������ж����ɺ��ֵ���,���������ɵ��ߵĶ�Ӧ����
	 */
	public PositionsGroup generatePro(PositionsGroup positionsGroup,Map map) {
		PositionsGroup proGroup = new PositionsGroup();
		proGroup.combine(this.generateProA(positionsGroup));
		proGroup.combine(this.generateProB(positionsGroup,map));
		return proGroup;
	}

}
