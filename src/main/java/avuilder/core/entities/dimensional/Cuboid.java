package avuilder.core.entities.dimensional;

import java.io.Serializable;
import java.util.ArrayList;

import avuilder.core.error.AvuilderEntityException;
import avuilder.core.error.Errors;
import avuilder.core.utils.K;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisEnds} in each of the three coordinate axis.
 */
public class Cuboid implements Serializable {
	private static final long serialVersionUID = -5598838939653504628L;

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
		validate();
		return axisX.getLength() * axisY.getLength() * axisZ.getLength();
	}

	/**
	 * Calculates the central point of the cuboid.
	 * 
	 * @return the central point of the cuboid.
	 */
	public Point getCenter() {
		validate();
		Point p = new Point();
		p.x = axisX.getCenter();
		p.y = axisY.getCenter();
		p.z = axisZ.getCenter();
		return p;
	}

	public Point getFaceCenter(int face) {
		validate();
		Point p = getCenter();

		switch (face) {
		case K.FACE_BASE:
			p.y = getAxisY().getLowerEnd();
			break;
		case K.FACE_TOP:
			p.y = getAxisY().getUpperEnd();
			break;
		case K.FACE_WALL_1:
			p.z = getAxisZ().getUpperEnd();
			break;
		case K.FACE_WALL_2:
			p.x = getAxisX().getUpperEnd();
			break;
		case K.FACE_WALL_3:
			p.z = getAxisZ().getLowerEnd();
			break;
		case K.FACE_WALL_4:
			p.x = getAxisX().getLowerEnd();
			break;
		default:
			throw new IllegalArgumentException(Errors.FACE_NOT_RECOGNIZED);
		}

		return p;
	}

	public Point getCorner(int corner) {
		validate();
		Point p = new Point();

		switch (corner) {
		case K.CORNER_BASE_1:
			p.x = getAxisX().getLowerEnd();
			p.y = getAxisY().getLowerEnd();
			p.z = getAxisZ().getLowerEnd();
			break;
		case K.CORNER_BASE_2:
			p.x = getAxisX().getUpperEnd();
			p.y = getAxisY().getLowerEnd();
			p.z = getAxisZ().getLowerEnd();
			break;
		case K.CORNER_BASE_3:
			p.x = getAxisX().getUpperEnd();
			p.y = getAxisY().getLowerEnd();
			p.z = getAxisZ().getUpperEnd();
			break;
		case K.CORNER_BASE_4:
			p.x = getAxisX().getLowerEnd();
			p.y = getAxisY().getLowerEnd();
			p.z = getAxisZ().getUpperEnd();
			break;
		case K.CORNER_TOP_1:
			p.x = getAxisX().getLowerEnd();
			p.y = getAxisY().getUpperEnd();
			p.z = getAxisZ().getLowerEnd();
			break;
		case K.CORNER_TOP_2:
			p.x = getAxisX().getUpperEnd();
			p.y = getAxisY().getUpperEnd();
			p.z = getAxisZ().getLowerEnd();
			break;
		case K.CORNER_TOP_3:
			p.x = getAxisX().getUpperEnd();
			p.y = getAxisY().getUpperEnd();
			p.z = getAxisZ().getUpperEnd();
			break;
		case K.CORNER_TOP_4:
			p.x = getAxisX().getLowerEnd();
			p.y = getAxisY().getUpperEnd();
			p.z = getAxisZ().getUpperEnd();
			break;
		default:
			throw new IllegalArgumentException(Errors.CORNER_NOT_RECOGNIZED);
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
			throw new AvuilderEntityException(Errors.NOT_SUFFICIENTLY_DEFINED);
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

	/**
	 * Sets the {@link #axisX} upper end.
	 * 
	 * @return the {@link #axisX} upper end.
	 */
	public Double getUpperX() {
		return getAxisX().getUpperEnd();
	}

	public void setUpperX(Double uX) {
		getAxisX().setUpperEnd(uX);
	}

	public Double geLowerX() {
		return getAxisX().getLowerEnd();
	}

	public void setLowerX(Double lX) {
		getAxisX().setLowerEnd(lX);
	}

	public Double getUpperY() {
		return getAxisY().getUpperEnd();
	}

	public void setUpperY(Double uY) {
		getAxisY().setUpperEnd(uY);
	}

	public Double getLowerY() {
		return getAxisY().getLowerEnd();
	}

	public void setLowerY(Double lY) {
		getAxisY().setLowerEnd(lY);
	}

	public Double getUpperZ() {
		return getAxisZ().getUpperEnd();
	}

	public void setUpperZ(Double uZ) {
		getAxisZ().setUpperEnd(uZ);
	}

	public Double getLowerZ() {
		return getAxisZ().getLowerEnd();
	}

	public void setLowerZ(Double lZ) {
		getAxisZ().setLowerEnd(lZ);
	}

	public Double getAxisUpper(int axis) {
		switch (axis) {
		case K.X:
			return getUpperX();
		case K.Y:
			return getUpperY();
		case K.Z:
			return getUpperZ();
		default:
			throw new IllegalArgumentException(Errors.AXIS_NOT_RECOGNIZED);
		}
	}

	public void setAxisUpper(int axis, Double uD) {
		switch (axis) {
		case K.X:
			setUpperX(uD);
			break;
		case K.Y:
			setUpperY(uD);
			break;
		case K.Z:
			setUpperZ(uD);
			break;
		default:
			throw new IllegalArgumentException(Errors.AXIS_NOT_RECOGNIZED);
		}
	}

	public Double getAxisLower(int axis) {
		switch (axis) {
		case K.X:
			return geLowerX();
		case K.Y:
			return getLowerY();
		case K.Z:
			return getLowerZ();
		default:
			throw new IllegalArgumentException(Errors.AXIS_NOT_RECOGNIZED);
		}
	}

	public void setAxisLower(int axis, Double lD) {
		switch (axis) {
		case K.X:
			setLowerX(lD);
			break;
		case K.Y:
			setLowerY(lD);
			break;
		case K.Z:
			setLowerZ(lD);
			break;
		default:
			throw new IllegalArgumentException(Errors.AXIS_NOT_RECOGNIZED);
		}
	}

	public Double getAxisEnd(int axis, int end) {
		switch (end) {
		case K.END_UPPER:
			return getAxisUpper(axis);
		case K.END_LOWER:
			return getAxisLower(axis);
		default:
			throw new IllegalArgumentException(Errors.END_NOT_RECOGNIZED);
		}
	}

	public void setAxisEnd(int dimension, int end, Double l) {
		switch (end) {
		case K.END_UPPER:
			setAxisUpper(dimension, l);
			break;
		case K.END_LOWER:
			setAxisLower(dimension, l);
			break;
		default:
			throw new IllegalArgumentException(Errors.END_NOT_RECOGNIZED);
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
