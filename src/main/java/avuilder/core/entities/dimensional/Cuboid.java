package avuilder.core.entities.dimensional;

import java.io.Serializable;

import avuilder.core.utils.K;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisLine} in each of the three coordinate axis.
 */
public class Cuboid implements Serializable {
	private static final long serialVersionUID = -5598838939653504628L;

	/**
	 * X axis line.
	 */
	private AxisLine axisX = new AxisLine();

	/**
	 * Y axis line.
	 */
	private AxisLine axisY = new AxisLine();

	/**
	 * Z axis line.
	 */
	private AxisLine axisZ = new AxisLine();

	public Cuboid() {
	}

	/**
	 * @param axisX the {@link #axisX}
	 * @param axisY the {@link #axisY}
	 * @param axisZ the {@link #axisZ}
	 */
	public Cuboid(AxisLine axisX, AxisLine axisY, AxisLine axisZ) {
		this.axisX = axisX;
		this.axisY = axisY;
		this.axisZ = axisZ;
	}

	public static Cuboid deepCopy(Cuboid cuboid) {
		Cuboid c = null;
		if (cuboid != null) {
			c = new Cuboid();
			c.setAxisX(AxisLine.deepCopy(cuboid.getAxisX()));
			c.setAxisY(AxisLine.deepCopy(cuboid.getAxisY()));
			c.setAxisZ(AxisLine.deepCopy(cuboid.getAxisZ()));
		}
		return c;
	}

	public Double getVolume() {
		validate(this);
		return axisX.getLength() * axisY.getLength() * axisZ.getLength();
	}

	/**
	 * Calculates the central point of the cuboid.
	 * 
	 * @return the central point of the cuboid.
	 */
	public Point getCenter() {
		validate(this);
		Point p = new Point();
		p.x = axisX.getCenter();
		p.y = axisY.getCenter();
		p.z = axisZ.getCenter();
		return p;
	}

	public Point getFaceCenter(String face) {
		validate(this);
		Point p = getCenter();

		switch (face) {
		case K.FACE_BASE:
			p.y = getAxisY().getLP();
			break;
		case K.FACE_TOP:
			p.y = getAxisY().getUP();
			break;
		case K.FACE_WALL_1:
			p.z = getAxisZ().getUP();
			break;
		case K.FACE_WALL_2:
			p.x = getAxisX().getUP();
			break;
		case K.FACE_WALL_3:
			p.z = getAxisZ().getLP();
			break;
		case K.FACE_WALL_4:
			p.x = getAxisX().getLP();
			break;
		default:
			throw new IllegalArgumentException("Illegal face: " + face + ".");
		}

		return p;
	}

	public Point getCorner(String corner) {
		validate(this);
		Point p = new Point();

		switch (corner) {
		case K.CORNER_BASE_1:
			p.x = getAxisX().getLP();
			p.y = getAxisY().getLP();
			p.z = getAxisZ().getLP();
			break;
		case K.CORNER_BASE_2:
			p.x = getAxisX().getUP();
			p.y = getAxisY().getLP();
			p.z = getAxisZ().getLP();
			break;
		case K.CORNER_BASE_3:
			p.x = getAxisX().getUP();
			p.y = getAxisY().getLP();
			p.z = getAxisZ().getUP();
			break;
		case K.CORNER_BASE_4:
			p.x = getAxisX().getLP();
			p.y = getAxisY().getLP();
			p.z = getAxisZ().getUP();
			break;
		case K.CORNER_TOP_1:
			p.x = getAxisX().getLP();
			p.y = getAxisY().getUP();
			p.z = getAxisZ().getLP();
			break;
		case K.CORNER_TOP_2:
			p.x = getAxisX().getUP();
			p.y = getAxisY().getUP();
			p.z = getAxisZ().getLP();
			break;
		case K.CORNER_TOP_3:
			p.x = getAxisX().getUP();
			p.y = getAxisY().getUP();
			p.z = getAxisZ().getUP();
			break;
		case K.CORNER_TOP_4:
			p.x = getAxisX().getLP();
			p.y = getAxisY().getUP();
			p.z = getAxisZ().getUP();
			break;
		default:
			throw new IllegalArgumentException("Illegal corner: " + corner + ".");
		}

		return p;
	}

	public static boolean isDefined(Cuboid cuboid) {
		boolean x = cuboid.getAxisX() != null && AxisLine.isDefined(cuboid.getAxisX());
		boolean y = cuboid.getAxisY() != null && AxisLine.isDefined(cuboid.getAxisY());
		boolean z = cuboid.getAxisZ() != null && AxisLine.isDefined(cuboid.getAxisZ());
		return x && y && z;
	}

	public static void validate(Cuboid cuboid) {
		AxisLine.validate(cuboid.getAxisX());
		AxisLine.validate(cuboid.getAxisY());
		AxisLine.validate(cuboid.getAxisZ());
	}

	/**
	 * Gets the {@link #axisX}.
	 * 
	 * @return the {@link #axisX}.
	 */
	public AxisLine getAxisX() {
		return axisX;
	}

	/**
	 * Sets the {@link #axisX}.
	 * 
	 * @param lineX the {@link #axisX} to set.
	 */
	public void setAxisX(AxisLine lineX) {
		this.axisX = lineX;
	}

	/**
	 * Gets the {@link #axisY}.
	 * 
	 * @return the {@link #axisY}.
	 */
	public AxisLine getAxisY() {
		return axisY;
	}

	/**
	 * Sets the {@link #axisY}.
	 * 
	 * @param lineY the {@link #axisY} to set.
	 */
	public void setAxisY(AxisLine lineY) {
		this.axisY = lineY;
	}

	/**
	 * Gets the {@link #axisZ}.
	 * 
	 * @return the {@link #axisZ}.
	 */
	public AxisLine getAxisZ() {
		return axisZ;
	}

	/**
	 * Sets the {@link #axisZ}.
	 * 
	 * @param lineZ the {@link #axisZ} to set.
	 */
	public void setAxisZ(AxisLine lineZ) {
		this.axisZ = lineZ;
	}

	/**
	 * Sets the {@link #axisX} upper point
	 * 
	 * @return the {@link #axisX} upper point
	 */
	public Double getUX() {
		return getAxisX().getUP();
	}

	public void setUX(Double uX) {
		getAxisX().setUP(uX);
	}

	public Double getLX() {
		return getAxisX().getLP();
	}

	public void setLX(Double lX) {
		getAxisX().setLP(lX);
	}

	public Double getUY() {
		return getAxisY().getUP();
	}

	public void setUY(Double uY) {
		getAxisY().setUP(uY);
	}

	public Double getLY() {
		return getAxisY().getLP();
	}

	public void setLY(Double lY) {
		getAxisY().setLP(lY);
	}

	public Double getUZ() {
		return getAxisZ().getUP();
	}

	public void setUZ(Double uZ) {
		getAxisZ().setUP(uZ);
	}

	public Double getLZ() {
		return getAxisZ().getLP();
	}

	public void setLZ(Double lZ) {
		getAxisZ().setLP(lZ);
	}

	public Double getUD(int dimension) {
		switch (dimension) {
		case K.X:
			return getUX();
		case K.Y:
			return getUY();
		case K.Z:
			return getUZ();
		default:
			throw new IllegalArgumentException("Illegal dimension: " + dimension + ".");
		}
	}

	public void setUD(int dimension, Double uD) {
		switch (dimension) {
		case K.X:
			setUX(uD);
			break;
		case K.Y:
			setUY(uD);
			break;
		case K.Z:
			setUZ(uD);
			break;
		default:
			throw new IllegalArgumentException("Illegal dimension: " + dimension + ".");
		}
	}

	public Double getLD(int dimension) {
		switch (dimension) {
		case K.X:
			return getLX();
		case K.Y:
			return getLY();
		case K.Z:
			return getLZ();
		default:
			throw new IllegalArgumentException("Illegal dimension: " + dimension + ".");
		}
	}

	public void setLD(int dimension, Double lD) {
		switch (dimension) {
		case K.X:
			setLX(lD);
			break;
		case K.Y:
			setLY(lD);
			break;
		case K.Z:
			setLZ(lD);
			break;
		default:
			throw new IllegalArgumentException("Illegal dimension: " + dimension + ".");
		}
	}

	public Double getD(int dimension, int side) {
		switch (side) {
		case K.UPPER:
			return getUD(dimension);
		case K.LOWER:
			return getLD(dimension);
		default:
			throw new IllegalArgumentException("Illegal side: " + side + ".");
		}
	}

	public void setL(int dimension, int side, Double l) {
		switch (side) {
		case K.UPPER:
			setUD(dimension, l);
			break;
		case K.LOWER:
			setLD(dimension, l);
			break;
		default:
			throw new IllegalArgumentException("Illegal side: " + side + ".");
		}
	}

	@Override
	public String toString() {
		return "Cuboid [axisX=" + axisX + ", axisY=" + axisY + ", axisZ=" + axisZ + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((axisX == null) ? 0 : axisX.hashCode());
		result = prime * result + ((axisY == null) ? 0 : axisY.hashCode());
		result = prime * result + ((axisZ == null) ? 0 : axisZ.hashCode());
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
		Cuboid other = (Cuboid) obj;
		if (axisX == null) {
			if (other.axisX != null)
				return false;
		} else if (!axisX.equals(other.axisX))
			return false;
		if (axisY == null) {
			if (other.axisY != null)
				return false;
		} else if (!axisY.equals(other.axisY))
			return false;
		if (axisZ == null) {
			if (other.axisZ != null)
				return false;
		} else if (!axisZ.equals(other.axisZ))
			return false;
		return true;
	}

}
