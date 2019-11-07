package avuilder4j.enums;

import avuilder4j.error.AvErrors;

public enum End {

	UPPER,
	LOWER;

	public static End getOppositeEndId(End end) {
		switch (end) {
		case UPPER:
			return LOWER;
		case LOWER:
			return UPPER;
		default:
			throw new IllegalArgumentException(AvErrors.END_NOT_RECOGNIZED);
		}
	}

	public static End getEndIdByFaceId(Face faceId) {
		switch (faceId) {
		case UX:
		case UY:
		case UZ:
			return UPPER;
		case LX:
		case LY:
		case LZ:
			return LOWER;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}
}
