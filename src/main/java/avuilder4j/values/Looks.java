package avuilder4j.values;

import avuilder4j.dtos.TypeLook;
import avuilder4j.enums.Axis;
import avuilder4j.error.AvErrors;

public class Looks {

	public static final TypeLook DIRECTIONAL_THRUSTER_X = new TypeLook(0, 3);
	public static final TypeLook DIRECTIONAL_THRUSTER_Y = new TypeLook(2, 0);
	public static final TypeLook DIRECTIONAL_THRUSTER_Z = new TypeLook(5, 3);
	public static final TypeLook GYRO_ARRAY_AROUND_X = new TypeLook(1, 2);
	public static final TypeLook GYRO_ARRAY_AROUND_Y = new TypeLook(2, 0);
	public static final TypeLook GYRO_ARRAY_AROUND_Z = new TypeLook(5, 2);

	public TypeLook getDirectionalThrusterLookByAxis(Axis axis) {
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

	public TypeLook getGyroArrayLookByAxis(Axis axis) {
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
