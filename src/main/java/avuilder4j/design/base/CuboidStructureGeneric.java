package avuilder4j.design.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.enums.End;
import avuilder4j.design.enums.Face;
import avuilder4j.design.sub.dimensional.AxisEnds;
import avuilder4j.design.sub.dimensional.Point;
import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.error.AvErrors;
import avuilder4j.error.AvValidations;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.java.NumberUtils;
import avuilder4j.util.java.Tagable;
import avuilder4j.util.java.Tagator;

@SuppressWarnings("rawtypes")
public abstract class CuboidStructureGeneric<B extends CuboidGeneric, S extends CuboidStructureGeneric> extends ArrayList<B> implements Serializable, Tagable, Chainable<S> {
	private static final long serialVersionUID = 3084475335938461639L;

	protected Tagator tagsAdministrator = new Tagator();

	public CuboidStructureGeneric() {
		super();
	}

	public CuboidStructureGeneric(Collection<? extends B> c) {
		super(c);
	}

	public CuboidStructureGeneric(int initialCapacity) {
		super(initialCapacity);
	}

	public void escalate(double ratio) {
		escalate(ratio, (Axis[]) null);
	}

	public void escalate(double ratioX, double ratioY, double ratioZ) {
		AvValidations.ratios(ratioX, ratioY, ratioZ);

		for (B cuboid : this) {
			cuboid.validateCuboid();
		}

		for (B cuboid : this) {
			cuboid.getAxisEndsX().escalateRelative(ratioX);
			cuboid.getAxisEndsY().escalateRelative(ratioY);
			cuboid.getAxisEndsZ().escalateRelative(ratioZ);
		}
	}

	public B addBlock(B block) {
		super.add(block);
		return block;
	}

	public S addBlockAndChain(B block) {
		super.add(block);
		return chain();
	}

	public S addBlocks(@SuppressWarnings("unchecked") B... blocks) {
		if (blocks != null)
			for (B b : blocks) {
				add(b);
			}
		return chain();
	}

	public S addBlocks(Collection<B> blocks) {
		super.addAll(blocks);
		return chain();
	}

	public void escalate(double ratio, Axis... axesIds) {
		if (axesIds == null || axesIds.length == 0) {
			axesIds = Axis.values();
		}
		AvValidations.ratios(ratio);
		AvValidations.validateAxesRepetition(axesIds);

		for (B cuboid : this) {
			cuboid.validateCuboid();
		}

		for (B cuboid : this) {
			for (Axis axisId : axesIds) {
				cuboid.getAxisEnds(axisId).escalateRelative(ratio);
			}
		}
	}

	public void escalateByVolumeCuboid(double finalVolume) {
		escalateByVolumeCuboid(finalVolume, (Axis[]) null);
	}

	public void escalateByVolumeCuboid(double finalVolume, Axis... axes) {
		AvValidations.volumes(finalVolume);
		if (axes == null || axes.length == 0) {
			axes = Axis.values();
		}
		AvValidations.validateAxesRepetition(axes);

		for (B cuboid : this) {
			cuboid.validateCuboid();
		}
		double currentVol = 0;
		for (B cuboid : this) {
			currentVol += cuboid.getVolumeCuboid();
		}

		double ratio;
		switch (axes.length) {
		case 0:
		case 3:
			ratio = Math.cbrt(finalVolume / currentVol);
			break;
		case 2:
			ratio = Math.sqrt(finalVolume / currentVol);
			break;
		case 1:
			ratio = finalVolume / currentVol;
			break;
		default:
			throw new Avuilder4jRuntimeException(AvErrors.AXIS_AMOUNT);
		}
		escalate(ratio, axes);
	}

	public B findByIndex(Integer index) {
		return this.stream().filter(b -> Nullable.m(() -> b.getIndex().equals(index), false)).findFirst().orElse(null);
	}

	public void rotate(Axis rotationAxis) {
		rotate(rotationAxis, 1);
	}

	public void rotate(Axis rotationAxis, int times) {
		rotate(rotationAxis, times, false);
	}

	public void rotate(Axis rotationAxis, int times, boolean inverse) {
		if (times < 0)
			throw new IllegalArgumentException("Rotation times cannot be lower than 0.");

		double grades = 90 * times;

		if (inverse) {
			grades = NumberUtils.negate(grades);
		}

		double cos = NumberUtils.round(Math.cos(Math.toRadians(grades)), 3);
		double sin = NumberUtils.round(Math.sin(Math.toRadians(grades)), 3);
		double sinN = NumberUtils.negate(sin);

		Map<Axis, Map<Axis, Double>> rotationMatrix = new HashMap<Axis, Map<Axis, Double>>();
		Map<Axis, Double> matrixRowAxis1 = new HashMap<Axis, Double>();
		Map<Axis, Double> matrixRowAxis2 = new HashMap<Axis, Double>();

		List<Axis> axesRotating = Axis.getAxesInvolvedInRotation(rotationAxis);
		Axis axisRotating1 = axesRotating.get(0);
		Axis axisRotating2 = axesRotating.get(1);

		matrixRowAxis1.put(axisRotating1, cos);
		matrixRowAxis1.put(axisRotating2, sinN);
		matrixRowAxis2.put(axisRotating1, sin);
		matrixRowAxis2.put(axisRotating2, cos);
		rotationMatrix.put(axisRotating1, matrixRowAxis1);
		rotationMatrix.put(axisRotating2, matrixRowAxis2);

		for (B block : this) {

			Map<Axis, AxisEnds> endsCopy = new HashMap<>();
			for (Axis axis : axesRotating) {
				endsCopy.put(axis, block.getAxisEnds(axis).getCopy());
				block.setAxisEnds(axis, new AxisEnds());
			}
			for (Axis matrixRowAxis : axesRotating) {
				List<Double> resultEnds = new ArrayList<>();
				for (End end : End.values()) {
					double endRotated = 0;
					for (Axis matrixColumnAxis : axesRotating) {
						Double savedEnd = endsCopy.get(matrixColumnAxis).getEnd(end);
						endRotated += savedEnd * rotationMatrix.get(matrixRowAxis).get(matrixColumnAxis);
					}
					resultEnds.add(endRotated);
				}

				Double upper, lower;
				if (resultEnds.get(0) > resultEnds.get(1)) {
					upper = resultEnds.get(0);
					lower = resultEnds.get(1);
				} else {
					upper = resultEnds.get(1);
					lower = resultEnds.get(0);
				}

				block.getAxisEnds(matrixRowAxis).setUpperEnd(upper);
				block.getAxisEnds(matrixRowAxis).setLowerEnd(lower);
			}
		}
	}

	protected Double applyRotationMatrix(Map<Axis, double[]> m, Axis axis, Double value) {
		Double result = null;
		for (int i = 0; i < m.size(); i++) {

		}
		return result;
	}

	public void attach(B attacher, B destinationCuboid, Face destinationFace) {

		if (attacher == destinationCuboid)
			throw new IllegalArgumentException("The destination cuboid can not be the cuboid itself.");
		attacher.validateCuboid();
		destinationCuboid.validateCuboid();

		Point destinationFacePoint = destinationCuboid.getFaceCenter(destinationFace);
		Point originFacePoint = attacher.getFaceCenter(Face.getOpposite(destinationFace));

		Vector vector = new Vector(destinationFacePoint.subV3(originFacePoint));
		for (B b : this) {
			b.validateCuboid();
			b.moveCenterByVector(vector);
		}

		attacher.setParent(destinationCuboid, true);
	}

	public List<B> getBlocks() { return this; }

	public B getLast() { return Nullable.m(() -> get(size() - 1)); }

	public B getPenultimate() { return Nullable.m(() -> get(size() - 2)); }

	@Override
	public Tagator getTagator() { return tagsAdministrator; }

	public double getVolumeCuboid(List<? extends CuboidGeneric> blocks) {
		return sumFromBlocks(B::getVolumeCuboid);
	}

	public Double sumFromBlocks(Function<B, Double> getAttributeFunction) {
		Double r = null;
		for (B block : this) {
			if (getAttributeFunction.apply(block) != null) {
				if (r == null)
					r = 0d;
				r += getAttributeFunction.apply(block);
			}
		}
		return r;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Structure [tags=");
		builder.append(tagsAdministrator.getTagsString());
		for (CuboidGeneric cuboid : this) {
			builder.append("\n\t").append(cuboid);
		}
		builder.append("\n").append("]");
		return builder.toString();
	}

}
