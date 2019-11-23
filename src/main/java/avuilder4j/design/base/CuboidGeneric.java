package avuilder4j.design.base;

import java.io.Serializable;
import java.util.ArrayList;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.enums.Corner;
import avuilder4j.design.enums.End;
import avuilder4j.design.enums.Face;
import avuilder4j.design.enums.Rotation;
import avuilder4j.design.sub.AxisEnds;
import avuilder4j.design.sub.Lengths;
import avuilder4j.design.sub.Point;
import avuilder4j.design.sub.Vector;
import avuilder4j.error.AvErrors;
import avuilder4j.error.AvValidations;
import avuilder4j.error.Avuilder4jRuntimeException;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisEnds} in each of the three coordinate axis.
 */
@SuppressWarnings("rawtypes")
public class CuboidGeneric<T extends CuboidGeneric> implements Serializable {
	private static final long serialVersionUID = -5598838939653504628L;

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
	protected T parent;

	protected String tags;

//	public static Cuboid deepCopy(Cuboid cuboid) {
//		Cuboid c = null;
//		if (cuboid != null) {
//			c = new Cuboid();
//			c.setIndex(cuboid.getIndex());
//			c.setParent(Cuboid.deepCopy(cuboid.getParent()));
//			c.setAxisX(AxisEnds.deepCopy(cuboid.getAxisX()));
//			c.setAxisY(AxisEnds.deepCopy(cuboid.getAxisY()));
//			c.setAxisZ(AxisEnds.deepCopy(cuboid.getAxisZ()));
//		}
//		return c;
//	}

	public CuboidGeneric() {}

	public CuboidGeneric(Integer index) {
		super();
		this.index = index;
	}

	public CuboidGeneric(Integer index, T parent) {
		super();
		this.index = index;
		this.parent = parent;
	}

	public CuboidGeneric(Lengths lengths) {
		setLengths(lengths);
	}

	public void attachTo(T destinationCuboid, Face destinationFaceId) {
		Point faceOrigin = null;
		Point faceDestination = null;

		if (this == destinationCuboid)
			throw new IllegalArgumentException("The destination cuboid can not be the cuboid itself.");
		validateCuboid();
		destinationCuboid.validateCuboid();

		faceDestination = destinationCuboid.getFaceCenter(destinationFaceId);
		faceOrigin = getFaceCenter(getOppositeFaceId(destinationFaceId));
		Vector centerToOwnFace = Vector.pointDiff(getCenter(), faceOrigin);

		moveCenterToPoint(faceDestination);
		moveCenterByVector(centerToOwnFace);

		parent = destinationCuboid;
	}

	public void escalate(double ratio) {
		escalate(ratio, null, null);
	}

	public void escalate(double ratio, Face[] fixedFacesIds) {
		escalate(ratio, fixedFacesIds, null);
	}

	public void escalate(double ratio, Face[] fixedFaces, Axis[] axes) {
		if (axes == null || axes.length == 0) {
			axes = Axis.values();
		}
		if (fixedFaces == null) {
			fixedFaces = new Face[0];
		}
		validateCuboid();
		AvValidations.ratios(ratio);
		AvValidations.validateFixedFacesMaxNumber(fixedFaces);
		AvValidations.validateFixedFacesAxes(fixedFaces);
		AvValidations.validateAxesRepetition(axes);

		for (Axis axisId : axes) {
			AxisEnds axis = getAxis(axisId);
			boolean axisCompleted = false;
			for (Face faceId : fixedFaces) {
				Axis faceIdAxisId = Axis.getAxisIdByFaceId(faceId);
				if (axisId == faceIdAxisId) {
					End endId = End.getEndIdByFaceId(faceId);
					axis.escalateStatic(ratio, endId);
					axisCompleted = true;
				}
			}
			if (!axisCompleted) {
				axis.escalateStatic(ratio, null);
			}
		}
	}

	public void escalateByVolume(double finalVolume) {
		escalateByVolume(finalVolume, null, null);
	}

	public void escalateByVolume(double finalVolume, Face... fixedFacesIds) {
		escalateByVolume(finalVolume, fixedFacesIds, null);
	}

	public void escalateByVolume(double finalVolume, Face[] fixedFacesIds, Axis[] axesIds) {
		if (axesIds == null) {
			axesIds = Axis.values();
		}
		validateCuboid();
		AvValidations.validateAxesRepetition(axesIds);

		double ratio;
		switch (axesIds.length) {
		case 0:
		case 3:
			ratio = Math.cbrt(finalVolume / getCuboidVolume());
			break;
		case 2:
			ratio = Math.sqrt(finalVolume / getCuboidVolume());
			break;
		case 1:
			ratio = finalVolume / getCuboidVolume();
			break;
		default:
			throw new Avuilder4jRuntimeException(AvErrors.AXIS_AMOUNT);
		}
		escalate(ratio, fixedFacesIds, axesIds);
	}

	public ArrayList<AxisEnds> getAllAxes() {
		ArrayList<AxisEnds> list = new ArrayList<AxisEnds>();
		list.add(axisX);
		list.add(axisY);
		list.add(axisZ);
		return list;
	}

	public AxisEnds getAxis(Axis axis) {
		switch (axis) {
		case X:
			return getAxisX();
		case Y:
			return getAxisY();
		case Z:
			return getAxisZ();
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	/**
	 * Gets the {@link CuboidGeneric#axisX}.
	 * 
	 * @return the {@link #axisX}.
	 */
	public AxisEnds getAxisX() { return axisX; }

	/**
	 * Gets the {@link #axisY}.
	 * 
	 * @return the {@link #axisY}.
	 */
	public AxisEnds getAxisY() { return axisY; }

	/**
	 * Gets the {@link #axisZ}.
	 * 
	 * @return the {@link #axisZ}.
	 */
	public AxisEnds getAxisZ() { return axisZ; }

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

	public Point getCorner(Corner cornerId) {
		Point p = null;

		if (isCuboidDefined()) {
			p = new Point();
			switch (cornerId) {
			case BASE_1:
				p.x = getAxisX().getLowerEnd();
				p.y = getAxisY().getLowerEnd();
				p.z = getAxisZ().getLowerEnd();
				break;
			case BASE_2:
				p.x = getAxisX().getUpperEnd();
				p.y = getAxisY().getLowerEnd();
				p.z = getAxisZ().getLowerEnd();
				break;
			case BASE_3:
				p.x = getAxisX().getUpperEnd();
				p.y = getAxisY().getLowerEnd();
				p.z = getAxisZ().getUpperEnd();
				break;
			case BASE_4:
				p.x = getAxisX().getLowerEnd();
				p.y = getAxisY().getLowerEnd();
				p.z = getAxisZ().getUpperEnd();
				break;
			case TOP_1:
				p.x = getAxisX().getLowerEnd();
				p.y = getAxisY().getUpperEnd();
				p.z = getAxisZ().getLowerEnd();
				break;
			case TOP_2:
				p.x = getAxisX().getUpperEnd();
				p.y = getAxisY().getUpperEnd();
				p.z = getAxisZ().getLowerEnd();
				break;
			case TOP_3:
				p.x = getAxisX().getUpperEnd();
				p.y = getAxisY().getUpperEnd();
				p.z = getAxisZ().getUpperEnd();
				break;
			case TOP_4:
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

	public Point getFaceCenter(Face faceId) {
		Point p = null;

		if (isCuboidDefined()) {
			p = getCenter();
			switch (faceId) {
			case UX:
				p.x = getAxisX().getUpperEnd();
				break;
			case LX:
				p.x = getAxisX().getLowerEnd();
				break;
			case UY:
				p.y = getAxisY().getUpperEnd();
				break;
			case LY:
				p.y = getAxisY().getLowerEnd();
				break;
			case UZ:
				p.z = getAxisZ().getUpperEnd();
				break;
			case LZ:
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
	public Integer getIndex() { return index; }

	public Lengths getLengths() {
		Double lenX = null;
		Double lenY = null;
		Double lenZ = null;
		if (axisX != null)
			lenX = axisX.getLength();
		if (axisY != null)
			lenY = axisY.getLength();
		if (axisZ != null)
			lenZ = axisZ.getLength();
		return new Lengths(lenX, lenY, lenZ);
	}

	public Face getOppositeFaceId(Face faceId) {
		switch (faceId) {
		case UX:
			return Face.LX;
		case LX:
			return Face.UX;
		case UY:
			return Face.LY;
		case LY:
			return Face.UY;
		case UZ:
			return Face.LZ;
		case LZ:
			return Face.UZ;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

	/**
	 * Gets the {@link #parent}.
	 * 
	 * @return the {@link #parent}.
	 */
	public T getParent() { return parent; }

	/**
	 * Gets the {@link #tags}.
	 * 
	 * @return the {@link #tags}.
	 */
	public String getTags() { return tags; }

	public Double getCuboidVolume() {
		if (isCuboidDefined()) {
			return axisX.getLength() * axisY.getLength() * axisZ.getLength();
		} else {
			return null;
		}
	}

	public boolean hasTags(String tags) {
		boolean noThisTags = this.tags == null || this.tags.trim().isEmpty();
		boolean noArgTags = tags == null || tags.trim().isEmpty();
		if (noThisTags || noArgTags) {
			return false;
		}

		String[] argTagsArray = tags.trim().split(" ");
		String[] thisTagsArray = this.tags.trim().split(" ");
		for (String argTag : argTagsArray) {
			boolean hasTag = false;
			for (String thisTag : thisTagsArray) {
				if (argTag.equals(thisTag)) {
					hasTag = true;
					break;
				}
			}
			if (!hasTag) {
				return false;
			}
		}
		return true;
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

	public void matchToFace(T reference, Face faceMatching) {
		matchToFace(reference, faceMatching, true);
	}

	public void matchToFace(T reference, Face faceMatching, boolean attach) {

		Axis[] matchingAxesIds = new Axis[2];
		switch (faceMatching) {
		case UY:
		case LY:
			matchingAxesIds[0] = Axis.X;
			matchingAxesIds[1] = Axis.Z;
			break;
		case UZ:
		case LZ:
			matchingAxesIds[0] = Axis.X;
			matchingAxesIds[1] = Axis.Y;
			break;
		case UX:
		case LX:
			matchingAxesIds[0] = Axis.Y;
			matchingAxesIds[1] = Axis.Z;
			break;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}

		for (Axis axisId : matchingAxesIds) {
			getAxis(axisId).validateAxisEnds();
			reference.getAxis(axisId).validateAxisEnds();

			getAxis(axisId).setLength(reference.getAxis(axisId).getLength());
		}

		if (attach) {
			attachTo(reference, faceMatching);
		}

	}

	/**
	 * Mueve el cubo con el vector necesario para unir los dos puntos de referencia.
	 * 
	 * @param originRefPoint
	 * @param destinyRefPoint
	 */
	public void moveByPointsOfReference(Point originRefPoint, Point destinyRefPoint) {
		Vector v = Vector.pointDiff(destinyRefPoint, originRefPoint);
		moveCenterByVector(v);
	}
//	public void moveByPointsOfReference(Point originRefPoint, Point destinyRefPoint) {
//		Vector centerToRefDistance = Vector.pointDiff(getCenter(), originRefPoint);
//
//		moveCenterToPoint(destinyRefPoint);
//		moveCenterByVector(centerToRefDistance);
//	}

	public void moveCenterByVector(Vector vector) {
		for (Axis axisId : Axis.values()) {
			getAxis(axisId).moveCenterByVector(vector.getAxisComponent(axisId));
		}
	}

	public void moveCenterToPoint(Point point) {
		for (Axis axisId : Axis.values()) {
			getAxis(axisId).moveCenterToPoint(point.getAxisComponent(axisId));
		}
	}

	public void rotate(int rotationId) {
		rotate(rotationId);
	}

	public void rotate(Rotation rotationId, Face... fixedFacesIds) {
		AvValidations.validateFixedFacesMaxNumber(fixedFacesIds);
		AvValidations.validateFixedFacesAxes(fixedFacesIds);

		Axis[] axesIds = Axis.getAxesInvolvedInRotation(rotationId);
		try {
			AxisEnds axis0 = getAxis(axesIds[0]);
			AxisEnds axis1 = getAxis(axesIds[1]);
			axis0.validateAxisEnds();
			axis1.validateAxisEnds();

			End axis0FixedEnd = null;
			End axis1FixedEnd = null;
			for (Face faceId : fixedFacesIds) {
				if (axesIds[0] == Axis.getAxisIdByFaceId(faceId)) {
					axis0FixedEnd = End.getEndIdByFaceId(faceId);
				}
				if (axesIds[1] == Axis.getAxisIdByFaceId(faceId)) {
					axis1FixedEnd = End.getEndIdByFaceId(faceId);
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

	public void setAxis(Axis axis, AxisEnds axisEnds) {
		switch (axis) {
		case X:
			setAxisX(axisEnds);
			break;
		case Y:
			setAxisY(axisEnds);
			break;
		case Z:
			setAxisZ(axisEnds);
			break;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	/**
	 * Sets the {@link #axisX}.
	 * 
	 * @param lineX the {@link #axisX} to set.
	 */
	public void setAxisX(AxisEnds lineX) { this.axisX = lineX; }

	/**
	 * Sets the {@link #axisY}.
	 * 
	 * @param lineY the {@link #axisY} to set.
	 */
	public void setAxisY(AxisEnds lineY) { this.axisY = lineY; }

	/**
	 * Sets the {@link #axisZ}.
	 * 
	 * @param lineZ the {@link #axisZ} to set.
	 */
	public void setAxisZ(AxisEnds lineZ) { this.axisZ = lineZ; }

	/**
	 * Sets the {@link #index}.
	 * 
	 * @param index the {@link #index} to set.
	 */
	public void setIndex(Integer index) {
		AvValidations.indexes(true, index);
		this.index = index;
	}

	public void setLengths(Lengths lengths) {
		setLengths(lengths, (Face[]) null);
	}

	public void setLengths(Lengths lengths, Face... fixedFacesIds) {
		if (lengths == null) {
			lengths = new Lengths();
		}
		if (fixedFacesIds == null) {
			fixedFacesIds = new Face[0];
		}
		AvValidations.validateFacesRepetition(fixedFacesIds);
		AvValidations.validateFixedFacesMaxNumber(fixedFacesIds);
		AvValidations.validateFixedFacesAxes(fixedFacesIds);

		if (fixedFacesIds.length == 0) {
			for (Axis axisId : Axis.values()) {
				getAxis(axisId).setLength(lengths.getLength(axisId));
			}

		} else {
			// fixed axes
			boolean notFixedX = true;
			boolean notFixedY = true;
			boolean notFixedZ = true;
			for (int i = 0; i < fixedFacesIds.length; i++) {
				Axis axisId = Axis.getAxisIdByFaceId(fixedFacesIds[i]);
				End endId = End.getEndIdByFaceId(fixedFacesIds[i]);
				getAxis(axisId).setLength(lengths.getLength(axisId), endId);

				if (axisId == Axis.X) {
					notFixedX = false;
				} else if (axisId == Axis.Y) {
					notFixedY = false;
				} else if (axisId == Axis.Z) {
					notFixedZ = false;
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
	public void setParent(T parent) { this.parent = parent; }

	/**
	 * Sets the {@link #tags}.
	 * 
	 * @param tags the {@link #tags} to set.
	 */
	public void setTags(String tags) { this.tags = tags; }

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String parentSring = "[";
		parentSring += parent != null;
		if (parent != null)
			parentSring += ", id=" + parent.getIndex();
		parentSring += "]";

		//@formatter:off
		return "Block ["
				+ "tags=\"" + tags + "\""
				+ ", index=" + index 
				+ ", parent=" + parentSring
				+ ", lengths=" + getLengths()
				+ ", cuboidVolume=" + getCuboidVolume()
				+ ", center=" + getCenter()
				+ ", axisX=" + getAxisX()
				+ ", axisY=" + getAxisY() 
				+ ", axisZ=" + getAxisZ()
				+ "]";
		//@formatter:on
	}

	public void validateCuboid() {
		if (!isCuboidDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((axisX == null) ? 0 : axisX.hashCode());
		result = prime * result + ((axisY == null) ? 0 : axisY.hashCode());
		result = prime * result + ((axisZ == null) ? 0 : axisZ.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		CuboidGeneric other = (CuboidGeneric) obj;
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
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

}
