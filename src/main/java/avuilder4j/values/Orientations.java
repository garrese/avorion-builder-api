package avuilder4j.values;

import avuilder4j.dtos.Orientation;
import avuilder4j.enums.Axis;
import avuilder4j.error.AvErrors;

public class Orientations {

	public static final Orientation DIRECTIONAL_THRUSTER_X = new Orientation(0, 3);
	public static final Orientation DIRECTIONAL_THRUSTER_Y = new Orientation(2, 0);
	public static final Orientation DIRECTIONAL_THRUSTER_Z = new Orientation(5, 3);
	public static final Orientation GYRO_ARRAY_AROUND_X = new Orientation(1, 2);
	public static final Orientation GYRO_ARRAY_AROUND_Y = new Orientation(2, 0);
	public static final Orientation GYRO_ARRAY_AROUND_Z = new Orientation(5, 2);

	public Orientation getDirectionalThrusterOrienation(Axis axis) {
		switch (axis) {
		case X:
			return DIRECTIONAL_THRUSTER_X;
		case Y:
			return DIRECTIONAL_THRUSTER_Y;
		case Z:
			return DIRECTIONAL_THRUSTER_Z;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public Orientation getGyroArrayOrientation(Axis axis) {
		switch (axis) {
		case X:
			return GYRO_ARRAY_AROUND_X;
		case Y:
			return GYRO_ARRAY_AROUND_Y;
		case Z:
			return GYRO_ARRAY_AROUND_Z;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

}
