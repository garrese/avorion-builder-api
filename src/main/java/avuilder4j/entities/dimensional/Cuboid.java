package avuilder4j.entities.dimensional;

import java.io.Serializable;
import java.util.ArrayList;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.utils.AvK;
import avuilder4j.utils.AvU;
import avuilder4j.utils.AvValidations;

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
	public static final int[] CORNERS_LIST = new int[] { // @formatter:off
			CORNER_BASE_1,
			CORNER_BASE_2,
			CORNER_BASE_3,
			CORNER_BASE_4,
			CORNER_TOP_1,
			CORNER_TOP_2,
			CORNER_TOP_3,
			CORNER_TOP_4
	}; //@formatter:on

	public static final int FACE_WALL_XL = 0;
	public static final int FACE_WALL_XU = 1;
	public static final int FACE_WALL_YL = 2;
	public static final int FACE_WALL_YU = 3;
	public static final int FACE_WALL_ZL = 4;
	public static final int FACE_WALL_ZU = 5;
	public static final int[] FACES_LIST = new int[] { // @formatter:off
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

	public Cuboid(Lengths lengths) {
		setLengths(lengths);
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

	public void escalate(double ratio, int[] fixedFacesIds) {
		escalate(ratio, null, fixedFacesIds);
	}

	public void escalate(double ratio, int[] fixedFacesIds, int[] axesIds) {
		if (axesIds == null) {
			axesIds = new int[0];
		}
		if (fixedFacesIds == null) {
			fixedFacesIds = new int[0];
		}
		validateCuboid();
		AvValidations.validateRatios(ratio);
		AvValidations.validateFacesExistance(fixedFacesIds);
		AvValidations.validateFixedFacesMaxNumber(fixedFacesIds);
		AvValidations.validateFixedFacesAxes(fixedFacesIds);
		AvValidations.validateAxesExistance(axesIds);
		AvValidations.validateAxesRepetition(axesIds);

		if (axesIds.length == 0) {
			axesIds = AvK.ALL_AXES;
		}

		for (int axisId : axesIds) {
			AxisEnds axis = getAxis(axisId);
			boolean axisCompleted = false;
			for (int faceId : fixedFacesIds) {
				int faceIdAxisId = AvU.getAxisIdByFaceId(faceId);
				if (axisId == faceIdAxisId) {
					Integer endId = AvU.getEndIdByFaceId(axisId);
					axis.escalateStatic(ratio, endId);
					axisCompleted = true;
				}
			}
			if (!axisCompleted) {
				axis.escalateStatic(ratio, null);
			}
		}
	}

	public void escalateByVolume(double finalVolume, int[] fixedFacesIds, int[] axesIds) {
		if (axesIds == null) {
			axesIds = new int[0];
		}
		validateCuboid();
		AvValidations.validateAxesExistance(axesIds);
		AvValidations.validateAxesRepetition(axesIds);

		double ratio;
		switch (axesIds.length) {
		case 0:
		case 3:
			ratio = Math.cbrt(finalVolume / getVolume());
			break;
		case 2:
			ratio = Math.sqrt(finalVolume / getVolume());
			break;
		case 1:
			ratio = finalVolume / getVolume();
			break;
		default:
			throw new Avuilder4jRuntimeException(AvErrors.AXIS_AMOUNT);
		}
		escalate(ratio, fixedFacesIds, axesIds);
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
		case AvK.AXIS_X:
			return getAxisX();
		case AvK.AXIS_Y:
			return getAxisY();
		case AvK.AXIS_Z:
			return getAxisZ();
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
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
				throw new IllegalArgumentException(AvErrors.CORNER_NOT_RECOGNIZED);
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
				throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
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

	public Lengths getLengths() {
		return new Lengths(axisX.getLength(), axisY.getLength(), axisZ.getLength());
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
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
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

	public void matchToFace(Cuboid reference, int faceMatching) {
		matchToFace(reference, faceMatching, true);
	}

	public void matchToFace(Cuboid reference, int faceMatching, boolean attach) {
		AvValidations.validateFacesExistance(faceMatching);

		int[] matchingAxesIds = new int[2];
		switch (faceMatching) {
		case Cuboid.FACE_WALL_YU:
		case Cuboid.FACE_WALL_YL:
			matchingAxesIds[0] = AvK.AXIS_X;
			matchingAxesIds[1] = AvK.AXIS_Z;
			break;
		case Cuboid.FACE_WALL_ZU:
		case Cuboid.FACE_WALL_ZL:
			matchingAxesIds[0] = AvK.AXIS_X;
			matchingAxesIds[1] = AvK.AXIS_Y;
			break;
		case Cuboid.FACE_WALL_XU:
		case Cuboid.FACE_WALL_XL:
			matchingAxesIds[0] = AvK.AXIS_Y;
			matchingAxesIds[1] = AvK.AXIS_Z;
			break;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}

		for (int axisId : matchingAxesIds) {
			getAxis(axisId).validateAxisEnds();
			reference.getAxis(axisId).validateAxisEnds();

			getAxis(axisId).setLength(reference.getAxis(axisId).getLength());
		}

		if (attach) {
			attachTo(reference, faceMatching);
		}

	}

	public void moveCenterByVector(Vector vector) {
		for (int axisId : AvK.ALL_AXES) {
			getAxis(axisId).moveCenterByVector(vector.getAxisComponent(axisId));
		}
	}

	public void moveCenterToPoint(Point point) {
		for (int axisId : AvK.ALL_AXES) {
			getAxis(axisId).moveCenterToPoint(point.getAxisComponent(axisId));
		}
	}

	public void rotate(int rotationId, int times, int... fixedFacesIds) {
		AvValidations.validateRotationsExistance(rotationId);
		AvValidations.validateFixedFacesMaxNumber(fixedFacesIds);
		AvValidations.validateFixedFacesAxes(fixedFacesIds);

		if (times <= 0) {
			throw new IllegalArgumentException("times argument must be greater than 0.");
		}

		int[] axesIds = AvU.getAxesIdsOfLengthsInvolvedInRotation(rotationId);
		try {
			AxisEnds axis0 = getAxis(axesIds[0]);
			AxisEnds axis1 = getAxis(axesIds[1]);
			axis0.validateAxisEnds();
			axis1.validateAxisEnds();

			Integer axis0FixedEnd = null;
			Integer axis1FixedEnd = null;
			for (int faceId : fixedFacesIds) {
				if (axesIds[0] == AvU.getAxisIdByFaceId(faceId)) {
					axis0FixedEnd = AvU.getEndIdByFaceId(faceId);
				}
				if (axesIds[1] == AvU.getAxisIdByFaceId(faceId)) {
					axis1FixedEnd = AvU.getEndIdByFaceId(faceId);
				}
			}

			double len0 = axis0.getLength();
			double len1 = axis1.getLength();
			axis0.setLength(len1, axis0FixedEnd);
			axis1.setLength(len0, axis1FixedEnd);

		} catch (Exception e) {
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED, e);
		}
	}

	public void setAxis(int axisId, AxisEnds axis) {
		switch (axisId) {
		case AvK.AXIS_X:
			setAxisX(axis);
		case AvK.AXIS_Y:
			setAxisY(axis);
		case AvK.AXIS_Z:
			setAxisZ(axis);
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
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

	public void setLengths(Lengths lengths, int... fixedFacesIds) {
		if (lengths == null) {
			lengths = new Lengths();
		}
		AvValidations.validateFacesExistance(fixedFacesIds);
		AvValidations.validateFacesRepetition(fixedFacesIds);
		AvValidations.validateFixedFacesMaxNumber(fixedFacesIds);
		AvValidations.validateFixedFacesAxes(fixedFacesIds);

		if (fixedFacesIds.length == 0) {
			for (int axisId : AvK.ALL_AXES) {
				getAxis(axisId).setLength(lengths.getLength(axisId));
			}

		} else {
			// fixed axes
			boolean notFixedX = true;
			boolean notFixedY = true;
			boolean notFixedZ = true;
			for (int i = 0; i < fixedFacesIds.length; i++) {
				int axisId = AvU.getAxisIdByFaceId(fixedFacesIds[i]);
				int endId = AvU.getEndIdByFaceId(fixedFacesIds[i]);
				getAxis(axisId).setLength(lengths.getLength(axisId), endId);

				if (axisId == AvK.AXIS_X) {
					notFixedX = false;
				} else if (axisId == AvK.AXIS_Y) {
					notFixedX = false;
				} else if (axisId == AvK.AXIS_Y) {
					notFixedX = false;
				}
			}

			// not fixed axes
			if (notFixedX)
				axisX.setLength(lengths.lengthX);
			if (notFixedY)
				axisY.setLength(lengths.lengthY);
			if (notFixedZ)
				axisZ.setLength(lengths.lengthZ);
		}
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
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

}
