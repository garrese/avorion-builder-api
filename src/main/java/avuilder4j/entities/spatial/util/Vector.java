package avuilder4j.entities.spatial.util;

import java.io.Serializable;

public class Vector extends Point implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

	public static Vector deepCopy(Vector p) {
		return new Vector(p.x, p.y, p.z);
	}

	public static Vector pointDiff(Point a, Point b) {
		a.validatePoint();
		b.validatePoint();
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector pointSum(Point a, Point b) {
		a.validatePoint();
		b.validatePoint();
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Vector vectorDiff(Vector a, Vector b) {
		a.validatePoint();
		b.validatePoint();
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector vectorSum(Vector a, Vector b) {
		a.validatePoint();
		b.validatePoint();
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public Vector() {
		super();
	}

	public Vector(Double x, Double y, Double z) {
		super(x, y, z);
	}

	public Vector(double x, double y, double z) {
		super(x, y, z);
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
