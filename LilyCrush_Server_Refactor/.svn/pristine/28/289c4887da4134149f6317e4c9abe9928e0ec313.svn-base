package model.managers;

import model.Map;
import model.PositionsGroupOperation;
import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;

/**
 * ScoreManager用来处理消除游戏中消除得分的相关逻辑，基础的得分为3个100，4个200,5个500，n个（n>5）100n
 * 
 * @author HeZhuang
 * 
 */

public class ScoreManager {
	private int score;
	private boolean hasPropD;
	private static int rows = Map.getRows();
	private static int columns = Map.getColumns();

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public ScoreManager(boolean hasPropD) {
		this.hasPropD = hasPropD;
		this.setScore(0);
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(boolean ifSuper, PositionsGroup elimGroup) {
		int tempScore = calculateScore(elimGroup);
		if (ifSuper) {
			tempScore = tempScore * 2;
		}
		this.score += tempScore;
	}

	public void calculateAllScore() {
		if (this.hasPropD) {
			// 道具D增强得分10%
			this.score = (int)(this.score * 1.1);
		}
	}

	/**
	 * 计算消除分数
	 * 
	 * @param elimGroup
	 *            -消除元组
	 * @return score
	 */
	private int calculateScore(PositionsGroup elimGroup) {
		int scoreTemp = 0;
		// 分割消除元组
		PositionsGroupOperation.divideGroup(elimGroup);
		// 计算水平方向得分
		for (int i = 0; i < PositionsGroupOperation.horizontalList.size(); i++) {
			scoreTemp = scoreTemp
					+ getBaseScore(PositionsGroupOperation.horizontalList.get(i).size());
		}
		// 计算垂直方向得分
		for (int j = 0; j < PositionsGroupOperation.verticalList.size(); j++) {
			scoreTemp = scoreTemp
					+ getBaseScore(PositionsGroupOperation.verticalList.get(j).size());
		}

		return scoreTemp;
	}

	/**
	 * 获取基准分数
	 * 
	 * @param elimNumber
	 *            -消除元组
	 * @return BaseScore
	 */
	private int getBaseScore(int elimNumber) {
		switch (elimNumber) {
		case 3:
			return 100;
		case 4:
			return 200;
		case 5:
			return 500;
		default:
			return (elimNumber * 100);
		}
	}

	public static void main(String[] args) {
		ScoreManager scoreManager = new ScoreManager(false);
		PositionsGroup elimGroup = new PositionsGroup();
		// elimGroup.addPosition(new Position(0, 1));
		// elimGroup.addPosition(new Position(0, 2));
		// elimGroup.addPosition(new Position(0, 3));
		// elimGroup.addPosition(new Position(0, 4));
		// elimGroup.addPosition(new Position(0, 5));
		// elimGroup.addPosition(new Position(1, 1));
		// elimGroup.addPosition(new Position(1, 2));
		// elimGroup.addPosition(new Position(1, 3));
		// elimGroup.addPosition(new Position(1, 4));
		// elimGroup.addPosition(new Position(1, 5));
		// elimGroup.addPosition(new Position(2, 1));
		// elimGroup.addPosition(new Position(2, 2));
		// elimGroup.addPosition(new Position(2, 3));
		// elimGroup.addPosition(new Position(2, 4));
		// elimGroup.addPosition(new Position(2, 5));

		elimGroup.addPosition(new Position(3, 2));
		elimGroup.addPosition(new Position(4, 2));
		elimGroup.addPosition(new Position(5, 2));
		elimGroup.addPosition(new Position(6, 2));

		int score = scoreManager.calculateScore(elimGroup);
		System.out.println(score);
	}

}
