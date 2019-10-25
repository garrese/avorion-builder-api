package avuilder4j.managers;

import avuilder4j.entities.dimensional.AxisEnds;
import avuilder4j.entities.dimensional.Cuboid;
import avuilder4j.entities.dimensional.Point;
import avuilder4j.entities.dimensional.Vector;
import avuilder4j.error.ACErrors;
import avuilder4j.error.AvuilderCoreRuntimeException;
import avuilder4j.utils.AvK;
import avuilder4j.utils.AvValidations;

public class BuildHelper {

	public static void escalate(Cuboid cuboid, double ratio, int... axesIds) {
		try {
			cuboid.validateCuboid();
			AvValidations.validateRatios(ratio);
			AvValidations.validateAxesExistance(axesIds);
			AvValidations.validateAxesRepetition(axesIds);

			if (axesIds.length == 0) {
				axesIds = AvK.ALL_AXES;
			}

			for (int axisId : axesIds) {
				AxisEnds axis = cuboid.getAxis(axisId);
				for (int endId : AxisEnds.ALL_ENDS) {
					axis.setEnd(endId, axis.getEnd(endId) * ratio);
				}
			}
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(e);
		}
	}

	public static void escalateByVolume(Cuboid cuboid, double finalVolume, int... axesIds) {
		try {
			cuboid.validateCuboid();
			AvValidations.validateVolumes(finalVolume);
			AvValidations.validateAxesExistance(axesIds);
			AvValidations.validateAxesRepetition(axesIds);
			double ratio;
			switch (axesIds.length) {
			case 0:
			case 3:
				ratio = Math.cbrt(finalVolume / cuboid.getVolume());
				break;
			case 2:
				ratio = Math.sqrt(finalVolume / cuboid.getVolume());
				break;
			case 1:
				ratio = finalVolume / cuboid.getVolume();
				break;
			default:
				throw new AvuilderCoreRuntimeException(ACErrors.AXIS_AMOUNT);
			}
			escalate(cuboid, ratio, axesIds);
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(e);
		}
	}

	public static void matchFace(Cuboid cuboid, Cuboid reference, int faceMatching, boolean move) {

		try {
			AvValidations.validateFacesExistance(faceMatching);

			int[] axesIds = new int[2];
			switch (faceMatching) {
			case Cuboid.FACE_WALL_YU:
			case Cuboid.FACE_WALL_YL:
				axesIds[0] = AvK.AXIS_X;
				axesIds[1] = AvK.AXIS_Z;
				break;
			case Cuboid.FACE_WALL_ZU:
			case Cuboid.FACE_WALL_ZL:
				axesIds[0] = AvK.AXIS_X;
				axesIds[1] = AvK.AXIS_Y;
				break;
			case Cuboid.FACE_WALL_XU:
			case Cuboid.FACE_WALL_XL:
				axesIds[0] = AvK.AXIS_Y;
				axesIds[1] = AvK.AXIS_Z;
				break;
			default:
				throw new IllegalArgumentException(ACErrors.FACE_NOT_RECOGNIZED);
			}

			for (int axisId : axesIds) {
				cuboid.getAxis(axisId).validateAxisEnds();
				reference.getAxis(axisId).validateAxisEnds();
				cuboid.getAxis(axisId).setLength(reference.getAxis(axisId).getLength());
			}
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(e);
		}
	}

	public static void move(Cuboid cuboid, Point origen, Point destino) {
		// Vector refsDistance = Point.difference(origen, destino);
		Vector centerToRefDistance = Vector.pointDiff(cuboid.getCenter(), origen);
		System.out.println("centerToRefDistance: " + centerToRefDistance);

		cuboid.moveCenterToPoint(destino);
		cuboid.moveCenterByVector(centerToRefDistance);
	}

	public static void applyCrop() {

	}

}
