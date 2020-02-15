package avuilder4j.design.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.enums.Corner;
import avuilder4j.design.enums.End;
import avuilder4j.design.enums.Face;
import avuilder4j.design.sub.dimensional.AxisEnds;
import avuilder4j.design.sub.dimensional.Lengths;
import avuilder4j.design.sub.dimensional.Point;
import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.error.AvErrors;
import avuilder4j.error.AvValidations;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Copyable;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.java.Tagable;
import avuilder4j.util.java.Tagator;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisEnds} in each of the three coordinate axis.
 */
@SuppressWarnings("rawtypes")
public abstract class CuboidGeneric<T extends CuboidGeneric<T>> implements Serializable, Tagable, Chainable<T>, Copyable<T> {
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

	protected Tagator tagator = new Tagator();

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

	public static <T extends CuboidGeneric> T copy(T copyFrom, T copyTo) {
		if (copyFrom != null && copyTo != null) {
			for (Axis axis : Axis.values()) {
				copyTo.setAxisEnds(axis, Nullable.m(() -> copyFrom.getAxisEnds(axis).getCopy()));
			}
			Tagator.copy(copyFrom.getTagator(), copyTo.getTagator());
		}
		return copyTo;
	}

	public T addTags(String tags) {
		if (tags != null)
			getTagator().addTags(tags);
		return chain();
	}

	public T attachTo(T destinationCuboid, Face destinationFace) {
		if (this == destinationCuboid)
			throw new IllegalArgumentException("The destination cuboid can not be the cuboid itself.");
		this.validateCuboid();
		destinationCuboid.validateCuboid();

		Point destinationFacePoint = destinationCuboid.getFaceCenter(destinationFace);
		Point originFacePoint = this.getFaceCenter(Face.getOpposite(destinationFace));

		Vector vector = new Vector(destinationFacePoint.subV3(originFacePoint));
		this.moveCenterByVector(vector);

//		Point originFacePoint = null;
//		Point destinyFacePoint = null;
//
//		destinyFacePoint = destinationCuboid.getFaceCenter(destinationFace);
//		originFacePoint = getFaceCenter(Face.getOpposite(destinationFace));
//		Vector centerToOwnFace = Vector.pointDiff(getCenter(), originFacePoint);
//
//		moveCenterToPoint(destinyFacePoint);
//		moveCenterByVector(centerToOwnFace);

		setParent(destinationCuboid);
		return chain();
	}

	@Override
	public abstract T chain();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CuboidGeneric))
			return false;
		CuboidGeneric other = (CuboidGeneric) obj;
		return Objects.equals(axisX, other.axisX) && Objects.equals(axisY, other.axisY) && Objects.equals(axisZ, other.axisZ) && Objects.equals(indexInStructure, other.indexInStructure) && Objects.equals(parent, other.parent)
				&& Objects.equals(tagator, other.tagator);
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
			AxisEnds axis = getAxisEnds(axisId);
			boolean axisCompleted = false;
			for (Face faceId : fixedFaces) {
				Axis faceIdAxisId = Axis.getAxisByFace(faceId);
				if (axisId == faceIdAxisId) {
					End endId = End.getEndByFace(faceId);
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

	public List<AxisEnds> getAllAxisEnds() {
		ArrayList<AxisEnds> list = new ArrayList<AxisEnds>();
		list.add(getAxisEndsX());
		list.add(getAxisEndsY());
		list.add(getAxisEndsZ());
		return list;
	}

	public AxisEnds getAxisEnds(Axis axis) {
		switch (axis) {
		case X:
			return getAxisEndsX();
		case Y:
			return getAxisEndsY();
		case Z:
			return getAxisEndsZ();
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public AxisEnds getAxisEndsOrCreate(Axis axis) {
		AxisEnds ends = getAxisEnds(axis);
		if (ends == null)
			ends = new AxisEnds();
		return ends;
	}

	/**
	 * Gets the {@link CuboidGeneric#axisX}.
	 * 
	 * @return the {@link #axisX}.
	 */
	public AxisEnds getAxisEndsX() { return axisX; }

	/**
	 * Gets the {@link #axisY}.
	 * 
	 * @return the {@link #axisY}.
	 */
	public AxisEnds getAxisEndsY() { return axisY; }

	/**
	 * Gets the {@link #axisZ}.
	 * 
	 * @return the {@link #axisZ}.
	 */
	public AxisEnds getAxisEndsZ() { return axisZ; }

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
				p.x = getAxisEndsX().getLowerEnd();
				p.y = getAxisEndsY().getLowerEnd();
				p.z = getAxisEndsZ().getLowerEnd();
				break;
			case BASE_2:
				p.x = getAxisEndsX().getUpperEnd();
				p.y = getAxisEndsY().getLowerEnd();
				p.z = getAxisEndsZ().getLowerEnd();
				break;
			case BASE_3:
				p.x = getAxisEndsX().getUpperEnd();
				p.y = getAxisEndsY().getLowerEnd();
				p.z = getAxisEndsZ().getUpperEnd();
				break;
			case BASE_4:
				p.x = getAxisEndsX().getLowerEnd();
				p.y = getAxisEndsY().getLowerEnd();
				p.z = getAxisEndsZ().getUpperEnd();
				break;
			case TOP_1:
				p.x = getAxisEndsX().getLowerEnd();
				p.y = getAxisEndsY().getUpperEnd();
				p.z = getAxisEndsZ().getLowerEnd();
				break;
			case TOP_2:
				p.x = getAxisEndsX().getUpperEnd();
				p.y = getAxisEndsY().getUpperEnd();
				p.z = getAxisEndsZ().getLowerEnd();
				break;
			case TOP_3:
				p.x = getAxisEndsX().getUpperEnd();
				p.y = getAxisEndsY().getUpperEnd();
				p.z = getAxisEndsZ().getUpperEnd();
				break;
			case TOP_4:
				p.x = getAxisEndsX().getLowerEnd();
				p.y = getAxisEndsY().getUpperEnd();
				p.z = getAxisEndsZ().getUpperEnd();
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
				p.x = getAxisEndsX().getUpperEnd();
				break;
			case XL:
				p.x = getAxisEndsX().getLowerEnd();
				break;
			case YU:
				p.y = getAxisEndsY().getUpperEnd();
				break;
			case YL:
				p.y = getAxisEndsY().getLowerEnd();
				break;
			case ZU:
				p.z = getAxisEndsZ().getUpperEnd();
				break;
			case ZL:
				p.z = getAxisEndsZ().getLowerEnd();
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
	public Integer getIndex() { return indexInStructure; }

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

	public T getRoot() {
		List<T> chain = getRootChain();
		return chain.get(chain.size() - 1);
	}

	/**
	 * Si no es circular: result.get(result.size()-1).getParent() == null; si no, no es nulo.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getRootChain() {
		List<T> parents = new ArrayList<>();
		boolean terminate = false;

		parents.add((T) this);
		T checking = parents.get(0);
		T lastParent = null;
		do {
			lastParent = checking.getParent();
			final T lastParentAuxFinal = lastParent;
			if (lastParent == null || parents.stream().anyMatch(x -> x == lastParentAuxFinal)) {
				terminate = true;
			} else {
				lastParent = checking.getParent();
				parents.add(lastParent);
			}
			checking = lastParent;
		} while (!terminate);

		return parents;
	}

	public Double getSurfaceArea() {
		return Nullable.m(() -> {
			Double face1 = axisX.getLength() * axisY.getLength();
			Double face2 = axisX.getLength() * axisZ.getLength();
			Double face3 = axisY.getLength() * axisZ.getLength();
			return 2 * (face1 + face2 + face3);
		});
	}

	@Override
	public Tagator getTagator() { return tagator; }

	public Double getVolumeCuboid() {
		if (isCuboidDefined()) {
			return axisX.getLength() * axisY.getLength() * axisZ.getLength();
		} else {
			return null;
		}
	}

	public Double getXL() {
		if (getAxisEndsX() != null)
			return getAxisEndsX().getLowerEnd();
		else
			return null;
	}

	public Double getXU() {
		if (getAxisEndsX() != null)
			return getAxisEndsX().getUpperEnd();
		else
			return null;
	}

	public Double getYL() {
		if (getAxisEndsY() != null)
			return getAxisEndsY().getLowerEnd();
		else
			return null;
	}

	public Double getYU() {
		if (getAxisEndsY() != null)
			return getAxisEndsY().getUpperEnd();
		else
			return null;
	}

	public Double getZL() {
		if (getAxisEndsZ() != null)
			return getAxisEndsZ().getLowerEnd();
		else
			return null;
	}

	public Double getZU() {
		if (getAxisEndsZ() != null)
			return getAxisEndsZ().getUpperEnd();
		else
			return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(axisX, axisY, axisZ, indexInStructure, parent, tagator);
	}

	public boolean isCuboidDefined() {
		if (axisX == null || axisY == null || axisZ == null) {
			return false;
		}
		for (AxisEnds axis : getAllAxisEnds()) {
			if (!axis.isAxisEndsDefined())
				return false;
		}
		return true;
	}

	public boolean isRooted() { return getRoot() != null ? true : false; }

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
			getAxisEnds(axisId).validateAxisEnds();
			reference.getAxisEnds(axisId).validateAxisEnds();

			getAxisEnds(axisId).setLength(reference.getAxisEnds(axisId).getLength());
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
		Vector v = new Vector(destinyRefPoint.subV3(originRefPoint));
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
			getAxisEnds(axis).moveCenterByVector(vector.getV3Axis(axis));
		}
		return chain();
	}

	public T moveCenterToPoint(Point point) {
		for (Axis axisId : Axis.values()) {
			getAxisEnds(axisId).moveCenterToPoint(point.getV3Axis(axisId));
		}
		return chain();
	}

	public T rotateCuboid(Axis rotationAxis) {
		return rotateCuboid(rotationAxis, new Face[0]);
	}

	public T rotateCuboid(Axis rotationAxis, Face... fixedFaces) {
		AvValidations.validateFixedFacesMaxNumber(fixedFaces);
		AvValidations.validateFixedFacesAxes(fixedFaces);

		List<Axis> axesIds = Axis.getAxesInvolvedInRotation(rotationAxis);
		try {
			AxisEnds axis0 = getAxisEnds(axesIds.get(0));
			AxisEnds axis1 = getAxisEnds(axesIds.get(1));
			axis0.validateAxisEnds();
			axis1.validateAxisEnds();

			End axis0FixedEnd = null;
			End axis1FixedEnd = null;
			for (Face faceId : fixedFaces) {
				if (axesIds.get(0) == Axis.getAxisByFace(faceId)) {
					axis0FixedEnd = End.getEndByFace(faceId);
				}
				if (axesIds.get(1) == Axis.getAxisByFace(faceId)) {
					axis1FixedEnd = End.getEndByFace(faceId);
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

	public T setAxisEnds(Axis axis, AxisEnds axisEnds) {
		switch (axis) {
		case X:
			setAxisEndsX(axisEnds);
			break;
		case Y:
			setAxisEndsY(axisEnds);
			break;
		case Z:
			setAxisEndsZ(axisEnds);
			break;
		}
		return chain();
	}

	/**
	 * Sets the {@link #axisX}.
	 * 
	 * @param lineX the {@link #axisX} to set.
	 */
	public T setAxisEndsX(AxisEnds lineX) {
		this.axisX = lineX;
		return chain();
	}

	/**
	 * Sets the {@link #axisY}.
	 * 
	 * @param lineY the {@link #axisY} to set.
	 */
	public T setAxisEndsY(AxisEnds lineY) {
		this.axisY = lineY;
		return chain();
	}

	/**
	 * Sets the {@link #axisZ}.
	 * 
	 * @param lineZ the {@link #axisZ} to set.
	 */
	public T setAxisEndsZ(AxisEnds lineZ) {
		this.axisZ = lineZ;
		return chain();
	}

	/**
	 * Sets the {@link #indexInStructure}.
	 * 
	 * @param index the {@link #indexInStructure} to set.
	 */
	public T setIndex(Integer index) {
		AvValidations.indexes(true, index);
		this.indexInStructure = index;
		return chain();
	}

	public T setLengthByAxis(Axis axis, Number length) {
		getAxisEndsOrCreate(axis).setLength(length);
		return chain();
	}

	public T setLengthByAxis(Axis axis, Number length, End fixedEnd) {
		getAxisEndsOrCreate(axis).setLength(length, fixedEnd);
		return chain();
	}

	public T setLengths(Lengths lengths) {
		for (Axis axis : Axis.values()) {
			Double len = lengths.getV3Axis(axis);
			if (getAxisEnds(axis) != null) {
				getAxisEnds(axis).setLength(len);
			} else {
				setAxisEnds(axis, new AxisEnds().setLength(len));
			}
		}
		return chain();
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
			setLengths(lengths);
		} else {
			// fixed axes
			boolean notFixedX = true;
			boolean notFixedY = true;
			boolean notFixedZ = true;
			for (int i = 0; i < fixedFacesIds.length; i++) {
				Axis axisId = Axis.getAxisByFace(fixedFacesIds[i]);
				End endId = End.getEndByFace(fixedFacesIds[i]);
				getAxisEnds(axisId).setLength(lengths.getV3Axis(axisId), endId);

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

	public T setLengths(Number xyz) {
		return setLengths(new Lengths().setV3(xyz));
	}

	public T setLengths(Number x, Number y, Number z) {
		return setLengths(new Lengths(x, y, z));
	}

	public T setLengthX(Number length) {
		return setLengthByAxis(Axis.X, length);
	}

	public T setLengthY(Number length) {
		return setLengthByAxis(Axis.Y, length);
	}

	public T setLengthZ(Number length) {
		return setLengthByAxis(Axis.Z, length);
	}

	/**
	 * Sets the {@link #parent}.
	 * 
	 * @param parent the {@link #parent} to set.
	 */
	public T setParent(T parent) {
		return setParent(parent, false);
	}

	public T setParent(T parent, boolean checkRoots) {

		if (parent == this) {
			throw new Avuilder4jRuntimeException("A cuboid cannot be its own parent.");
		}

		if (checkRoots && parent != null) {
			List<T> rootChain = getRootChain();
			T thisRoot = rootChain.get(rootChain.size() - 1);

			if (thisRoot != parent.getRoot()) {
				for (int i = rootChain.size() - 1; i > 0; i--) {
					rootChain.get(i).setParent(rootChain.get(i - 1));
				}
			}
		}

		this.parent = parent;
		return chain();
	}

	public T setXL(Double XL) {
		getAxisEndsX().setLowerEnd(XL);
		return chain();
	}

	public T setXU(Double XU) {
		getAxisEndsX().setUpperEnd(XU);
		return chain();
	}

	public T setYL(Double YL) {
		getAxisEndsY().setLowerEnd(YL);
		return chain();
	}

	public T setYU(Double YU) {
		getAxisEndsY().setUpperEnd(YU);
		return chain();
	}

	public T setZL(Double ZL) {
		getAxisEndsZ().setLowerEnd(ZL);
		return chain();
	}

	public T setZU(Double ZU) {
		getAxisEndsZ().setUpperEnd(ZU);
		return chain();
	}

	@Override
	public String toString() {

		String parentSring = null;
		if (parent != null)
			parentSring = "[id=" + parent.getIndex() + "]";

		StringBuilder builder = new StringBuilder();
		builder.append("Cuboid [");
		builder.append(toStringHeader());
		builder.append(", ");
		builder.append(toStringBodyCuboid());
		builder.append("]");
		return builder.toString();
	}

	public String toStringBodyCuboid() {
		StringBuilder builder = new StringBuilder();
		builder.append("lengths=");
		builder.append(getLengths());
		builder.append(", volCuboid=");
		builder.append(getVolumeCuboid());
		builder.append(", center=");
		builder.append(getCenter());
		builder.append(", axisX=");
		builder.append(getAxisEndsX());
		builder.append(", axisY=");
		builder.append(getAxisEndsY());
		builder.append(", axisZ=");
		builder.append(getAxisEndsZ());
		return builder.toString();
	}

	public String toStringHeader() {

		StringBuilder builder = new StringBuilder();
		builder.append("tags=");
		builder.append(getTagator().getTagList());
		builder.append(", index=");
		builder.append(getIndex());
		builder.append(", parent=");
		builder.append(Nullable.m(() -> parent.getIndex()));
		return builder.toString();
	}

	public void validateCuboid() {
		if (!isCuboidDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

}
