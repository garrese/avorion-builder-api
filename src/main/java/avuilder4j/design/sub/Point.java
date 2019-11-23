package avuilder4j.design.sub;

import java.io.Serializable;

import avuilder4j.design.enums.Axis;
import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

/**
 * Represents a point in a Cartesian coordinate system.
 */
public class Point implements Serializable {
	private static final long serialVersionUID = -8385581038463928346L;

	public Double x, y, z;

	public Point() {}

	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Point deepCopy(Point p) {
		return new Point(p.x, p.y, p.z);
	}

	public static Point vectorSum(Point a, Vector b) {
		a.validate();
		b.validate();
		return new Point(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Point vectorSub(Point a, Vector b) {
		a.validate();
		b.validate();
		return new Point(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public boolean isDefined() {
		if (x != null && y != null && z != null) {
			return true;
		} else {
			return false;
		}
	}

	public void validate() {
		if (!isDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	public Double getAxisComponent(Axis axis) {
		switch (axis) {
		case X:
			return x;
		case Y:
			return y;
		case Z:
			return z;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}

	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		result = prime * result + ((z == null) ? 0 : z.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		if (z == null) {
			if (other.z != null)
				return false;
		} else if (!z.equals(other.z))
			return false;
		return true;
	}

}
