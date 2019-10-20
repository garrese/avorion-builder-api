package avuilder.core.entities.dimensional;

import java.io.Serializable;

public class Vector extends Point implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

	public Vector() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(Double x, Double y, Double z) {
		super(x, y, z);
	}

	public static Vector deepCopy(Vector p) {
		return new Vector(p.x, p.y, p.z);
	}

	public static Vector difference(Vector a, Vector b) {
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector sum(Vector a, Vector b) {
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

}
