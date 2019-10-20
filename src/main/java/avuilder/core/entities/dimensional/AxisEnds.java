package avuilder.core.entities.dimensional;

import java.io.Serializable;

import avuilder.core.error.AvuilderEntityException;
import avuilder.core.error.ACErrors;
import avuilder.core.utils.ACValidations;

/**
 * Represents a line in a single coordinate axis.
 * <p>
 * The line is defined by an upper and a lower point in the coordinate axis.
 */
public class AxisEnds implements Serializable {
	private static final long serialVersionUID = 1186136799589694838L;

	/**
	 * Lower point of the line in the coordinate axis.
	 */
	protected Double lowerEnd;

	/**
	 * Upper point of the line in the coordinate axis.
	 */
	protected Double upperEnd;

	public static final int END_UPPER = 0;

	public static final int END_LOWER = 1;

	public AxisEnds() {
	}

	/**
	 * @param lP the {@link #lowerEnd}.
	 * @param uP the {@link #upperEnd}.
	 */
	public AxisEnds(Double lP, Double uP) {
		setUpperEnd(uP);
		setLowerEnd(lP);
	}

	public static AxisEnds deepCopy(AxisEnds line) {
		AxisEnds l = null;
		if (line != null) {
			l = new AxisEnds();
			l.setLowerEnd(line.getLowerEnd());
			l.setUpperEnd(line.getUpperEnd());
		}
		return line;
	}

	public boolean isDefined() {
		if (lowerEnd != null && upperEnd != null)
			return true;
		else
			return false;
	}

	public void validate() {
		if (!isDefined())
			throw new AvuilderEntityException(ACErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	/**
	 * Calculates the center of the line.
	 * 
	 * @return the center of the line in the coordinate axis.
	 * @throws IllegalStateException if {@link #upperEnd} or {@link #lowerEnd} are null.
	 */
	public Double getCenter() {
		if (isDefined())
			return lowerEnd + (upperEnd - lowerEnd) / 2;
		else
			return null;
	}

	/**
	 * Calculates the length of the line.
	 * 
	 * @return the line length.
	 * @throws IllegalStateException if {@link #upperEnd} or {@link #lowerEnd} are null.
	 */
	public Double getLength() {
		if (isDefined())
			return upperEnd - lowerEnd;
		else
			return null;
	}

	public void setLength(double length) {
		ACValidations.validateLengths(length);

		double center = 0;
		if (isDefined()) {
			center = getCenter();
		}

		upperEnd = center + getHalf();
		lowerEnd = center - getHalf();
	}

	public Double getHalf() {
		if (isDefined()) {
			return getLength() / 2;
		} else {
			return null;
		}
	}

	/**
	 * Gets the {@link #lowerEnd}.
	 * 
	 * @return the {@link #lowerEnd}.
	 */
	public Double getLowerEnd() {
		return lowerEnd;
	}

	/**
	 * Gets the {@link #upperEnd}.
	 * 
	 * @return the {@link #upperEnd}.
	 */
	public Double getUpperEnd() {
		return upperEnd;
	}

	/**
	 * Sets the {@link #lowerEnd}.
	 * 
	 * @param lP the {@link #lowerEnd} to set.
	 * @throws IllegalArgumentException if lP is higher than {@link #upperEnd}
	 */
	public void setLowerEnd(Double lP) {
		if (upperEnd != null && lP != null && upperEnd <= lP)
			throw new IllegalArgumentException("Lower point must be less than upper point.");
		this.lowerEnd = lP;
	}

	/**
	 * Sets the {@link #upperEnd}.
	 * 
	 * @param uP the {@link #upperEnd} to set.
	 * @throws IllegalArgumentException if uP is lower than {@link #lowerEnd}
	 */
	public void setUpperEnd(Double uP) {
		if (uP != null && lowerEnd != null && uP <= lowerEnd)
			throw new IllegalArgumentException("Upper point must be higher than lower point.");
		this.upperEnd = uP;
	}

	public Double getEnd(int end) {
		switch (end) {
		case AxisEnds.END_UPPER:
			return upperEnd;
		case AxisEnds.END_LOWER:
			return lowerEnd;
		default:
			throw new IllegalArgumentException(ACErrors.END_NOT_RECOGNIZED);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lowerEnd == null) ? 0 : lowerEnd.hashCode());
		result = prime * result + ((upperEnd == null) ? 0 : upperEnd.hashCode());
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
		AxisEnds other = (AxisEnds) obj;
		if (lowerEnd == null) {
			if (other.lowerEnd != null)
				return false;
		} else if (!lowerEnd.equals(other.lowerEnd))
			return false;
		if (upperEnd == null) {
			if (other.upperEnd != null)
				return false;
		} else if (!upperEnd.equals(other.upperEnd))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AxisLine [uP=" + upperEnd + ", lP=" + lowerEnd + "]";
	}

}
