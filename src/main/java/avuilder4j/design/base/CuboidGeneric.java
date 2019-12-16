package avuilder4j.design.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.enums.Corner;
import avuilder4j.design.enums.End;
import avuilder4j.design.enums.Face;
import avuilder4j.design.enums.Rotation;
import avuilder4j.design.sub.dimensional.AxisEnds;
import avuilder4j.design.sub.dimensional.Lengths;
import avuilder4j.design.sub.dimensional.Point;
import avuilder4j.design.sub.dimensional.Tagable;
import avuilder4j.design.sub.dimensional.Tagator;
import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.error.AvErrors;
import avuilder4j.error.AvValidations;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Instantiable;
import avuilder4j.util.java.NullSafe;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisEnds} in each of the three coordinate axis.
 */
@SuppressWarnings("rawtypes")
public abstract class CuboidGeneric<T extends CuboidGeneric>
		implements Serializable, Tagable, Chainable<T>, Instantiable<T> {
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

	/**
	 * Cuboid's index in structure.
	 */
	private Integer indexInStructure;

	/**
	 * Cuboid's parent index in a structure.
	 */
	private T parent;

	protected Tagator tagsAdministrator = new Tagator();

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
		this.indexInStructure = index;
	}

	public CuboidGeneric(Integer index, T parent) {
		super();
		this.indexInStructure = index;
		this.parent = parent;
	}

	public CuboidGeneric(Lengths lengths) {
		setLengths(lengths);
	}

	public T attachTo(T destinationCuboid, Face destinationFace) {
		Point faceOrigin = null;
		Point faceDestination = null;

		if (this == destinationCuboid)
			throw new IllegalArgumentException("The destination cuboid can not be the cuboid itself.");
		validateCuboid();
		destinationCuboid.validateCuboid();

		faceDestination = destinationCuboid.getFaceCenter(destinationFace);
		faceOrigin = getFaceCenter(Face.getOpposite(destinationFace));
		Vector centerToOwnFace = Vector.pointDiff(getCenter(), faceOrigin);

		moveCenterToPoint(faceDestination);
		moveCenterByVector(centerToOwnFace);

		setParent(destinationCuboid);
		return chain();
	}

	public T escalate(double ratio) {
		return escalate(ratio, null, null);
	}

	public T escalate(double ratio, Face[] fixedFacesIds) {
		return escalate(ratio, fixedFacesIds, null);
	}

	public T escalate(double ratio, Face[] fixedFaces, Axis[] axes) {
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
				Axis faceIdAxisId = Axis.getAxisByFace(faceId);
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
		return chain();
	}

	public T escalateByVolume(double finalVolume) {
		return escalateByVolume(finalVolume, null, null);
	}

	public T escalateByVolume(double finalVolume, Face... fixedFacesIds) {
		return escalateByVolume(finalVolume, fixedFacesIds, null);
	}

	public T escalateByVolume(double finalVolume, Face[] fixedFacesIds, Axis[] axesIds) {
		if (axesIds == null) {
			axesIds = Axis.values();
		}
		validateCuboid();
		AvValidations.validateAxesRepetition(axesIds);

		double ratio;
		switch (axesIds.length) {
		case 0:
		case 3:
			ratio = Math.cbrt(finalVolume / getVolumeCuboid());
			break;
		case 2:
			ratio = Math.sqrt(finalVolume / getVolumeCuboid());
			break;
		case 1:
			ratio = finalVolume / getVolumeCuboid();
			break;
		default:
			throw new Avuilder4jRuntimeException(AvErrors.AXIS_AMOUNT);
		}
		escalate(ratio, fixedFacesIds, axesIds);
		return chain();
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
			case XU:
				p.x = getAxisX().getUpperEnd();
				break;
			case XL:
				p.x = getAxisX().getLowerEnd();
				break;
			case YU:
				p.y = getAxisY().getUpperEnd();
				break;
			case YL:
				p.y = getAxisY().getLowerEnd();
				break;
			case ZU:
				p.z = getAxisZ().getUpperEnd();
				break;
			case ZL:
				p.z = getAxisZ().getLowerEnd();
				break;
			default:
				throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
			}
		}

		return p;
	}

	/**
	 * Gets the {@link #indexInStructure}.
	 * 
	 * @return the {@link #indexInStructure}.
	 */
	public Integer getIndexInStructure() { return indexInStructure; }

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

	/**
	 * Gets the {@link #parent}.
	 * 
	 * @return the {@link #parent}.
	 */
	public T getParent() { return parent; }

	public Double getVolumeCuboid() {
		if (isCuboidDefined()) {
			return axisX.getLength() * axisY.getLength() * axisZ.getLength();
		} else {
			return null;
		}
	}

	public Double getSurfaceArea() {
		return NullSafe.run(() -> {
			Double face1 = axisX.getLength() * axisY.getLength();
			Double face2 = axisX.getLength() * axisZ.getLength();
			Double face3 = axisY.getLength() * axisZ.getLength();
			return 2 * (face1 + face2 + face3);
		});
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

	public T matchToFace(T reference, Face faceMatching) {
		return matchToFace(reference, faceMatching, true);
	}

	public T matchToFace(T reference, Face faceMatching, boolean attach) {

		Axis[] matchingAxesIds = new Axis[2];
		switch (faceMatching) {
		case YU:
		case YL:
			matchingAxesIds[0] = Axis.X;
			matchingAxesIds[1] = Axis.Z;
			break;
		case ZU:
		case ZL:
			matchingAxesIds[0] = Axis.X;
			matchingAxesIds[1] = Axis.Y;
			break;
		case XU:
		case XL:
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
		return chain();

	}

	/**
	 * Mueve el cubo con el vector necesario para unir los dos puntos de referencia.
	 * 
	 * @param originRefPoint
	 * @param destinyRefPoint
	 */
	public T moveByPointsOfReference(Point originRefPoint, Point destinyRefPoint) {
		Vector v = Vector.pointDiff(destinyRefPoint, originRefPoint);
		moveCenterByVector(v);
		return chain();
	}
//	public void moveByPointsOfReference(Point originRefPoint, Point destinyRefPoint) {
//		Vector centerToRefDistance = Vector.pointDiff(getCenter(), originRefPoint);
//
//		moveCenterToPoint(destinyRefPoint);
//		moveCenterByVector(centerToRefDistance);
//	}

	public T moveCenterByVector(Vector vector) {
		for (Axis axis : Axis.values()) {
			getAxis(axis).moveCenterByVector(vector.getXyzByAxis(axis));
		}
		return chain();
	}

	public T moveCenterToPoint(Point point) {
		for (Axis axisId : Axis.values()) {
			getAxis(axisId).moveCenterToPoint(point.getXyzByAxis(axisId));
		}
		return chain();
	}

	public T rotate(int rotationId) {
		return rotate(rotationId);
	}

	public T rotate(Rotation rotationId, Face... fixedFacesIds) {
		AvValidations.validateFixedFacesMaxNumber(fixedFacesIds);
		AvValidations.validateFixedFacesAxes(fixedFacesIds);

		List<Axis> axesIds = Axis.getAxesInvolvedInCuboidRotation(rotationId);
		try {
			AxisEnds axis0 = getAxis(axesIds.get(0));
			AxisEnds axis1 = getAxis(axesIds.get(1));
			axis0.validateAxisEnds();
			axis1.validateAxisEnds();

			End axis0FixedEnd = null;
			End axis1FixedEnd = null;
			for (Face faceId : fixedFacesIds) {
				if (axesIds.get(0) == Axis.getAxisByFace(faceId)) {
					axis0FixedEnd = End.getEndIdByFaceId(faceId);
				}
				if (axesIds.get(1) == Axis.getAxisByFace(faceId)) {
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
		return chain();
	}

	public T setAxis(Axis axis, AxisEnds axisEnds) {
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
		return chain();
	}

	/**
	 * Sets the {@link #axisX}.
	 * 
	 * @param lineX the {@link #axisX} to set.
	 */
	public T setAxisX(AxisEnds lineX) {
		this.axisX = lineX;
		return chain();
	}

	/**
	 * Sets the {@link #axisY}.
	 * 
	 * @param lineY the {@link #axisY} to set.
	 */
	public T setAxisY(AxisEnds lineY) {
		this.axisY = lineY;
		return chain();
	}

	/**
	 * Sets the {@link #axisZ}.
	 * 
	 * @param lineZ the {@link #axisZ} to set.
	 */
	public T setAxisZ(AxisEnds lineZ) {
		this.axisZ = lineZ;
		return chain();
	}

	/**
	 * Sets the {@link #indexInStructure}.
	 * 
	 * @param index the {@link #indexInStructure} to set.
	 */
	public T setIndexInStructure(Integer index) {
		AvValidations.indexes(true, index);
		this.indexInStructure = index;
		return chain();
	}

	public T setLengths(Lengths lengths) {
		return setLengths(lengths, (Face[]) null);
	}

	public T setLengths(Lengths lengths, Face... fixedFacesIds) {
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
				getAxis(axisId).setLength(lengths.getXyzByAxis(axisId));
			}

		} else {
			// fixed axes
			boolean notFixedX = true;
			boolean notFixedY = true;
			boolean notFixedZ = true;
			for (int i = 0; i < fixedFacesIds.length; i++) {
				Axis axisId = Axis.getAxisByFace(fixedFacesIds[i]);
				End endId = End.getEndIdByFaceId(fixedFacesIds[i]);
				getAxis(axisId).setLength(lengths.getXyzByAxis(axisId), endId);

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
				axisX.setLength(lengths.x);
			if (notFixedY)
				axisY.setLength(lengths.y);
			if (notFixedZ)
				axisZ.setLength(lengths.z);
		}
		return chain();
	}

	/**
	 * Sets the {@link #parent}.
	 * 
	 * @param parent the {@link #parent} to set.
	 */
	public T setParent(T parent) {
		this.parent = parent;
		return chain();
	}

	@Override
	public String toString() {

		String parentSring = null;
		if (parent != null)
			parentSring = "[id=" + parent.getIndexInStructure() + "]";

		StringBuilder builder = new StringBuilder();
		builder.append("Cuboid [");
		builder.append(toStringHeader());
		builder.append(", ");
		builder.append(toStringBodyCuboid());
		builder.append("]");
		return builder.toString();
	}

	public String toStringHeader() {

		String parentSring = null;
		if (parent != null)
			parentSring = "[idx=" + parent.getIndexInStructure() + "]";

		StringBuilder builder = new StringBuilder();
		builder.append("tags=");
		builder.append(getTagator().getTags());
		builder.append(", index=");
		builder.append(getIndexInStructure());
		builder.append(", parent=");
		builder.append(parentSring);
		return builder.toString();
	}

	public String toStringBodyCuboid() {
		StringBuilder builder = new StringBuilder();
		builder.append("lengths=");
		builder.append(getLengths());
		builder.append(", volumeCuboid=");
		builder.append(getVolumeCuboid());
		builder.append(", center=");
		builder.append(getCenter());
		builder.append(", axisX=");
		builder.append(getAxisX());
		builder.append(", axisY=");
		builder.append(getAxisY());
		builder.append(", axisZ=");
		builder.append(getAxisZ());
		return builder.toString();
	}

	public void validateCuboid() {
		if (!isCuboidDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	@Override
	public int hashCode() {
		return Objects.hash(axisX, axisY, axisZ, indexInStructure, parent, tagsAdministrator);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CuboidGeneric))
			return false;
		CuboidGeneric other = (CuboidGeneric) obj;
		return Objects.equals(axisX, other.axisX) && Objects.equals(axisY, other.axisY)
				&& Objects.equals(axisZ, other.axisZ) && Objects.equals(indexInStructure, other.indexInStructure)
				&& Objects.equals(parent, other.parent) && Objects.equals(tagsAdministrator, other.tagsAdministrator);
	}

	@Override
	public Tagator getTagator() { return tagsAdministrator; }

	@Override
	public abstract T chain();

}
