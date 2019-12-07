package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

/**
 * Represents a point in a Cartesian coordinate system.
 */
public class Point extends Xyz<Point> implements Serializable {
	private static final long serialVersionUID = -8385581038463928346L;

	public Point() {
		super();
	}

	public Point(double x, double y, double z) {
		super(x, y, z);
	}

	public Point(Double x, Double y, Double z) {
		super(x, y, z);
	}

	public void sumVector(Vector v) {
		sumXyz(v);
	}

	public void subVector(Vector v) {
		subXyz(v);
	}

	@Override
	public Point getCopy() { return new Point().copyXyz(this); }

	@Override
	public Point returnThis() {
		return this;
	}

}
