package common.gameInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import model.Map;

public class PositionsGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Position> positions;
	

	public PositionsGroup() {
		positions = new ArrayList<Position>();
	}

	public void addPosition(Position p) {
		positions.add(p);
	}

	public void showPositions() {
		Iterator<Position> iterator = positions.iterator();
		while (iterator.hasNext()) {
			iterator.next().showPosition();
			System.out.println();
		}
	}

	public Position get(int i) {
		return this.positions.get(i);
	}

	public boolean equal(PositionsGroup positionsGroup) {
		boolean equal = false;

		if (positionsGroup.size() == this.positions.size()) {
			equal = true;
			for (int i = 0; i < positionsGroup.size(); i++) {
				if (this.positions.contains(positionsGroup.get(i))) {
					continue;
				} else {
					equal = false;
					break;
				}
			}
		}
		return equal;
	}

	public int size() {
		return this.positions.size();
	}

	public PositionsGroup clone() {
		PositionsGroup positionsGroup = new PositionsGroup();
		for (int i = 0; i < this.positions.size(); i++) {
			positionsGroup.addPosition(this.positions.get(i));
		}
		return positionsGroup;
	}

	public boolean contains(Position position) {
		return this.positions.contains(position);
	}

	
	public void remove(Position position) {
		int size = positions.size();
		for (int i = 0; i < size; i++) {
			if (this.positions.get(i).equal(position)) {
				this.positions.remove(i);
				break;
			}
		}
	}

	public void remove(PositionsGroup positionsGroup) {
		for (int i = 0; i < positionsGroup.size(); i++) {
			this.positions.remove(positionsGroup.get(i));
		}
	}

	public boolean hasPosition(Position p) {
		boolean has = false;
		for (Position temp : this.positions) {
			if (temp.equal(p)) {
				has = true;
				break;
			}
		}
		return has;

	}

	public void combine(PositionsGroup positionsGroup) {
		if (positionsGroup != null) {
			for (int i = 0; i < positionsGroup.size(); i++) {
				boolean hasPosition = false;
				for (int j = 0; j < this.positions.size(); j++) {
					if (positionsGroup.get(i).equal(this.positions.get(j))) {
						hasPosition = true;
						break;
					}
				}
				if (hasPosition) {
					continue;
				} else {
					this.positions.add(positionsGroup.get(i));
				}
			}
		}
	}

	
}
