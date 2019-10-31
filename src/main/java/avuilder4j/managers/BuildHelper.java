package avuilder4j.managers;

import java.util.List;

import avuilder4j.entities.dimensional.Cuboid;
import avuilder4j.entities.dimensional.Point;
import avuilder4j.entities.dimensional.Vector;
import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.utils.AvK;
import avuilder4j.utils.AvValidations;

public class BuildHelper {

	public static void escalateStructure(List<Cuboid> cuboids, double ratio, int... axesIds) {
		AvValidations.validateRatios(ratio);
		AvValidations.validateAxesExistance(axesIds);
		AvValidations.validateAxesRepetition(axesIds);
		if (axesIds.length == 0) {
			axesIds = AvK.ALL_AXES;
		}

		for (Cuboid cuboid : cuboids) {
			cuboid.validateCuboid();
		}

		for (Cuboid cuboid : cuboids) {
			for (int axisId : axesIds) {
				cuboid.getAxis(axisId).escalateRelative(ratio);
			}
		}
	}

	public static void escalateStructureByVolume(List<Cuboid> cuboids, double finalVolume, int... axesIds) {
		AvValidations.validateVolumes(finalVolume);
		AvValidations.validateAxesExistance(axesIds);
		AvValidations.validateAxesRepetition(axesIds);
		if (axesIds.length == 0) {
			axesIds = AvK.ALL_AXES;
		}

		for (Cuboid cuboid : cuboids) {
			cuboid.validateCuboid();
		}
		double currentVol = 0;
		for (Cuboid cuboid : cuboids) {
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
		escalateStructure(cuboids, ratio, axesIds);
	}

	public static void move(Cuboid cuboid, Point origen, Point destino) {
		Vector centerToRefDistance = Vector.pointDiff(cuboid.getCenter(), origen);

		cuboid.moveCenterToPoint(destino);
		cuboid.moveCenterByVector(centerToRefDistance);
	}

}
