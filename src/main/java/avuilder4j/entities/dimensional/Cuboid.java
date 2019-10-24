package avuilder4j.entities.dimensional;

import java.io.Serializable;
import java.util.ArrayList;

import avuilder4j.error.ACErrors;
import avuilder4j.error.AvuilderEntityException;
import avuilder4j.utils.ACK;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisEnds} in each of the three coordinate axis.
 */
public class Cuboid implements Serializable {


	public static final int CORNER_BASE_1 = 0;
	public static final int CORNER_BASE_2 = 1;
	public static final int CORNER_BASE_3 = 2;
	public static final int CORNER_BASE_4 = 3;
	public static final int CORNER_TOP_1 = 4;
	public static final int CORNER_TOP_2 = 5;
	public static final int CORNER_TOP_3 = 6;
	public static final int CORNER_TOP_4 = 7;
	public static final int[] CORNERS = new int[] { // @formatter:off
			CORNER_BASE_1,
			CORNER_BASE_2,
			CORNER_BASE_3,
			CORNER_BASE_4,
			CORNER_TOP_1,
			CORNER_TOP_2,
			CORNER_TOP_3,
			CORNER_TOP_4
	}; //@formatter:on

	public static final int FACE_WALL_XL = 1;
	public static final int FACE_WALL_XU = 0;
	public static final int FACE_WALL_YL = 3;
	public static final int FACE_WALL_YU = 2;
	public static final int FACE_WALL_ZL = 5;
	public static final int FACE_WALL_ZU = 4;
	public static final int[] FACES = new int[] { // @formatter:off
			FACE_WALL_XU,
			FACE_WALL_XL,
			FACE_WALL_YU, 
			FACE_WALL_YL, 
			FACE_WALL_ZU, 
			FACE_WALL_ZL
	}; // @formatter:on

	private static final long serialVersionUID = -5598838939653504628L;

	public static Cuboid deepCopy(Cuboid cuboid) {
		Cuboid c = null;
		if (cuboid != null) {
			c = new Cuboid();
			c.setIndex(cuboid.getIndex());
			c.setParent(Cuboid.deepCopy(cuboid.getParent()));
			c.setAxisX(AxisEnds.deepCopy(cuboid.getAxisX()));
			c.setAxisY(AxisEnds.deepCopy(cuboid.getAxisY()));
			c.setAxisZ(AxisEnds.deepCopy(cuboid.getAxisZ()));
		}
		return c;
	}

	/**
	 * X axis line.
	 */
	protected AxisEnds axisX = new AxisEnds();

	/**
	 * Y axis line.
	 */
	protected AxisEnds axisY = new AxisEnds();

	/**
	 * Z axis line.
	 */
	protected AxisEnds axisZ = new AxisEnds();

	/**
	 * Cuboid's index in structure.
	 */
	protected Integer index;

	/**
	 * Cuboid's parent index in a structure.
	 */
	protected Cuboid parent;

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

	public Cuboid(double lengthX, double lengthY, double lengthZ) {
		setLengths(lengthX, lengthY, lengthZ);
	}

	public Cuboid(Integer index) {
		super();
		this.index = index;
	}

	public Cuboid(Integer index, Cuboid parent) {
		super();
		this.index = index;
		this.parent = parent;
	}

	public void attachTo(Cuboid destinationCuboid, int destinationFaceId) {
		Point faceOrigin = null;
		Point faceDestination = null;

		validateCuboid();
		destinationCuboid.validateCuboid();

		faceDestination = destinationCuboid.getFaceCenter(destinationFaceId);
		faceOrigin = getFaceCenter(getOppositeFaceId(destinationFaceId));
		Vector centerToOwnFace = Vector.pointDiff(getCenter(), faceOrigin);

		moveCenterToPoint(faceDestination);
		moveCenterByVector(centerToOwnFace);

		parent = destinationCuboid;
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

	public ArrayList<AxisEnds> getAllAxes() {
		ArrayList<AxisEnds> list = new ArrayList<AxisEnds>();
		list.add(axisX);
		list.add(axisY);
		list.add(axisZ);
		return list;
	}

	public AxisEnds getAxis(int axisId) {
		switch (axisId) {
		case ACK.AXIS_X:
			return getAxisX();
		case ACK.AXIS_Y:
			return getAxisY();
		case ACK.AXIS_Z:
			return getAxisZ();
		default:
			throw new IllegalArgumentException(ACErrors.AXIS_NOT_RECOGNIZED);
		}
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
	 * Gets the {@link #axisY}.
	 * 
	 * @return the {@link #axisY}.
	 */
	public AxisEnds getAxisY() {
		return axisY;
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
	 * Calculates the central point of the cuboid.
	 * 
	 * @return the central point of the cuboid.
	 */
	public Point getCenter() {
		Point p = null;
		if (isCuboidDefined()) {
			p = new Point();
			p.x = axisX.getCenter();
			p.y = axisY.getCenter();
			p.z = axisZ.getCenter();
		}
		return p;
	}

	public Point getCorner(int cornerId) {
		Point p = null;

		if (isCuboidDefined()) {
			p = new Point();
			switch (cornerId) {
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

	public Point getFaceCenter(int faceId) {
		Point p = null;

		if (isCuboidDefined()) {
			p = getCenter();
			switch (faceId) {
			case FACE_WALL_XU:
				p.x = getAxisX().getUpperEnd();
				break;
			case FACE_WALL_XL:
				p.x = getAxisX().getLowerEnd();
				break;
			case FACE_WALL_YU:
				p.y = getAxisY().getUpperEnd();
				break;
			case FACE_WALL_YL:
				p.y = getAxisY().getLowerEnd();
				break;
			case FACE_WALL_ZU:
				p.z = getAxisZ().getUpperEnd();
				break;
			case FACE_WALL_ZL:
				p.z = getAxisZ().getLowerEnd();
				break;
			default:
				throw new IllegalArgumentException(ACErrors.FACE_NOT_RECOGNIZED);
			}
		}

		return p;
	}

	/**
	 * Gets the {@link #index}.
	 * 
	 * @return the {@link #index}.
	 */
	public Integer getIndex() {
		return index;
	}

	public int getOppositeFaceId(int faceId) {
		switch (faceId) {
		case FACE_WALL_XU:
			return FACE_WALL_XL;
		case FACE_WALL_XL:
			return FACE_WALL_XU;
		case FACE_WALL_YU:
			return FACE_WALL_YL;
		case FACE_WALL_YL:
			return FACE_WALL_YU;
		case FACE_WALL_ZU:
			return FACE_WALL_ZL;
		case FACE_WALL_ZL:
			return FACE_WALL_ZU;
		default:
			throw new IllegalArgumentException(ACErrors.FACE_NOT_RECOGNIZED);
		}
	}

	/**
	 * Gets the {@link #parent}.
	 * 
	 * @return the {@link #parent}.
	 */
	public Cuboid getParent() {
		return parent;
	}

	public Double getVolume() {
		if (isCuboidDefined()) {
			return axisX.getLength() * axisY.getLength() * axisZ.getLength();
		} else {
			return null;
		}
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

	public boolean isCuboidDefined() {
		if (axisX == null || axisY == null || axisZ == null) {
			return false;
		}
		for (AxisEnds axis : getAllAxes()) {
			if (!axis.isAxisEndsDefined())
				return false;
		}
		return true;
	}

	public void moveCenterByVector(Vector vector) {
		for (int axisId : ACK.ALL_AXES) {
			getAxis(axisId).moveCenterByVector(vector.getAxisComponent(axisId));
		}
	}

	public void moveCenterToPoint(Point point) {
		for (int axisId : ACK.ALL_AXES) {
			getAxis(axisId).moveCenterToPoint(point.getAxisComponent(axisId));
		}
	}

	public void setAxis(int axisId, AxisEnds axis) {
		switch (axisId) {
		case ACK.AXIS_X:
			setAxisX(axis);
		case ACK.AXIS_Y:
			setAxisY(axis);
		case ACK.AXIS_Z:
			setAxisZ(axis);
		default:
			throw new IllegalArgumentException(ACErrors.AXIS_NOT_RECOGNIZED);
		}
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
	 * Sets the {@link #axisY}.
	 * 
	 * @param lineY the {@link #axisY} to set.
	 */
	public void setAxisY(AxisEnds lineY) {
		this.axisY = lineY;
	}

	/**
	 * Sets the {@link #axisZ}.
	 * 
	 * @param lineZ the {@link #axisZ} to set.
	 */
	public void setAxisZ(AxisEnds lineZ) {
		this.axisZ = lineZ;
	}

	/**
	 * Sets the {@link #index}.
	 * 
	 * @param index the {@link #index} to set.
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setLengths(double lengthX, double lengthY, double lengthZ) {
		axisX.setLength(lengthX);
		axisY.setLength(lengthY);
		axisZ.setLength(lengthZ);
	}

	/**
	 * Sets the {@link #parent}.
	 * 
	 * @param parent the {@link #parent} to set.
	 */
	public void setParent(Cuboid parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cuboid [volume=" + getVolume() + ", center=" + getCenter() + ", axisX=" + axisX + ", axisY=" + axisY
				+ ", axisZ=" + axisZ + "]";
	}

	public void validateCuboid() {
		if (!isCuboidDefined())
			throw new AvuilderEntityException(ACErrors.NOT_SUFFICIENTLY_DEFINED);
	}

}
