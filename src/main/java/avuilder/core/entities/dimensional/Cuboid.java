package avuilder.core.entities.dimensional;

import java.io.Serializable;
import java.util.ArrayList;

import avuilder.core.error.AvuilderEntityException;
import avuilder.core.error.ACErrors;
import avuilder.core.utils.ACK;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisEnds} in each of the three coordinate axis.
 */
public class Cuboid implements Serializable {
	private static final long serialVersionUID = -5598838939653504628L;

	public static final int FACE_BASE = 0;
	public static final int FACE_TOP = 1;
	public static final int FACE_WALL_1 = 2;
	public static final int FACE_WALL_2 = 3;
	public static final int FACE_WALL_3 = 4;
	public static final int FACE_WALL_4 = 5;
	public static final int[] ALL_FACES = new int[] { //@formatter:off hhh
			FACE_BASE,
			FACE_TOP,
			FACE_WALL_1,
			FACE_WALL_2,
			FACE_WALL_3,
			FACE_WALL_4
	}; //@formatter:on

	public static final int CORNER_BASE_1 = 0;
	public static final int CORNER_BASE_2 = 1;
	public static final int CORNER_BASE_3 = 2;
	public static final int CORNER_BASE_4 = 3;
	public static final int CORNER_TOP_1 = 4;
	public static final int CORNER_TOP_2 = 5;
	public static final int CORNER_TOP_3 = 6;
	public static final int CORNER_TOP_4 = 7;
	public static final int[] ALL_CORNERS = new int[] { //@formatter:off
			CORNER_BASE_1,
			CORNER_BASE_2,
			CORNER_BASE_3,
			CORNER_BASE_4,
			CORNER_TOP_1,
			CORNER_TOP_2,
			CORNER_TOP_3,
			CORNER_TOP_4
	}; //@formatter:on

	/**
	 * X axis line.
	 */
	private AxisEnds axisX = new AxisEnds();

	/**
	 * Y axis line.
	 */
	private AxisEnds axisY = new AxisEnds();

	/**
	 * Z axis line.
	 */
	private AxisEnds axisZ = new AxisEnds();

	public Cuboid() {
	}

	/**
	 * @param axisX the {@link #axisX}
	 * @param axisY the {@link #axisY}
	 * @param axisZ the {@link #axisZ}
	 */
	public Cuboid(AxisEnds axisX, AxisEnds axisY, AxisEnds axisZ) {
		this.axisX = axisX;
		this.axisY = axisY;
		this.axisZ = axisZ;
	}

	public static Cuboid deepCopy(Cuboid cuboid) {
		Cuboid c = null;
		if (cuboid != null) {
			c = new Cuboid();
			c.setAxisX(AxisEnds.deepCopy(cuboid.getAxisX()));
			c.setAxisY(AxisEnds.deepCopy(cuboid.getAxisY()));
			c.setAxisZ(AxisEnds.deepCopy(cuboid.getAxisZ()));
		}
		return c;
	}

	public Double getVolume() {
		if (isDefined()) {
			return axisX.getLength() * axisY.getLength() * axisZ.getLength();
		} else {
			return null;
		}
	}

	/**
	 * Calculates the central point of the cuboid.
	 * 
	 * @return the central point of the cuboid.
	 */
	public Point getCenter() {
		Point p = null;
		if (isDefined()) {
			p = new Point();
			p.x = axisX.getCenter();
			p.y = axisY.getCenter();
			p.z = axisZ.getCenter();
		}
		return p;
	}

	public Point getFaceCenter(int face) {
		Point p = null;

		if (isDefined()) {
			p = new Point();
			switch (face) {
			case FACE_BASE:
				p.y = getAxisY().getLowerEnd();
				break;
			case FACE_TOP:
				p.y = getAxisY().getUpperEnd();
				break;
			case FACE_WALL_1:
				p.z = getAxisZ().getUpperEnd();
				break;
			case FACE_WALL_2:
				p.x = getAxisX().getUpperEnd();
				break;
			case FACE_WALL_3:
				p.z = getAxisZ().getLowerEnd();
				break;
			case FACE_WALL_4:
				p.x = getAxisX().getLowerEnd();
				break;
			default:
				throw new IllegalArgumentException(ACErrors.FACE_NOT_RECOGNIZED);
			}
		}

		return p;
	}

	public Point getCorner(int corner) {
		Point p = null;

		if (isDefined()) {
			p = new Point();
			switch (corner) {
			case CORNER_BASE_1:
				p.x = getAxisX().getLowerEnd();
				p.y = getAxisY().getLowerEnd();
				p.z = getAxisZ().getLowerEnd();
				break;
			case CORNER_BASE_2:
				p.x = getAxisX().getUpperEnd();
				p.y = getAxisY().getLowerEnd();
				p.z = getAxisZ().getLowerEnd();
				break;
			case CORNER_BASE_3:
				p.x = getAxisX().getUpperEnd();
				p.y = getAxisY().getLowerEnd();
				p.z = getAxisZ().getUpperEnd();
				break;
			case CORNER_BASE_4:
				p.x = getAxisX().getLowerEnd();
				p.y = getAxisY().getLowerEnd();
				p.z = getAxisZ().getUpperEnd();
				break;
			case CORNER_TOP_1:
				p.x = getAxisX().getLowerEnd();
				p.y = getAxisY().getUpperEnd();
				p.z = getAxisZ().getLowerEnd();
				break;
			case CORNER_TOP_2:
				p.x = getAxisX().getUpperEnd();
				p.y = getAxisY().getUpperEnd();
				p.z = getAxisZ().getLowerEnd();
				break;
			case CORNER_TOP_3:
				p.x = getAxisX().getUpperEnd();
				p.y = getAxisY().getUpperEnd();
				p.z = getAxisZ().getUpperEnd();
				break;
			case CORNER_TOP_4:
				p.x = getAxisX().getLowerEnd();
				p.y = getAxisY().getUpperEnd();
				p.z = getAxisZ().getUpperEnd();
				break;
			default:
				throw new IllegalArgumentException(ACErrors.CORNER_NOT_RECOGNIZED);
			}
		}

		return p;
	}

	public boolean isDefined() {
		for (AxisEnds axis : getAllAxes()) {
			if (!axis.isDefined())
				return false;
		}
		return true;
	}

	public void validate() {
		if (!isDefined())
			throw new AvuilderEntityException(ACErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	/**
	 * Gets the {@link #axisX}.
	 * 
	 * @return the {@link #axisX}.
	 */
	public AxisEnds getAxisX() {
		return axisX;
	}

	/**
	 * Sets the {@link #axisX}.
	 * 
	 * @param lineX the {@link #axisX} to set.
	 */
	public void setAxisX(AxisEnds lineX) {
		this.axisX = lineX;
	}

	/**
	 * Gets the {@link #axisY}.
	 * 
	 * @return the {@link #axisY}.
	 */
	public AxisEnds getAxisY() {
		return axisY;
	}

	/**
	 * Sets the {@link #axisY}.
	 * 
	 * @param lineY the {@link #axisY} to set.
	 */
	public void setAxisY(AxisEnds lineY) {
		this.axisY = lineY;
	}

	/**
	 * Gets the {@link #axisZ}.
	 * 
	 * @return the {@link #axisZ}.
	 */
	public AxisEnds getAxisZ() {
		return axisZ;
	}

	/**
	 * Sets the {@link #axisZ}.
	 * 
	 * @param lineZ the {@link #axisZ} to set.
	 */
	public void setAxisZ(AxisEnds lineZ) {
		this.axisZ = lineZ;
	}

	public ArrayList<AxisEnds> getAllAxes() {
		ArrayList<AxisEnds> list = new ArrayList<AxisEnds>();
		list.add(axisX);
		list.add(axisY);
		list.add(axisZ);
		return list;
	}

	public Double getAxisUpper(int axis) {
		switch (axis) {
		case ACK.AXIS_X:
			return getAxisX().getUpperEnd();
		case ACK.AXIS_Y:
			return getAxisY().getUpperEnd();
		case ACK.AXIS_Z:
			return getAxisZ().getUpperEnd();
		default:
			throw new IllegalArgumentException(ACErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public void setAxisUpper(int axis, Double uD) {
		switch (axis) {
		case ACK.AXIS_X:
			getAxisX().setUpperEnd(uD);
			break;
		case ACK.AXIS_Y:
			getAxisY().setUpperEnd(uD);
			break;
		case ACK.AXIS_Z:
			getAxisZ().setUpperEnd(uD);
			break;
		default:
			throw new IllegalArgumentException(ACErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public Double getAxisLower(int axis) {
		switch (axis) {
		case ACK.AXIS_X:
			return getAxisX().getLowerEnd();
		case ACK.AXIS_Y:
			return getAxisY().getLowerEnd();
		case ACK.AXIS_Z:
			return getAxisZ().getLowerEnd();
		default:
			throw new IllegalArgumentException(ACErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public void setAxisLower(int axis, Double lD) {
		switch (axis) {
		case ACK.AXIS_X:
			getAxisX().setLowerEnd(lD);
			break;
		case ACK.AXIS_Y:
			getAxisY().setLowerEnd(lD);
			break;
		case ACK.AXIS_Z:
			getAxisZ().setLowerEnd(lD);
			break;
		default:
			throw new IllegalArgumentException(ACErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public void setLengths(double lengthX, double lengthY, double lengthZ) {
		axisX.setLength(lengthX);
		axisY.setLength(lengthY);
		axisZ.setLength(lengthZ);
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
