package avuilder4j.utils;

import java.util.ArrayList;

import avuilder4j.entities.dimensional.AxisEnds;
import avuilder4j.entities.dimensional.Cuboid;
import avuilder4j.error.AvErrors;

public class AvU {

	public static int[] getFacesIdsByAxisId(int axisId) {
		switch (axisId) {
		case AvK.AXIS_X:
			return new int[] { Cuboid.FACE_WALL_XL, Cuboid.FACE_WALL_XU };
		case AvK.AXIS_Y:
			return new int[] { Cuboid.FACE_WALL_YL, Cuboid.FACE_WALL_YU };
		case AvK.AXIS_Z:
			return new int[] { Cuboid.FACE_WALL_ZL, Cuboid.FACE_WALL_ZU };
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public static int getAxisIdByFaceId(int faceId) {
		switch (faceId) {
		case Cuboid.FACE_WALL_XU:
		case Cuboid.FACE_WALL_XL:
			return AvK.AXIS_X;
		case Cuboid.FACE_WALL_YU:
		case Cuboid.FACE_WALL_YL:
			return AvK.AXIS_Y;
		case Cuboid.FACE_WALL_ZU:
		case Cuboid.FACE_WALL_ZL:
			return AvK.AXIS_Z;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

	public static int[] getAxesIdsOfLengthsInvolvedInRotation(int rotationId) {

		int[] axes = new int[2];
		switch (rotationId) {
		case AvK.ROTATION_X:
		case AvK.ROTATION_X_INVERSE:
			axes[0] = AvK.AXIS_Y;
			axes[1] = AvK.AXIS_Z;
			break;
		case AvK.ROTATION_Y:
		case AvK.ROTATION_Y_INVERSE:
			axes[0] = AvK.AXIS_X;
			axes[1] = AvK.AXIS_Z;
			break;
		case AvK.ROTATION_Z:
		case AvK.ROTATION_Z_INVERSE:
			axes[0] = AvK.AXIS_Z;
			axes[1] = AvK.AXIS_Y;
			break;
		default:
			throw new IllegalArgumentException(AvErrors.ROTATION_NOT_RECOGNIZED);
		}
		return null;
	}

	public static int getEndIdByFaceId(int faceId) {
		switch (faceId) {
		case Cuboid.FACE_WALL_XU:
		case Cuboid.FACE_WALL_YU:
		case Cuboid.FACE_WALL_ZU:
			return AxisEnds.END_UPPER;
		case Cuboid.FACE_WALL_XL:
		case Cuboid.FACE_WALL_YL:
		case Cuboid.FACE_WALL_ZL:
			return AxisEnds.END_LOWER;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

	public static int[] findMissingIds(int[] ids, int[] allIds) {
		ArrayList<Integer> missed = new ArrayList<Integer>();
		for (int id : ids) {
			boolean found = false;
			for (int idRef : allIds) {
				if (id == idRef)
					found = true;
			}
			if (!found)
				missed.add(id);
		}

		int[] array = new int[missed.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = missed.get(i);
		}
		return array;
	}

}
