package avuilder.core.entities.dimensional;

/**
 * Line on a coordinate axis.
 */
public class AxisLine {

	/**
	 * Upper point of the line in the coordinate axis.
	 */
	protected Double uP;
	/**
	 * Lower point of the line in the coordinate axis.
	 */
	protected Double lP;

	public AxisLine() {
	}

	/**
	 * @param uP the {@link #uP}.
	 * @param lP the {@link #lP}.
	 */
	public AxisLine(Double uP, Double lP) {
		super();
		setUP(uP);
		setLP(lP);
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
	 * Gets the {@link #uP}.
	 * 
	 * @return the {@link #uP}.
	 */
	public Double getUP() {
		return uP;
	}

	/**
	 * Gets the {@link #lP}.
	 * 
	 * @return the {@link #lP}.
	 */
	public Double getlP() {
		return lP;
	}

	/**
	 * Checks if the line is completely defined in order to perform operations with
	 * it imminently.
	 * 
	 * @throws IllegalStateException if {@link #uP} or {@link #lP} are null.
	 */
	public void checkState() {
		if (uP == null)
			throw new IllegalStateException("Upper point is not defined");
		if (lP == null)
			throw new IllegalStateException("Lower point is not defined");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AxisLine [uP=" + uP + ", lP=" + lP + "]";
	}

}
