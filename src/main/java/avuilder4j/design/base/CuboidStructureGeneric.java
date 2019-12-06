package avuilder4j.design.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.enums.Rotation;
import avuilder4j.design.sub.dimensional.AxisEnds;
import avuilder4j.design.sub.dimensional.Tagable;
import avuilder4j.design.sub.dimensional.TagsAdministrator;
import avuilder4j.error.AvErrors;
import avuilder4j.error.AvValidations;
import avuilder4j.error.Avuilder4jRuntimeException;

@SuppressWarnings("rawtypes")
public class CuboidStructureGeneric<B extends CuboidGeneric> extends ArrayList<B> implements Serializable, Tagable {
	private static final long serialVersionUID = 3084475335938461639L;

	protected TagsAdministrator tagsAdministrator = new TagsAdministrator();

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

	public void escalateByVolumeCuboid(double finalVolume, Axis... axesIds) {
		AvValidations.volumes(finalVolume);
		AvValidations.validateAxesRepetition(axesIds);
		if (axesIds == null || axesIds.length == 0) {
			axesIds = Axis.values();
		}

		for (B cuboid : this) {
			cuboid.validateCuboid();
		}
		double currentVol = 0;
		for (B cuboid : this) {
			currentVol += cuboid.getVolumeCuboid();
		}

		double ratio;
		switch (axesIds.length) {
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
		escalate(ratio, axesIds);
	}

	public void rotate(Rotation rotationId) {
		rotate(rotationId, 1);
	}

	public void rotate(Rotation rotationId, int times) {
		if (times <= 0)
			throw new IllegalArgumentException("'times' argument can not be lower than 1.");

		Axis[] axesIds = Axis.getAxesInvolvedInRotation(rotationId);
		try {
			for (int i = 0; i < times; i++) {
				for (B cuboid : this) {
					AxisEnds axis0 = cuboid.getAxis(axesIds[0]);
					AxisEnds axis1 = cuboid.getAxis(axesIds[1]);
					axis0.validateAxisEnds();
					axis1.validateAxisEnds();

					AxisEnds axis0Aux = axis0;
					AxisEnds axis1Aux = axis1;
					cuboid.setAxis(axesIds[0], axis1Aux);
					cuboid.setAxis(axesIds[1], axis0Aux);
				}
			}
		} catch (Exception e) {
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED, e);
		}
	}

	public List<B> getBlocks() { return this; }

	@Override
	public TagsAdministrator getTagsAdministrator() { return tagsAdministrator; }

	public double getVolumeCuboid(List<? extends CuboidGeneric> blocks) {
		return sumFromBlocks(B::getVolumeCuboid);
	}

	public double sumFromBlocks(Function<B, Double> getAttributeFunction) {
		double r = 0;
		for (B block : this) {
			if (getAttributeFunction.apply(block) != null)
				r += getAttributeFunction.apply(block);
		}
		return r;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CuboidStructureGeneric [tags=");
		builder.append(tagsAdministrator.getTags());
		for (CuboidGeneric cuboid : this) {
			builder.append("\n\t").append(cuboid);
		}
		builder.append("\n").append("]");
		return builder.toString();
	}

}
