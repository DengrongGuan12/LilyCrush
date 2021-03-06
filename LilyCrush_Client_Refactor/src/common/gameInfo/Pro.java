package common.gameInfo;

import java.io.Serializable;

import common.gameInfo.Position;
import common.gameInfo.PositionsGroup;
import enums.ProType;
/*
 * 道具类，包含道具类型及其位置以及被使用后产生的效果
 */
public abstract class Pro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Position position;
	protected ProType proType;
	
	public Pro(Position position, ProType proType) {
		this.position = position;
		this.proType = proType;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public ProType getProType() {
		return this.proType;
	}
	
	public void setProtype(ProType proType) {
		this.proType = proType;
	}
	
	abstract public PositionsGroup usingPro();

}
