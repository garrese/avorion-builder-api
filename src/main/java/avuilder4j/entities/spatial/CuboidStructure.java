package avuilder4j.entities.spatial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.utils.AvValidations;
import avuilder4j.values.Spatial;

public class CuboidStructure<T extends Cuboid> extends ArrayList<T> implements Serializable {
	private static final long serialVersionUID = 3084475335938461639L;

	public void escalate(double ratio) {
		escalate(ratio, null);
	}

	public void escalate(double ratioX, double ratioY, double ratioZ) {
		AvValidations.validateRatios(ratioX, ratioY, ratioZ);

		for (Cuboid cuboid : this) {
			cuboid.validateCuboid();
		}

		for (Cuboid cuboid : this) {
			cuboid.getAxisX().escalateRelative(ratioX);
			cuboid.getAxisY().escalateRelative(ratioY);
			cuboid.getAxisZ().escalateRelative(ratioZ);
		}
	}

	public void escalate(double ratio, int... axesIds) {
		if (axesIds == null || axesIds.length == 0) {
			axesIds = Spatial.AXES_LIST;
		}
		AvValidations.validateRatios(ratio);
		AvValidations.validateAxesExistance(axesIds);
		AvValidations.validateAxesRepetition(axesIds);

		for (Cuboid cuboid : this) {
			cuboid.validateCuboid();
		}

		for (Cuboid cuboid : this) {
			for (int axisId : axesIds) {
				cuboid.getAxis(axisId).escalateRelative(ratio);
			}
		}
	}

	public void escalateByVolume(double finalVolume) {
		escalateByVolume(finalVolume, new int[0]);
	}

	public void escalateByVolume(double finalVolume, int... axesIds) {
		AvValidations.validateVolumes(finalVolume);
		AvValidations.validateAxesExistance(axesIds);
		AvValidations.validateAxesRepetition(axesIds);
		if (axesIds.length == 0) {
			axesIds = Spatial.AXES_LIST;
		}

		for (Cuboid cuboid : this) {
			cuboid.validateCuboid();
		}
		double currentVol = 0;
		for (Cuboid cuboid : this) {
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

	public List<T> getByTags(String tags) {
		List<T> r = new ArrayList<T>();
		for (T cuboid : this) {
			if (cuboid.hasTags(tags))
				r.add(cuboid);
		}
		return r;
	}

	public T getByTagsUnique(String tags) {
		for (T cuboid : this) {
			if (cuboid.hasTags(tags))
				return cuboid;
		}
		return null;
	}

	public void rotate(int rotationId) {
		rotate(rotationId, 1);
	}

	public void rotate(int rotationId, int times) {
		AvValidations.validateRotationsExistance(rotationId);
		if (times <= 0)
			throw new IllegalArgumentException("'times' argument can not be lower than 1.");

		int[] axesIds = Spatial.getAxesIdsInvolvedInRotation(rotationId);
		try {
			for (Cuboid cuboid : this) {
				AxisEnds axis0 = cuboid.getAxis(axesIds[0]);
				AxisEnds axis1 = cuboid.getAxis(axesIds[1]);
				axis0.validateAxisEnds();
				axis1.validateAxisEnds();

				AxisEnds axis0Aux = axis0;
				AxisEnds axis1Aux = axis1;
				cuboid.setAxis(axesIds[0], axis1Aux);
				cuboid.setAxis(axesIds[1], axis0Aux);
			}
		} catch (Exception e) {
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED, e);
		}
	}

}
