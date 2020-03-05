package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

public class Vector extends Xyz<Vector> implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

	public Vector(Xyz<?> xyz) {
		super(xyz);
	}

	public Vector() {
		super();
	}

	public Vector(Number x, Number y, Number z) {
		super(x, y, z);
	}

	public Vector(Number xyz) {
		super(xyz);
	}

	public Vector(Vector xyz) {
		super(xyz);
	}

	@Override
	public Vector chain() {
		return this;
	}

}
