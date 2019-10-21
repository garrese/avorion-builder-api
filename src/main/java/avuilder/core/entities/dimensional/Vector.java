package avuilder.core.entities.dimensional;

import java.io.Serializable;

public class Vector extends Point implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

	public static Vector deepCopy(Vector p) {
		return new Vector(p.x, p.y, p.z);
	}

	public static Vector pointDiff(Point a, Point b) {
		a.validate();
		b.validate();
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector pointSum(Point a, Point b) {
		a.validate();
		b.validate();
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Vector vectorDiff(Vector a, Vector b) {
		a.validate();
		b.validate();
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector vectorSum(Vector a, Vector b) {
		a.validate();
		b.validate();
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

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

}
