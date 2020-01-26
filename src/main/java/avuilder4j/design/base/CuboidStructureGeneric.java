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
import avuilder4j.design.enums.Rotation;
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
			cuboid.getAxisX().escalateRelative(ratioX);
			cuboid.getAxisY().escalateRelative(ratioY);
			cuboid.getAxisZ().escalateRelative(ratioZ);
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
				cuboid.getAxis(axisId).escalateRelative(ratio);
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

	public void rotate(Rotation rotationId) {
		rotate(rotationId, 1);
	}

	@SuppressWarnings("incomplete-switch")
	public void rotate(Rotation rotation, int times) {
		if (times < 0)
			throw new IllegalArgumentException("Rotation times cannot be lower than 0.");

//		double[][] rx = new double[3][3];
//		rx[0] = new double[] { 1, 0, 0 };
//		rx[1] = new double[] { 0, cos, sinN };
//		rx[2] = new double[] { 0, sin, cos };
//
//		double[][] ry = new double[3][3];
//		ry[0] = new double[] { cos, 0, sin };
//		ry[1] = new double[] { 0, 1, 0 };
//		ry[2] = new double[] { sinN, 0, cos };
//
//		double[][] rz = new double[3][3];
//		rz[0] = new double[] { cos, sinN, 0 };
//		rz[1] = new double[] { sin, cos, 0 };
//		rz[2] = new double[] { 0, 0, 1 };

//		Map<Axis, double[]> mx = new HashMap<>();
//		mx.put(Axis.X, new double[] { 1, 0, 0 });
//		mx.put(Axis.Y, new double[] { 0, cos, sinN });
//		mx.put(Axis.Z, new double[] { 0, sin, cos });
//
//		Map<Axis, double[]> my = new HashMap<>();
//		my.put(Axis.X, new double[] { cos, 0, sin });
//		my.put(Axis.Y, new double[] { 0, 1, 0 });
//		my.put(Axis.Z, new double[] { sinN, 0, cos });
//
//		Map<Axis, double[]> mz = new HashMap<>();
//		mz.put(Axis.X, new double[] { cos, sinN, 0 });
//		mz.put(Axis.Y, new double[] { sin, cos, 0 });
//		mz.put(Axis.Z, new double[] { 0, 0, 1 });

//		Map<Axis, double[]> mx = new HashMap<>();
//		mx.put(Axis.Y, new double[] { cos, sinN });
//		mx.put(Axis.Z, new double[] { sin, cos });
//		
//		Map<Axis, double[]> my = new HashMap<>();
//		my.put(Axis.X, new double[] { cos, sin });
//		my.put(Axis.Z, new double[] { sinN, cos });
//
//		Map<Axis, double[]> mz = new HashMap<>();
//		mz.put(Axis.X, new double[] { cos, sinN });
//		mz.put(Axis.Y, new double[] { sin, cos });

		double grades = 90 * times;

		switch (rotation) {
		case AROUND_X_INVERSE:
		case AROUND_Y_INVERSE:
		case AROUND_Z_INVERSE:
			grades = NumberUtils.negate(grades);
		}

		double cos = NumberUtils.round(Math.cos(Math.toRadians(grades)), 3);
		double sin = NumberUtils.round(Math.sin(Math.toRadians(grades)), 3);
		double cosN = NumberUtils.negate(cos);
		double sinN = NumberUtils.negate(sin);

		Map<Axis, Map<Axis, Double>> matrix = new HashMap<Axis, Map<Axis, Double>>();
		Map<Axis, Double> mx1 = new HashMap<Axis, Double>();
		Map<Axis, Double> mx2 = new HashMap<Axis, Double>();

		List<Axis> axesRotating = Axis.getAxisInvolvedInRotation(rotation);

		switch (rotation) {

		case AROUND_X:
			mx1.put(Axis.Y, cos);
			mx1.put(Axis.Z, sinN);
			mx2.put(Axis.Y, sin);
			mx2.put(Axis.Z, cos);
			matrix.put(Axis.Y, mx1);
			matrix.put(Axis.Z, mx2);
			break;

		case AROUND_Y:
			mx1.put(Axis.X, cos);
			mx1.put(Axis.Z, sinN);
			mx2.put(Axis.X, sin);
			mx2.put(Axis.Z, cos);
			matrix.put(Axis.X, mx1);
			matrix.put(Axis.Z, mx2);
			break;

		case AROUND_Z:
			mx1.put(Axis.X, cos);
			mx1.put(Axis.Y, sinN);
			mx2.put(Axis.X, sin);
			mx2.put(Axis.Y, cos);
			matrix.put(Axis.X, mx1);
			matrix.put(Axis.Y, mx2);
			break;

		}

		for (B b : this) {

			Map<Axis, AxisEnds> endsCopy = new HashMap<>();
			for (Axis axis : axesRotating) {
				endsCopy.put(axis, b.getAxis(axis).getCopy());
				b.setAxis(axis, new AxisEnds());
			}
			for (Axis matrixRowAxis : axesRotating) {
				List<Double> resultEnds = new ArrayList<>();
				for (End end : End.values()) {
					double endRotated = 0;
					for (Axis matrixLineAxis : axesRotating) {
						Double savedEnd = endsCopy.get(matrixLineAxis).getEnd(end);
						endRotated += savedEnd * matrix.get(matrixRowAxis).get(matrixLineAxis);
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

				b.getAxis(matrixRowAxis).setEnd(End.UPPER, upper);
				b.getAxis(matrixRowAxis).setEnd(End.LOWER, lower);
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

		Vector vector = Vector.pointDiff(destinationFacePoint, originFacePoint);
		for (B b : this) {
			b.validateCuboid();
			b.moveCenterByVector(vector);
		}

		attacher.setParent(destinationCuboid, true);
	}

	public List<B> getBlocks() { return this; }

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
