package avuilder4j.spatial.enums;

import avuilder4j.error.AvErrors;

public enum End {

	END_UPPER,
	END_LOWER;

	public static End getOppositeEndId(End end) {
		switch (end) {
		case END_UPPER:
			return END_LOWER;
		case END_LOWER:
			return END_UPPER;
		default:
			throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
		}
	}

	public static End getEndIdByFaceId(Face faceId) {
		switch (faceId) {
		case FACE_WALL_XU:
		case FACE_WALL_YU:
		case FACE_WALL_ZU:
			return END_UPPER;
		case FACE_WALL_XL:
		case FACE_WALL_YL:
		case FACE_WALL_ZL:
			return END_LOWER;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}
}
