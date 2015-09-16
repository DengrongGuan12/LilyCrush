package model;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;

public class CrushState {

		private PositionsGroup verticalPositions;// 竖直方向连续消除的数目

		public PositionsGroup getVerticalPositions() {
			return verticalPositions;
		}

		public PositionsGroup getHorizontalPositions() {
			return horizontalPositions;
		}

		private PositionsGroup horizontalPositions;// 水平方向连续消除的数目

		public CrushState() {
			verticalPositions = new PositionsGroup();
			horizontalPositions = new PositionsGroup();
		}

		public void addVerticalPosition(Position p) {
			verticalPositions.addPosition(p);
		}

		public void addhorizontalPosition(Position p) {
			horizontalPositions.addPosition(p);
		}

		// 清空竖直方向的位置组
		public void clearVerticalPositions() {
			this.verticalPositions = new PositionsGroup();
		}

		// 清空水平方向上的位置组
		public void clearHorizontalPositions() {
			this.horizontalPositions = new PositionsGroup();
		}


}
