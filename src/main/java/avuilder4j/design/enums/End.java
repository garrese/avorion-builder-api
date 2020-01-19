package avuilder4j.design.enums;

import avuilder4j.error.AvErrors;

public enum End {

	UPPER,
	LOWER;

	public static End getOppositeEnd(End end) {
		switch (end) {
		case UPPER:
			return LOWER;
		case LOWER:
			return UPPER;
		default:
			throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
		}
	}

	public static End getEndByFace(Face faceId) {
		switch (faceId) {
		case XU:
		case YU:
		case ZU:
			return UPPER;
		case XL:
		case YL:
		case ZL:
			return LOWER;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}
}
