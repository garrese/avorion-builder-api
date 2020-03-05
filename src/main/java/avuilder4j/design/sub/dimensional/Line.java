package avuilder4j.design.sub.dimensional;

import java.io.Serializable;
import java.util.Objects;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

public class Line implements Serializable {
	private static final long serialVersionUID = -1723294270396933910L;

	private Point p1;
	private Point p2;

	public Line() {}

	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public boolean isLineDefined() {
		if (p1 != null && p2 != null && p1.isXyzDefined() && p2.isXyzDefined())
			return true;
		else
			return false;
	}

	public void validateLine() {
		if (!isLineDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	public Double getLength() {
		if (!isLineDefined())
			return null;
		else
			return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) + Math.pow(p1.z - p2.z, 2));
	}

	public Point getCenter() {
		if (!isLineDefined()) {
			return null;
		} else {
			Point p = new Point();
			p.x = (p1.x + p2.x) / 2;
			p.y = (p1.y + p2.y) / 2;
			p.z = (p1.z + p2.z) / 2;
			return p;
		}
	}

	/**
	 * Gets the {@link #p1}.
	 * 
	 * @return the {@link #p1}.
	 */
	public Point getP1() { return p1; }

	/**
	 * Sets the {@link #p1}.
	 * 
	 * @param p1 the {@link #p1} to set.
	 */
	public void setP1(Point p1) { this.p1 = p1; }

	/**
	 * Gets the {@link #p2}.
	 * 
	 * @return the {@link #p2}.
	 */
	public Point getP2() { return p2; }

	/**
	 * Sets the {@link #p2}.
	 * 
	 * @param p2 the {@link #p2} to set.
	 */
	public void setP2(Point p2) { this.p2 = p2; }

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Line [p1=" + p1 + ", p2=" + p2 + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(p1, p2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Line))
			return false;
		Line other = (Line) obj;
		return Objects.equals(p1, other.p1) && Objects.equals(p2, other.p2);
	}

}
