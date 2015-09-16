package model;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;

public class JudgeAndHint {
	public static PositionsGroup judgeIfDead(int [][] states) {

		PositionsGroup positionsGroup = null;
		for (int i = 0; i < Map.getRows(); i++) {
			for (int j = 0; j < Map.getColumns(); j++) {
				// �жϺ�����������������
				if (j + 1 < Map.getColumns()) {
					if (states[i][j] ==  states[i][j + 1]) {
						if (j + 2 < Map.getColumns()) {
							// �ж���ֱ������
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
						// �ж������û������
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
						// �ж�������û��������
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
				// �ж�������������������
				if (i + 1 < Map.getRows()) {
					if ( states[i][j] ==  states[i + 1][j]) {
						if (i + 2 < Map.getRows()) {
							// �ж����ҷ�����û����ͬ��
							if (j > 0 && j < Map.getColumns() - 1) {
								// ���
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
								// �Ҳ�
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
								// �Ҳ�
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
								// ���
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
						// ���ֻҪ�ж������Ƿ����ܹ�������
						if (j > 0 && j < Map.getColumns() - 1) {
							// ���
							if ( states[i + 1][j - 1] ==  states[i][j]) {
								positionsGroup = new PositionsGroup();
								positionsGroup.addPosition(new Position(i, j));
								positionsGroup.addPosition(new Position(i + 2,
										j));
								positionsGroup.addPosition(new Position(i + 1,
										j - 1));
								break;
							}
							// �Ҳ�
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
							// �Ҳ�
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
							// ���
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
						// ���ж��ϲ���û����ͬ��
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
						// �ж�������û����ͬ��
						if (j > 0 && j < Map.getColumns() - 1) {
							// ��
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
							// ��
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
							// ��
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
							// ��
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
