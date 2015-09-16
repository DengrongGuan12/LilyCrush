package model;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;

public class JudgeAndHint {
	public static PositionsGroup judgeIfDead(int [][] states) {

		PositionsGroup positionsGroup = null;
		for (int i = 0; i < Map.getRows(); i++) {
			for (int j = 0; j < Map.getColumns(); j++) {
				// 判断横向连续的三个坐标
				if (j + 1 < Map.getColumns()) {
					if (states[i][j] ==  states[i][j + 1]) {
						if (j + 2 < Map.getColumns()) {
							// 判断竖直方向上
							if (i > 0 && i < Map.getRows() - 1) {
								if ( states[i][j] ==  states[i - 1][j + 2]) {
									positionsGroup = new PositionsGroup();
									positionsGroup.addPosition(new Position(i,
											j));
									positionsGroup.addPosition(new Position(i,
											j + 1));
									positionsGroup.addPosition(new Position(
											i - 1, j + 2));
									break;
								}
								if ( states[i][j] ==  states[i + 1][j + 2]) {
									positionsGroup = new PositionsGroup();
									positionsGroup.addPosition(new Position(i,
											j));
									positionsGroup.addPosition(new Position(i,
											j + 1));
									positionsGroup.addPosition(new Position(
											i + 1, j + 2));
									break;
								}
							} else if (i == 0) {
								if ( states[i][j] ==  states[i + 1][j + 2]) {
									positionsGroup = new PositionsGroup();
									positionsGroup.addPosition(new Position(i,
											j));
									positionsGroup.addPosition(new Position(i,
											j + 1));
									positionsGroup.addPosition(new Position(
											i + 1, j + 2));
									break;
								}
							} else {
								if ( states[i][j] ==  states[i - 1][j + 2]) {
									positionsGroup = new PositionsGroup();
									positionsGroup.addPosition(new Position(i,
											j));
									positionsGroup.addPosition(new Position(i,
											j + 1));
									positionsGroup.addPosition(new Position(
											i - 1, j + 2));
									break;
								}
							}
						}
						if (j + 3 < Map.getColumns()) {
							if ( states[i][j + 3] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i,
										j + 1));
								positionsGroup.addPosition(new Position(i,
										j + 3));
								break;

							}
						}
					}
				}
				if (j + 2 < Map.getColumns()) {
					if ( states[i][j] ==  states[i][j + 2]) {
						if (i > 0 && i < Map.getRows() - 1) {
							if ( states[i - 1][j + 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i - 1,
										j + 1));
								break;
							}
							if ( states[i + 1][j + 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i + 1,
										j + 1));
								break;
							}
						} else if (i == 0) {
							if ( states[i + 1][j + 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i + 1,
										j + 1));
								break;
							}
						} else {
							if ( states[i - 1][j + 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i - 1,
										j + 1));
								break;
							}
						}
					}
					if ( states[i][j + 1] ==  states[i][j + 2]) {
						// 判断左侧有没有消除
						if (j != 0) {
							if ( states[i][j - 1] ==  states[i][j + 1]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i,
										j + 1));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i,
										j - 1));
								break;
							}

						}
						// 判断上下有没有消除的
						if (i > 0 && i < Map.getRows() - 1) {
							if ( states[i - 1][j] ==  states[i][j + 1]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i,
										j + 1));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i - 1,
										j));
								break;
							}
							if ( states[i + 1][j] ==  states[i][j + 1]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i,
										j + 1));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i + 1,
										j));
								break;
							}
						} else if (i == 0) {
							if ( states[i + 1][j] ==  states[i][j + 1]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i,
										j + 1));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i + 1,
										j));
								break;
							}
						} else {
							if ( states[i - 1][j] ==  states[i][j + 1]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i,
										j + 1));
								positionsGroup.addPosition(new Position(i,
										j + 2));
								positionsGroup.addPosition(new Position(i - 1,
										j));
								break;
							}
						}
					}

				}
				// 判断纵向连续的三个坐标
				if (i + 1 < Map.getRows()) {
					if ( states[i][j] ==  states[i + 1][j]) {
						if (i + 2 < Map.getRows()) {
							// 判断左右方向有没有相同的
							if (j > 0 && j < Map.getColumns() - 1) {
								// 左侧
								if ( states[i][j] ==  states[i + 2][j - 1]) {
									positionsGroup = new PositionsGroup();
									positionsGroup.addPosition(new Position(i,
											j));
									positionsGroup.addPosition(new Position(
											i + 1, j));
									positionsGroup.addPosition(new Position(
											i + 2, j - 1));
									break;
								}
								// 右侧
								if ( states[i][j] ==  states[i + 2][j + 1]) {
									positionsGroup = new PositionsGroup();
									positionsGroup.addPosition(new Position(i,
											j));
									positionsGroup.addPosition(new Position(
											i + 1, j));
									positionsGroup.addPosition(new Position(
											i + 2, j + 1));
									break;
								}
							} else if (j == 0) {
								// 右侧
								if ( states[i][j] ==  states[i + 2][j + 1]) {
									positionsGroup = new PositionsGroup();
									positionsGroup.addPosition(new Position(i,
											j));
									positionsGroup.addPosition(new Position(
											i + 1, j));
									positionsGroup.addPosition(new Position(
											i + 2, j + 1));
									break;
								}
							} else {
								// 左侧
								if ( states[i][j] ==  states[i + 2][j - 1]) {
									positionsGroup = new PositionsGroup();
									positionsGroup.addPosition(new Position(i,
											j));
									positionsGroup.addPosition(new Position(
											i + 1, j));
									positionsGroup.addPosition(new Position(
											i + 2, j - 1));
									break;
								}
							}
						}
						if (i + 3 < Map.getRows()) {
							if ( states[i + 3][j] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i + 1,
										j));
								positionsGroup.addPosition(new Position(i + 3,
										j));
								break;

							}
						}
					}
				}
				if (i + 2 < Map.getRows()) {
					if ( states[i][j] ==  states[i + 2][j]) {
						// 这个只要判断左右是否有能够消除的
						if (j > 0 && j < Map.getColumns() - 1) {
							// 左侧
							if ( states[i + 1][j - 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i + 1,
										j - 1));
								break;
							}
							// 右侧
							if ( states[i + 1][j + 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i + 1,
										j + 1));
								break;
							}
						} else if (j == 0) {
							// 右侧
							if ( states[i + 1][j + 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i + 1,
										j + 1));
								break;
							}
						} else {
							// 左侧
							if ( states[i + 1][j - 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i + 1,
										j - 1));
								break;
							}
						}
					}
					if ( states[i + 1][j] ==  states[i + 2][j]) {
						// 先判断上侧有没有相同的
						if (i != 0) {
							if ( states[i - 1][j] ==  states[i + 1][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i + 1,
										j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i - 1,
										j));
								break;
							}

						}
						// 判断左右有没有相同的
						if (j > 0 && j < Map.getColumns() - 1) {
							// 左
							if ( states[i][j - 1] ==  states[i + 1][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i + 1,
										j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i,
										j - 1));
								break;
							}
							// 右
							if ( states[i][j + 1] ==  states[i + 1][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i + 1,
										j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i,
										j + 1));
								break;
							}
						} else if (j == 0) {
							// 右
							if ( states[i][j + 1] ==  states[i + 1][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i + 1,
										j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i,
										j + 1));
								break;
							}
						} else {
							// 左
							if ( states[i][j - 1] ==  states[i + 1][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i + 1,
										j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i,
										j - 1));
								break;
							}
						}

					}
				}
			}
			if (positionsGroup != null) {
				break;
			}
		}
		return positionsGroup;

	}

}
