package common.gameInfo;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;
import common.gameInfo.Pro;
import enums.ProType;

public class ProB extends Pro{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows = 9;
	private int columns = 9;
	
	public ProB(Position position) {
		super(position, ProType.ProB);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 使用道具B，消除包括道具B横竖两排
	 */
	@Override
	public PositionsGroup usingPro() {
		PositionsGroup positionsGroup = new PositionsGroup();
		int x = this.position.getX();
		int y = this.position.getY();
		for (int i = 0; i < this.columns; i++) {
			positionsGroup.addPosition(new Position(x, i));
		}
		for (int j = 0; j < this.rows; j++) {
			if (j == x) {
				continue;
			}
			positionsGroup.addPosition(new Position(j, y));
		}
		return positionsGroup;
	}

}
