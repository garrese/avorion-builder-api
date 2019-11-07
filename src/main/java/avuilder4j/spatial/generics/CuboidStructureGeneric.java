package avuilder4j.spatial.generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.spatial.AxisEnds;
import avuilder4j.spatial.enums.Axis;
import avuilder4j.spatial.enums.Rotation;
import avuilder4j.utils.AvValidations;

@SuppressWarnings("rawtypes")
public class CuboidStructureGeneric<B extends CuboidGeneric> extends ArrayList<B> implements Serializable {
	private static final long serialVersionUID = 3084475335938461639L;

	public void escalate(double ratio) {
		escalate(ratio, (Axis[]) null);
	}

	public void escalate(double ratioX, double ratioY, double ratioZ) {
		AvValidations.validateRatios(ratioX, ratioY, ratioZ);

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
		AvValidations.validateRatios(ratio);
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

	public void escalateByVolume(double finalVolume) {
		escalateByVolume(finalVolume, (Axis[]) null);
	}

	public void escalateByVolume(double finalVolume, Axis... axesIds) {
		AvValidations.validateVolumes(finalVolume);
		AvValidations.validateAxesRepetition(axesIds);
		if (axesIds == null || axesIds.length == 0) {
			axesIds = Axis.values();
		}

		for (B cuboid : this) {
			cuboid.validateCuboid();
		}
		double currentVol = 0;
		for (B cuboid : this) {
			currentVol += cuboid.getVolume();
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

	public List<B> getByTags(String tags) {
		List<B> r = new ArrayList<B>();
		for (B cuboid : this) {
			if (cuboid.hasTags(tags))
				r.add(cuboid);
		}
		return r;
	}

	public B getByTagsUnique(String tags) {
		for (B cuboid : this) {
			if (cuboid.hasTags(tags))
				return cuboid;
		}
		return null;
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

	/**
	 * Gets the {@link #blocks}.
	 * 
	 * @return the {@link #blocks}.
	 */
	public List<B> getBlocks() { return this; }

}
