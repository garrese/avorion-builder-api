package avuilder.core.entities.dimensional;

/**
 * Represents a cube in a Cartesian coordinate system.
 */
public class Cube {

	/**
	 * X axis line.
	 */
	private AxisLine lineX;

	/**
	 * Y axis line.
	 */
	private AxisLine lineY;

	/**
	 * Z axis line.
	 */
	private AxisLine lineZ;
	
	public Cube() {
	}
	
	/**
	 * @param lineX the {@link #lineX}
	 * @param lineY the {@link #lineY}
	 * @param lineZ the {@link #lineZ}
	 */
	public Cube(AxisLine lineX, AxisLine lineY, AxisLine lineZ) {
		this.lineX = lineX;
		this.lineY = lineY;
		this.lineZ = lineZ;
	}

	/**
	 * Calculates the central point of the cube.
	 * 
	 * @return the central point of the cube.
	 */
	public Point getCenter() {
		Point p = new Point();
		p.x = lineX.getCenter();
		p.y = lineY.getCenter();
		p.z = lineZ.getCenter();
		return p;
	}

	/**
	 * Gets the {@link #lineX}.
	 * 
	 * @return the {@link #lineX}.
	 */
	public AxisLine getLineX() {
		return lineX;
	}

	/**
	 * Sets the {@link #lineX}.
	 * 
	 * @param lineX the {@link #lineX} to set.
	 */
	public void setLineX(AxisLine lineX) {
		this.lineX = lineX;
	}

	/**
	 * Gets the {@link #lineY}.
	 * 
	 * @return the {@link #lineY}.
	 */
	public AxisLine getLineY() {
		return lineY;
	}

	/**
	 * Sets the {@link #lineY}.
	 * 
	 * @param lineY the {@link #lineY} to set.
	 */
	public void setLineY(AxisLine lineY) {
		this.lineY = lineY;
	}

	/**
	 * Gets the {@link #lineZ}.
	 * 
	 * @return the {@link #lineZ}.
	 */
	public AxisLine getLineZ() {
		return lineZ;
	}

	/**
	 * Sets the {@link #lineZ}.
	 * 
	 * @param lineZ the {@link #lineZ} to set.
	 */
	public void setLineZ(AxisLine lineZ) {
		this.lineZ = lineZ;
	}

}
