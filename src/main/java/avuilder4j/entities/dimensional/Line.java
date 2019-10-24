package avuilder4j.entities.dimensional;

import java.io.Serializable;

import avuilder4j.error.ACErrors;
import avuilder4j.error.AvuilderEntityException;

public class Line implements Serializable {
	private static final long serialVersionUID = -1723294270396933910L;

	private Point p1;
	private Point p2;

	public Line() {
	}

	/**
	 * @param p1 the {@link #p1}
	 * @param p2 the {@link #p2}
	 */
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public boolean isLineDefined() {
		if (p1 != null && p2 != null && p1.isPointDefined() && p2.isPointDefined())
			return true;
		else
			return false;
	}

	public void validateLine() {
		if (!isLineDefined())
			throw new AvuilderEntityException(ACErrors.NOT_SUFFICIENTLY_DEFINED);
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
	public Point getP1() {
		return p1;
	}

	/**
	 * Sets the {@link #p1}.
	 * 
	 * @param p1 the {@link #p1} to set.
	 */
	public void setP1(Point p1) {
		this.p1 = p1;
	}

	/**
	 * Gets the {@link #p2}.
	 * 
	 * @return the {@link #p2}.
	 */
	public Point getP2() {
		return p2;
	}

	/**
	 * Sets the {@link #p2}.
	 * 
	 * @param p2 the {@link #p2} to set.
	 */
	public void setP2(Point p2) {
		this.p2 = p2;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Line [p1=" + p1 + ", p2=" + p2 + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}

}
