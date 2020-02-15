package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

public class Vector extends V3Generic<Vector> implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

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

	public static Vector pointDiff(Point a, Point b) {
		return new Vector().copyV3(a).subV3(b);
	}

	public static Vector pointSum(Point a, Point b) {
		return new Vector().copyV3(a).sumV3(b);
	}

	public static Vector vectorDiff(Vector a, Vector b) {
		return new Vector(a).subV3(b);
	}

	public static Vector vectorSum(Vector a, Vector b) {
		return new Vector(a).sumV3(b);
	}

	@Override
	public Vector chain() {
		return this;
	}

}
