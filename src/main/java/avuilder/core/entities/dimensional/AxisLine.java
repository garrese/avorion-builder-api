package avuilder.core.entities.dimensional;

import java.io.Serializable;

import avuilder.core.error.AvuilderCoreRuntimeException;
import avuilder.core.error.Errors;

/**
 * Represents a line in a single coordinate axis.
 * <p>
 * The line is defined by an upper and a lower point in the coordinate axis.
 */
public class AxisLine implements Serializable {
	private static final long serialVersionUID = 1186136799589694838L;

	/**
	 * Lower point of the line in the coordinate axis.
	 */
	protected Double lP;

	/**
	 * Upper point of the line in the coordinate axis.
	 */
	protected Double uP;

	public AxisLine() {
	}

	/**
	 * @param lP the {@link #lP}.
	 * @param uP the {@link #uP}.
	 */
	public AxisLine(Double lP, Double uP) {
		setUP(uP);
		setLP(lP);
	}

	public static AxisLine deepCopy(AxisLine line) {
		AxisLine l = null;
		if (line != null) {
			l = new AxisLine();
			l.setLP(line.getLP());
			l.setUP(line.getUP());
		}
		return line;
	}

	/**
	 * Calculates the center of the line.
	 * 
	 * @return the center of the line in the coordinate axis.
	 * @throws IllegalStateException if {@link #uP} or {@link #lP} are null.
	 */
	public Double getCenter() {
		double l;
		try {
			l = lP + (uP - lP) / 2;
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(Errors.NOT_SUFFICIENTLY_DEFINED, e);
		}
		return l;
	}

	/**
	 * Calculates the length of the line.
	 * 
	 * @return the line length.
	 * @throws IllegalStateException if {@link #uP} or {@link #lP} are null.
	 */
	public Double getLength() {
		double l;
		try {
			l = uP -lP;
		}catch(Exception e) {
			throw new AvuilderCoreRuntimeException(Errors.NOT_SUFFICIENTLY_DEFINED, e);
		}
		return l;
	}

	public static boolean isDefined(AxisLine axisLine) {
		if (axisLine.getUP() != null && axisLine.getLP() != null) {
			return true;
		} else {
			return false;
		}
	}

	public static void validate(AxisLine axisLine) {
		if (axisLine.getUP() == null || axisLine.getLP() == null)
			throw new AvuilderCoreRuntimeException(Errors.NOT_SUFFICIENTLY_DEFINED);
	}

	/**
	 * Gets the {@link #lP}.
	 * 
	 * @return the {@link #lP}.
	 */
	public Double getLP() {
		return lP;
	}

	/**
	 * Gets the {@link #uP}.
	 * 
	 * @return the {@link #uP}.
	 */
	public Double getUP() {
		return uP;
	}

	/**
	 * Sets the {@link #lP}.
	 * 
	 * @param lP the {@link #lP} to set.
	 * @throws IllegalArgumentException if lP is higher than {@link #uP}
	 */
	public void setLP(Double lP) {
		if (uP != null && lP != null && uP <= lP)
			throw new IllegalArgumentException("Lower point must be less than upper point.");
		this.lP = lP;
	}

	/**
	 * Sets the {@link #uP}.
	 * 
	 * @param uP the {@link #uP} to set.
	 * @throws IllegalArgumentException if uP is lower than {@link #lP}
	 */
	public void setUP(Double uP) {
		if (uP != null && lP != null && uP <= lP)
			throw new IllegalArgumentException("Upper point must be higher than lower point.");
		this.uP = uP;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lP == null) ? 0 : lP.hashCode());
		result = prime * result + ((uP == null) ? 0 : uP.hashCode());
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
		AxisLine other = (AxisLine) obj;
		if (lP == null) {
			if (other.lP != null)
				return false;
		} else if (!lP.equals(other.lP))
			return false;
		if (uP == null) {
			if (other.uP != null)
				return false;
		} else if (!uP.equals(other.uP))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AxisLine [uP=" + uP + ", lP=" + lP + "]";
	}

}
