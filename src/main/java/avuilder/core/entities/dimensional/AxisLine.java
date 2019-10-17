package avuilder.core.entities.dimensional;

import avuilder.core.error.AvuilderCoreRuntimeException;

/**
 * Represents a line on a single coordinate axis.
 * <p>
 * The line is defined by an upper and a lower point in the coordinate axis.
 */
public class AxisLine {

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
	 * Checks if the line is completely defined in order to perform operations with it imminently.
	 * 
	 * @throws IllegalStateException if {@link #uP} or {@link #lP} are null.
	 */
	public void checkState() {
		if (uP == null)
			throw new AvuilderCoreRuntimeException("Upper point is not defined");
		if (lP == null)
			throw new AvuilderCoreRuntimeException("Lower point is not defined");
	}

	/**
	 * Calculates the center of the line.
	 * 
	 * @return the center of the line in the coordinate axis.
	 * @throws IllegalStateException if {@link #uP} or {@link #lP} are null.
	 */
	public Double getCenter() {
		checkState();
		return lP + (uP - lP) / 2;
	}

	/**
	 * Calculates the length of the line.
	 * 
	 * @return the line length.
	 * @throws IllegalStateException if {@link #uP} or {@link #lP} are null.
	 */
	public Double getLength() {
		checkState();
		return uP - lP;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AxisLine [uP=" + uP + ", lP=" + lP + "]";
	}

}
