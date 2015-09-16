package common.gameInfo;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;
import common.gameInfo.Pro;
import enums.ProType;

public class ProA extends Pro{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows = 9;
	private int columns = 9;
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public ProA(Position position) {
		super(position, ProType.ProA);
	}

	/*
	 * 使用道具A，消除包括道具A在内的周围9个消除单元
	 */
	@Override
	public PositionsGroup usingPro() {
		PositionsGroup positionsGroup = new PositionsGroup();
		int x = this.position.getX();
		int y = this.position.getY();
		//上
		if (x - 1 >= 0) {
			if (y - 1 >= 0) {
				positionsGroup.addPosition(new Position(x - 1, y - 1));
			}
			positionsGroup.addPosition(new Position(x - 1, y));
			if (y + 1 < this.getRows()) {
				positionsGroup.addPosition(new Position(x -1, y + 1));
			}
		}
		
		//中
		if (y - 1 >= 0) {
			positionsGroup.addPosition(new Position(x, y - 1));
		}
		if (y + 1 < this.getRows()) {
			positionsGroup.addPosition(new Position(x, y + 1));
		}
		
		//下
		if (x + 1 < this.getRows()) {
			if (y - 1 >= 0) {
				positionsGroup.addPosition(new Position(x + 1, y - 1));
			}
			positionsGroup.addPosition(new Position(x + 1, y));
			if (y + 1 < this.getRows()) {
				positionsGroup.addPosition(new Position(x + 1, y + 1));
			}
		}
		
		return positionsGroup;
	}
	
}
