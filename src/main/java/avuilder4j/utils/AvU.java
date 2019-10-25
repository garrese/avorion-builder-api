package avuilder4j.utils;

import avuilder4j.entities.dimensional.AxisEnds;
import avuilder4j.entities.dimensional.Cuboid;
import avuilder4j.error.ACErrors;

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
			throw new IllegalArgumentException(ACErrors.AXIS_NOT_RECOGNIZED);
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
			throw new IllegalArgumentException(ACErrors.FACE_NOT_RECOGNIZED);
		}
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
			throw new IllegalArgumentException(ACErrors.FACE_NOT_RECOGNIZED);
		}
	}

}
