package avuilder.core.entities.dimensional;

import java.io.Serializable;

import avuilder.core.error.AvuilderCoreRuntimeException;
import avuilder.core.error.Errors;
import avuilder.core.utils.K;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisLine} in each of the three coordinate axis.
 */
public class Cuboid implements Serializable{
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

	/**
	 * Calculates the central point of the cuboid.
	 * 
	 * @return the central point of the cuboid.
	 */
	public Point getCenter() {
		Point p = new Point();
		try {
			p.x = axisX.getCenter();
			p.y = axisY.getCenter();
			p.z = axisZ.getCenter();
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(Errors.IMPOSSIBLE_TO_CALCULATE, e);
		}
		return p;
	}

	public Point getFaceCenter(String face) {
		Point p = null;
		try {
			p = getCenter();
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
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(Errors.IMPOSSIBLE_TO_CALCULATE, e);
		}
		return p;
	}

	public Point getCorner(String corner) {
		Point p = new Point();
		try {
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
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(Errors.IMPOSSIBLE_TO_CALCULATE, e);

		}
		return p;
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
