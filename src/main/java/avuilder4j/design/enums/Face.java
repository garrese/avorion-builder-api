package avuilder4j.design.enums;

import avuilder4j.error.AvErrors;

public enum Face {

	LX,
	UX,
	LY,
	UY,
	LZ,
	UZ;

	public static Face[] getFacesByAxis(Axis axis) {
		switch (axis) {
		case X:
			return new Face[] { LX, UX };
		case Y:
			return new Face[] { LY, UY };
		case Z:
			return new Face[] { LZ, UZ };
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

}
