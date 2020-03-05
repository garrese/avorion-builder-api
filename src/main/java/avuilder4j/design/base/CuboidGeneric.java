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
	private AxisEnds axisEndsX = new AxisEnds();

	/**
	 * Y axis line.
	 */
	private AxisEnds axisEndsY = new AxisEnds();

	/**
	 * Z axis line.
	 */
	private AxisEnds axisEndsZ = new AxisEnds();

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

		Point destinationFacePoint = destinationCuboid.calcFaceCenter(destinationFace);
		Point originFacePoint = this.calcFaceCenter(Face.getOpposite(destinationFace));

		Vector vector = new Vector(destinationFacePoint).subXyz(originFacePoint);
		this.moveCenterByVector(vector);

		setParent(destinationCuboid);
		return chain();
	}

	/**
	 * Calculates the central point of the cuboid.
	 * 
	 * @return the central point of the cuboid.
	 */
	public Point calcCenter() {
		Point p = new Point();
		p.x = axisEndsX.getCenter();
		p.y = axisEndsY.getCenter();
		p.z = axisEndsZ.getCenter();

		return p;
	}

	public Point calcCorner(Corner corner) {
		Point p = new Point();
		switch (corner) {
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

		return p;
	}

	public Point calcFaceCenter(Face faceId) {
		Point p = null;

		if (isCuboidDefined()) {
			p = calcCenter();
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

	public Lengths calcLengths() {
		return new Lengths(axisEndsX.getLength(), axisEndsY.getLength(), axisEndsZ.getLength());
	}

	public T calcRoot() {
		List<T> chain = calcRootChain();
		return chain.get(chain.size() - 1);
	}

	/**
	 * Si no es circular: result.get(result.size()-1).getParent() == null; si no, no es nulo.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> calcRootChain() {
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

	public Double calcSurfaceArea() {
		Double face1 = axisEndsX.getLength() * axisEndsY.getLength();
		Double face2 = axisEndsX.getLength() * axisEndsZ.getLength();
		Double face3 = axisEndsY.getLength() * axisEndsZ.getLength();
		return 2 * (face1 + face2 + face3);
	}

	public Double calcVolumeCuboid() {
		return axisEndsX.getLength() * axisEndsY.getLength() * axisEndsZ.getLength();
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
		return Objects.equals(axisEndsX, other.axisEndsX) && Objects.equals(axisEndsY, other.axisEndsY) && Objects.equals(axisEndsZ, other.axisEndsZ) && Objects.equals(indexInStructure, other.indexInStructure)
				&& Objects.equals(parent, other.parent)
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

	public T escalateByVolume(double finalVolume, Face[] fixedFaces, Axis[] axes) {
		if (axes == null) {
			axes = Axis.values();
		}
		validateCuboid();
		AvValidations.validateAxesRepetition(axes);

		double ratio;
		switch (axes.length) {
		case 0:
		case 3:
			ratio = Math.cbrt(finalVolume / calcVolumeCuboid());
			break;
		case 2:
			ratio = Math.sqrt(finalVolume / calcVolumeCuboid());
			break;
		case 1:
			ratio = finalVolume / calcVolumeCuboid();
			break;
		default:
			throw new Avuilder4jRuntimeException(AvErrors.AXIS_AMOUNT);
		}
		escalate(ratio, fixedFaces, axes);
		return chain();
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

	public List<AxisEnds> getAxisEndsList() {
		ArrayList<AxisEnds> list = new ArrayList<AxisEnds>();
		list.add(getAxisEndsX());
		list.add(getAxisEndsY());
		list.add(getAxisEndsZ());
		return list;
	}

	public AxisEnds getAxisEndsOrCreate(Axis axis) {
		AxisEnds ends = getAxisEnds(axis);
		if (ends == null)
			ends = new AxisEnds();
		return ends;
	}

	/**
	 * Gets the {@link CuboidGeneric#axisEndsX}.
	 * 
	 * @return the {@link #axisEndsX}.
	 */
	public AxisEnds getAxisEndsX() { return axisEndsX; }

	/**
	 * Gets the {@link #axisEndsY}.
	 * 
	 * @return the {@link #axisEndsY}.
	 */
	public AxisEnds getAxisEndsY() { return axisEndsY; }

	/**
	 * Gets the {@link #axisEndsZ}.
	 * 
	 * @return the {@link #axisEndsZ}.
	 */
	public AxisEnds getAxisEndsZ() { return axisEndsZ; }

	/**
	 * Gets the {@link #indexInStructure}.
	 * 
	 * @return the {@link #indexInStructure}.
	 */
	public Integer getIndex() { return indexInStructure; }

	/**
	 * Gets the {@link #parent}.
	 * 
	 * @return the {@link #parent}.
	 */
	public T getParent() { return parent; }

	@Override
	public Tagator getTagator() { return tagator; }

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
		return Objects.hash(axisEndsX, axisEndsY, axisEndsZ, indexInStructure, parent, tagator);
	}

	public boolean isCuboidDefined() {
		if (axisEndsX == null || axisEndsY == null || axisEndsZ == null) {
			return false;
		}
		for (AxisEnds axis : getAxisEndsList()) {
			if (!axis.isAxisEndsDefined())
				return false;
		}
		return true;
	}

	public boolean isRooted() { return calcRoot() != null ? true : false; }

	public T matchToFace(T reference, Face faceMatching) {
		return matchToFace(reference, faceMatching, true);
	}

	public T matchToFace(T reference, Face faceMatching, boolean attach) {

		Axis[] matchingAxes = new Axis[2];
		switch (faceMatching) {
		case YU:
		case YL:
			matchingAxes[0] = Axis.X;
			matchingAxes[1] = Axis.Z;
			break;
		case ZU:
		case ZL:
			matchingAxes[0] = Axis.X;
			matchingAxes[1] = Axis.Y;
			break;
		case XU:
		case XL:
			matchingAxes[0] = Axis.Y;
			matchingAxes[1] = Axis.Z;
			break;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}

		for (Axis axisId : matchingAxes) {
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
		Vector v = new Vector(destinyRefPoint).subXyz(originRefPoint);
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
			getAxisEnds(axis).moveCenterByVector(vector.getXyzAxis(axis));
		}
		return chain();
	}

	public T moveCenterToPoint(Point point) {
		for (Axis axisId : Axis.values()) {
			getAxisEnds(axisId).moveCenterToPoint(point.getXyzAxis(axisId));
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
	 * Sets the {@link #axisEndsX}.
	 * 
	 * @param lineX the {@link #axisEndsX} to set.
	 */
	public T setAxisEndsX(AxisEnds lineX) {
		this.axisEndsX = lineX;
		return chain();
	}

	/**
	 * Sets the {@link #axisEndsY}.
	 * 
	 * @param lineY the {@link #axisEndsY} to set.
	 */
	public T setAxisEndsY(AxisEnds lineY) {
		this.axisEndsY = lineY;
		return chain();
	}

	/**
	 * Sets the {@link #axisEndsZ}.
	 * 
	 * @param lineZ the {@link #axisEndsZ} to set.
	 */
	public T setAxisEndsZ(AxisEnds lineZ) {
		this.axisEndsZ = lineZ;
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
			Double len = lengths.getXyzAxis(axis);
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
				getAxisEnds(axisId).setLength(lengths.getXyzAxis(axisId), endId);

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
				axisEndsX.setLength(lengths.x);
			if (notFixedY)
				axisEndsY.setLength(lengths.y);
			if (notFixedZ)
				axisEndsZ.setLength(lengths.z);
		}
		return chain();
	}

	public T setLengths(Number xyz) {
		return setLengths(new Lengths().setXyz(xyz));
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
			List<T> rootChain = calcRootChain();
			T thisRoot = rootChain.get(rootChain.size() - 1);

			if (thisRoot != parent.calcRoot()) {
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
		builder.append(Nullable.m(() -> calcLengths()));
		builder.append(", volCuboid=");
		builder.append(Nullable.m(() -> calcVolumeCuboid()));
		builder.append(", center=");
		builder.append(Nullable.m(() -> calcCenter()));
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
