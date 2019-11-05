package avuilder4j.spatial.enums;

import avuilder4j.error.AvErrors;

public enum Axis {

	X,
	Y,
	Z;

	public static Axis[] getAxesInvolvedInRotation(Rotation rotation) {
		Axis[] axes = new Axis[2];
		switch (rotation) {
		case ROTATION_X:
		case ROTATION_X_INVERSE:
			axes[0] = Y;
			axes[1] = Z;
			break;
		case ROTATION_Y:
		case ROTATION_Y_INVERSE:
			axes[0] = X;
			axes[1] = Z;
			break;
		case ROTATION_Z:
		case ROTATION_Z_INVERSE:
			axes[0] = Z;
			axes[1] = Y;
			break;
		default:
			throw new IllegalArgumentException(AvErrors.ROTATION_NOT_RECOGNIZED);
		}
		return axes;
	}

	public static Axis getAxisIdByFaceId(Face faceId) {
		switch (faceId) {
		case FACE_WALL_XU:
		case FACE_WALL_XL:
			return X;
		case FACE_WALL_YU:
		case FACE_WALL_YL:
			return Y;
		case FACE_WALL_ZU:
		case FACE_WALL_ZL:
			return Z;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

}
