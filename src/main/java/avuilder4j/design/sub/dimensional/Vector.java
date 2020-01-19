package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

public class Vector extends Xyz<Vector> implements Serializable {
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
		return new Vector().copyXyz(a).subXyz(b);
	}

	public static Vector pointSum(Point a, Point b) {
		return new Vector().copyXyz(a).sumXyz(b);
	}

	public static Vector vectorDiff(Vector a, Vector b) {
		return new Vector(a).subXyz(b);
	}

	public static Vector vectorSum(Vector a, Vector b) {
		return new Vector(a).sumXyz(b);
	}

	@Override
	public Vector chain() {
		return this;
	}

}
