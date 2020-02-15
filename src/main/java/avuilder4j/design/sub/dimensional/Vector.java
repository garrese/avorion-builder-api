package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

public class Vector extends V3Generic<Vector> implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

	public Vector(V3Generic<?> xyz) {
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

}
