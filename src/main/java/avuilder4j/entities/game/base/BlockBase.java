package avuilder4j.entities.game.base;

import avuilder4j.entities.game.Block;

public class BlockBase extends Block {
	private static final long serialVersionUID = 1458001735434413040L;

	@Override
	public MaterialBase getMaterial() {
		return getMaterial();
	}

	@Override
	public TypeBlockBase getType() {
		return getType();
	}

	@Override
	public String toString() { // TODO ordenar
		return "BlockBase [color=" + color + ", orientation=" + orientation + ", axisX=" + axisX + ", axisY=" + axisY + ", axisZ=" + axisZ + ", index=" + index + ", parent=" + parent + ", getMaterial()=" + getMaterial()
				+ ", getType()=" + getType() + "]";
	}

}
