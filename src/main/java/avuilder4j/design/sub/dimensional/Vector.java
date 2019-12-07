package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

public class Vector extends Xyz<Vector> implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

	public Vector() {
		super();
	}

	public Vector(double x, double y, double z) {
		super(x, y, z);
	}

	public Vector(Double x, Double y, Double z) {
		super(x, y, z);
	}

	public static Vector pointDiff(Point a, Point b) {
		return new Vector().copyXyz(a.getXyzSub(b));
	}

	public static Vector pointSum(Point a, Point b) {
		return new Vector().copyXyz(a.getXyzSum(b));
	}

	public static Vector vectorDiff(Vector a, Vector b) {
		return new Vector().copyXyz(a.getXyzSub(b));
	}

	public static Vector vectorSum(Vector a, Vector b) {
		return new Vector().copyXyz(a.getXyzSum(b));
	}

	@Override
	public Vector getCopy() { return new Vector().copyXyz(this); }

	@Override
	public Vector returnThis() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vector [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append("]");
		return builder.toString();
	}

}
