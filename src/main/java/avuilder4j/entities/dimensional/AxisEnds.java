package avuilder4j.entities.dimensional;

import java.io.Serializable;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.utils.AvValidations;

/**
 * Represents a line in a single coordinate axis.
 * <p>
 * The line is defined by an upper and a lower point in the coordinate axis.
 */
public class AxisEnds implements Serializable {

	public static final int END_LOWER = 0;

	public static final int END_UPPER = 1;

	public static final int[] ENDS_LIST = new int[] { END_LOWER, END_UPPER };

	private static final long serialVersionUID = 1186136799589694838L;
	/**
	 * Lower point of the line in the coordinate axis.
	 */
	protected Double lowerEnd;
	/**
	 * Upper point of the line in the coordinate axis.
	 */
	protected Double upperEnd;

	public static AxisEnds deepCopy(AxisEnds line) {
		AxisEnds l = null;
		if (line != null) {
			l = new AxisEnds();
			l.setLowerEnd(line.getLowerEnd());
			l.setUpperEnd(line.getUpperEnd());
		}
		return line;
	}

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

	public void sumToLowerEnd(double x) {
		if (lowerEnd == null)
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		setLowerEnd(lowerEnd + x);
	}

	public void sumToUpperEnd(double x) {
		if (upperEnd == null)
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		setUpperEnd(lowerEnd + x);
	}

	public void sumToEnd(int endId, double x) {
		Double endValue = getEnd(endId);
		if (endValue == null)
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		setEnd(endId, endValue + x);
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

	public void escalateRelative(double ratio) {
		AvValidations.validateRatios(ratio);
		validateAxisEnds();
		upperEnd *= ratio;
		lowerEnd *= ratio;
	}

	public void escalateStatic(double ratio, Integer fixedEndId) {
		AvValidations.validateRatios(ratio);
		validateAxisEnds();
		setLength(getLength() * ratio, fixedEndId);
	}

	/**
	 * Calculates the center of the line.
	 * 
	 * @return the center of the line in the coordinate axis.
	 * @throws IllegalStateException if {@link #upperEnd} or {@link #lowerEnd} are null.
	 */
	public Double getCenter() {
		if (isAxisEndsDefined())
			return lowerEnd + (upperEnd - lowerEnd) / 2;
		else
			return null;
	}

	public Double getEnd(int endId) {
		switch (endId) {
		case AxisEnds.END_UPPER:
			return upperEnd;
		case AxisEnds.END_LOWER:
			return lowerEnd;
		default:
			throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
		}
	}

	public Double getHalf() {
		if (isAxisEndsDefined()) {
			return getLength() / 2;
		} else {
			return null;
		}
	}

	/**
	 * Calculates the length of the line.
	 * 
	 * @return the line length.
	 * @throws IllegalStateException if {@link #upperEnd} or {@link #lowerEnd} are null.
	 */
	public Double getLength() {
		if (isAxisEndsDefined())
			return upperEnd - lowerEnd;
		else
			return null;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lowerEnd == null) ? 0 : lowerEnd.hashCode());
		result = prime * result + ((upperEnd == null) ? 0 : upperEnd.hashCode());
		return result;
	}

	public boolean isAxisEndsDefined() {
		if (lowerEnd != null && upperEnd != null)
			return true;
		else
			return false;
	}

	public void moveCenterByVector(double vector) {
		validateAxisEnds();
		upperEnd += vector;
		lowerEnd += vector;
	}

	public void moveCenterToPoint(double destination) {
		validateAxisEnds();
		double vector = destination - getCenter();
		moveCenterByVector(vector);
	}

	public void setEnd(int endId, Double end) {
		switch (endId) {
		case AxisEnds.END_UPPER:
			setUpperEnd(end);
		case AxisEnds.END_LOWER:
			setLowerEnd(end);
		default:
			throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
		}
	}

	public void setLength(Double length) {
		setLength(length, null);
	}

	public void setLength(Double length, Integer fixedEndId) {
		if (length == null) {
			upperEnd = null;
			lowerEnd = null;
		} else {
			AvValidations.validateLengths(length);

			if (fixedEndId == null) {
				double center = 0;
				if (isAxisEndsDefined()) {
					center = getCenter();
				}
				upperEnd = center + length / 2;
				lowerEnd = center - length / 2;

			} else {
				switch (fixedEndId) {
				case END_UPPER:
					if (upperEnd == null)
						upperEnd = 0.0;
					lowerEnd = upperEnd - length;
					break;
				case END_LOWER:
					if (lowerEnd == null)
						lowerEnd = 0.0;
					upperEnd = lowerEnd + length;
					break;
				default:
					throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
				}
			}
		}
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AxisEnds [length=" + getLength() + " lowerEnd=" + lowerEnd + ", center=" + getCenter() + ", upperEnd="
				+ upperEnd + "]";
	}

	public void validateAxisEnds() {
		if (!isAxisEndsDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

}
