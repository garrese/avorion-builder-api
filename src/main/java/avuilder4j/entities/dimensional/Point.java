package avuilder4j.entities.dimensional;

import java.io.Serializable;

import avuilder4j.error.ACErrors;
import avuilder4j.error.AvuilderEntityException;
import avuilder4j.utils.AvK;
import avuilder4j.utils.AvValidations;

/**
 * Represents a point in a Cartesian coordinate system.
 */
public class Point implements Serializable {
	private static final long serialVersionUID = -8385581038463928346L;

	public Double x, y, z;

	public Point() {
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Point deepCopy(Point p) {
		return new Point(p.x, p.y, p.z);
	}

	public static Point vectorSum(Point a, Vector b) {
		a.validatePoint();
		b.validatePoint();
		return new Point(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Point vectorSub(Point a, Vector b) {
		a.validatePoint();
		b.validatePoint();
		return new Point(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public boolean isPointDefined() {
		if (x != null && y != null && z != null) {
			return true;
		} else {
			return false;
		}
	}

	public void validatePoint() {
		if (!isPointDefined())
			throw new AvuilderEntityException(ACErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	public Double getAxisComponent(int axisId) {
		AvValidations.validateAxesExistance(axisId);
		switch (axisId) {
		case AvK.AXIS_X:
			return x;
		case AvK.AXIS_Y:
			return y;
		case AvK.AXIS_Z:
			return z;
		default:
			throw new IllegalArgumentException(ACErrors.AXIS_NOT_RECOGNIZED);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", z=" + z + "]";
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
