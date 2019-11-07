package avuilder4j.enums;

import avuilder4j.error.AvErrors;

public enum Axis {

	X,
	Y,
	Z;

	public static Axis[] getAxesInvolvedInRotation(Rotation rotation) {
		Axis[] axes = new Axis[2];
		switch (rotation) {
		case AROUND_X:
		case AROUND_X_INVERSE:
			axes[0] = Y;
			axes[1] = Z;
			break;
		case AROUND_Y:
		case AROUND_Y_INVERSE:
			axes[0] = X;
			axes[1] = Z;
			break;
		case AROUND_Z:
		case AROUND_Z_INVERSE:
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
		case UX:
		case LX:
			return X;
		case UY:
		case LY:
			return Y;
		case UZ:
		case LZ:
			return Z;
		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

}
