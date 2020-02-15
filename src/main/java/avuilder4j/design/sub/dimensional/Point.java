package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

/**
 * Represents a point in a Cartesian coordinate system.
 */
public class Point extends V3Generic<Point> implements Serializable {
	private static final long serialVersionUID = -8385581038463928346L;

	public Point() {
		super();
	}

	public Point(Number x, Number y, Number z) {
		super(x, y, z);
	}

	public Point(Number xyz) {
		super(xyz);
	}

	public Point(Point xyz) {
		super(xyz);
	}

	public void sumVector(Vector v) {
		sumV3(v);
	}

	public void subVector(Vector v) {
		subV3(v);
	}

	@Override
	public Point chain() {
		return this;
	}

}
