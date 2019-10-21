package avuilder.core.managers;


import avuilder.core.entities.dimensional.AxisEnds;
import avuilder.core.entities.dimensional.Cuboid;
import avuilder.core.entities.dimensional.Point;
import avuilder.core.entities.dimensional.Vector;
import avuilder.core.error.ACErrors;
import avuilder.core.error.AvuilderCoreRuntimeException;
import avuilder.core.utils.ACK;
import avuilder.core.utils.ACValidations;

public class BuildHelper {

	public static void escalate(Cuboid cuboid, double ratio, int... axesIds) {
		try {
			cuboid.validate();
			ACValidations.validateRatios(ratio);
			ACValidations.validateAxes(axesIds);

			if (axesIds.length == 0) {
				axesIds = ACK.ALL_AXES;
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

	public static void escalateByVolume(Cuboid cuboid, double finalVolume, int... axes) {
		try {
			cuboid.validate();
			ACValidations.validateVolumes(finalVolume);
			ACValidations.validateAxes(axes);
			double ratio;
			switch (axes.length) {
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
			escalate(cuboid, ratio, axes);
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(e);
		}
	}

	public static void matchFace(Cuboid cuboid, Cuboid reference, int faceMatching, boolean move) {

		try {
			ACValidations.validateFacesExistance(faceMatching);

			int[] axesIds = new int[2];
			switch (faceMatching) {
			case Cuboid.FACE_WALL_YU:
			case Cuboid.FACE_WALL_YL:
				axesIds[0] = ACK.AXIS_X;
				axesIds[1] = ACK.AXIS_Z;
				break;
			case Cuboid.FACE_WALL_ZU:
			case Cuboid.FACE_WALL_ZL:
				axesIds[0] = ACK.AXIS_X;
				axesIds[1] = ACK.AXIS_Y;
				break;
			case Cuboid.FACE_WALL_XU:
			case Cuboid.FACE_WALL_XL:
				axesIds[0] = ACK.AXIS_Y;
				axesIds[1] = ACK.AXIS_Z;
				break;
			default:
				throw new IllegalArgumentException(ACErrors.FACE_NOT_RECOGNIZED);
			}

			for (int axisId : axesIds) {
				cuboid.getAxis(axisId).validate();
				reference.getAxis(axisId).validate();
				cuboid.getAxis(axisId).setLength(reference.getAxis(axisId).getLength());
			}
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException(e);
		}
	}

	public static void move(Cuboid cuboid, Point origen, Point destino) {
		// Vector refsDistance = Point.difference(origen, destino);
		Vector centerToRefDistance = Point.pointDiff(cuboid.getCenter(), origen);
		System.out.println("centerToRefDistance: " + centerToRefDistance);

		cuboid.moveCenterToPoint(destino);
		cuboid.moveCenterByVector(centerToRefDistance);
	}

	public static void applyCrop() {

	}

}
