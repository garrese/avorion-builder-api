package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

import avuilder4j.design.enums.End;
import avuilder4j.error.AvErrors;
import avuilder4j.error.AvValidations;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.util.java.Copyable;
import avuilder4j.util.java.Nullable;

/**
 * Represents a line in a single coordinate axis.
 * <p>
 * The line is defined by an upper and a lower point in the coordinate axis.
 */
public class AxisEnds implements Serializable, Copyable<AxisEnds> {
	private static final long serialVersionUID = 1186136799589694838L;

	/**
	 * Lower point of the line in the coordinate axis.
	 */
	protected Double lowerEnd;
	/**
	 * Upper point of the line in the coordinate axis.
	 */
	protected Double upperEnd;

	public AxisEnds() {}

	/**
	 * @param lP the {@link #lowerEnd}.
	 * @param uP the {@link #upperEnd}.
	 */
	public AxisEnds(Number lP, Number uP) {
		setUpperEnd(uP);
		setLowerEnd(lP);
	}

	public static AxisEnds copy(AxisEnds copyFrom, AxisEnds copyTo) {
		if (copyFrom != null && copyTo != null) {
			copyTo.setLowerEnd(copyFrom.getLowerEnd());
			copyTo.setUpperEnd(copyFrom.getUpperEnd());
		}
		return copyTo;
	}

	public AxisEnds cleanEnds() {
		lowerEnd = null;
		upperEnd = null;
		return this;
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

	public AxisEnds escalateRelative(double ratio) {
		AvValidations.ratios(ratio);
		validateAxisEnds();
		upperEnd *= ratio;
		lowerEnd *= ratio;
		return this;
	}

	public AxisEnds escalateStatic(double ratio, End fixedEnd) {
		AvValidations.ratios(ratio);
		validateAxisEnds();
		setLength(getLength() * ratio, fixedEnd);
		return this;
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

	@Override
	public AxisEnds getCopy() { return AxisEnds.copy(this, new AxisEnds()); }

	public Double getEnd(End end) {
		switch (end) {
		case UPPER:
			return upperEnd;
		case LOWER:
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
	public Double getLowerEnd() { return lowerEnd; }

	/**
	 * Gets the {@link #upperEnd}.
	 * 
	 * @return the {@link #upperEnd}.
	 */
	public Double getUpperEnd() { return upperEnd; }

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

	public AxisEnds moveCenterByVector(double vector) {
		validateAxisEnds();
		upperEnd += vector;
		lowerEnd += vector;
		return this;
	}

	public AxisEnds moveCenterToPoint(double destination) {
		validateAxisEnds();
		double vector = destination - getCenter();
		moveCenterByVector(vector);
		return this;
	}

	public void setByEndAndLength(End end, double value, double length) {
		AvValidations.lengths(length);

		switch (end) {
		case UPPER:
			setByUpperEndAndLength(value, length);
			break;
		case LOWER:
			setByLowerEndAndLength(value, length);
			break;
		default:
			throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
		}
	}

	public void setByLowerEndAndLength(double lowerEnd, double length) {
		AvValidations.lengths(length);

		upperEnd = null;
		setLowerEnd(lowerEnd);
		setUpperEnd(lowerEnd + length);
	}

	public void setByUpperEndAndLength(double upperEnd, double length) {
		AvValidations.lengths(length);

		lowerEnd = null;
		setUpperEnd(upperEnd);
		setLowerEnd(upperEnd - length);
	}

	public void setEnd(End end, Number value) {
		switch (end) {
		case UPPER:
			setUpperEnd(value);
			break;
		case LOWER:
			setLowerEnd(value);
			break;
		default:
			throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
		}
	}

	public AxisEnds setLength(Number length) {
		if (length != null) {
			double center = 0;
			if (isAxisEndsDefined()) {
				center = getCenter();
			}
			upperEnd = center + length.doubleValue() / 2;
			lowerEnd = center - length.doubleValue() / 2;
		} else {
			setUpperEnd(null);
			setLowerEnd(null);
		}
		return this;
	}

	public AxisEnds setLength(Number length, End fixedEnd) {
		if (length == null) {
			upperEnd = null;
			lowerEnd = null;
		} else {
			Double len = length.doubleValue();
			AvValidations.lengths(len);

			if (fixedEnd == null) {
				double center = 0;
				if (isAxisEndsDefined()) {
					center = getCenter();
				}
				upperEnd = center + len / 2;
				lowerEnd = center - len / 2;

			} else {
				switch (fixedEnd) {
				case UPPER:
					if (upperEnd == null)
						upperEnd = 0.0;
					lowerEnd = upperEnd - len;
					break;
				case LOWER:
					if (lowerEnd == null)
						lowerEnd = 0.0;
					upperEnd = lowerEnd + len;
					break;
				default:
					throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
				}
			}
		}
		return this;
	}

	/**
	 * Sets the {@link #lowerEnd}.
	 * 
	 * @param lowerEnd the {@link #lowerEnd} to set.
	 * @throws IllegalArgumentException if lP is higher than {@link #upperEnd}
	 */
	public AxisEnds setLowerEnd(Number lowerEnd) {
		if (Nullable.m(() -> getUpperEnd() <= lowerEnd.doubleValue(), false))
			throw new IllegalArgumentException(AvErrors.END_NOT_LOWER);
		this.lowerEnd = Nullable.m(() -> lowerEnd.doubleValue());
		return this;
	}

	/**
	 * Sets the {@link #upperEnd}.
	 * 
	 * @param uP the {@link #upperEnd} to set.
	 * @throws IllegalArgumentException if uP is lower than {@link #lowerEnd}
	 */
	public AxisEnds setUpperEnd(Number upperEnd) {
		if (Nullable.m(() -> upperEnd.doubleValue() <= getLowerEnd(), false))
			throw new IllegalArgumentException(AvErrors.END_NOT_UPPER);
		this.upperEnd = Nullable.m(() -> upperEnd.doubleValue());
		return this;
	}

	public AxisEnds sumToEnd(End end, double value) {
		Double endValue = getEnd(end);
		if (endValue == null)
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		setEnd(end, endValue + value);
		return this;
	}

	public AxisEnds sumToLowerEnd(double value) {
		if (lowerEnd == null)
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		setLowerEnd(lowerEnd + value);
		return this;
	}

	public AxisEnds sumToUpperEnd(double value) {
		if (upperEnd == null)
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		setUpperEnd(lowerEnd + value);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[l=");
		builder.append(lowerEnd);
		builder.append(", u=");
		builder.append(upperEnd);
		builder.append("]");
		return builder.toString();
	}

	public void validateAxisEnds() {
		if (!isAxisEndsDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

}
