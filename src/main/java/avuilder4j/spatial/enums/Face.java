package avuilder4j.spatial.enums;

import avuilder4j.error.AvErrors;

public enum Face {

	FACE_WALL_XL,
	FACE_WALL_XU,
	FACE_WALL_YL,
	FACE_WALL_YU,
	FACE_WALL_ZL,
	FACE_WALL_ZU;

	public static Face[] getFacesByAxis(Axis axis) {
		switch (axis) {
		case X:
			return new Face[] { FACE_WALL_XL, FACE_WALL_XU };
		case Y:
			return new Face[] { FACE_WALL_YL, FACE_WALL_YU };
		case Z:
			return new Face[] { FACE_WALL_ZL, FACE_WALL_ZU };
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

}
