package avuilder4j.util.values;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.enums.Face;
import avuilder4j.design.enums.Rotation;
import avuilder4j.design.sub.dimensional.Orientation;
import avuilder4j.error.AvErrors;
import avuilder4j.util.java.Nullable;

public class Orients {

	public static Orientation getDefault() { return new Orientation(Face.ZU, Face.YU); }

	public static Orientation getByDirectionalThrusterAxisThrust(Axis axis) {
		switch (axis) {
		case X:
			return new Orientation(Face.XU, Face.YU);
		case Y:
			return new Orientation(Face.YL, Face.ZU);
		case Z:
			return new Orientation(Face.ZU, Face.YU);
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public static Orientation getByGyroArrayAxisOfRotation(Axis axis) {
		switch (axis) {
		case X:
			return new Orientation(Face.XU, Face.YU);
		case Y:
			return new Orientation(Face.YL, Face.ZU);
		case Z:
			return new Orientation(Face.ZU, Face.YU);
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public static Orientation getByGyroArrayRotation(Rotation rotation) {
		switch (rotation) {
		case AROUND_X:
		case AROUND_X_INVERSE:
			return new Orientation(Face.XU, Face.YU);
		case AROUND_Y:
		case AROUND_Y_INVERSE:
			return new Orientation(Face.YL, Face.ZU);
		case AROUND_Z:
		case AROUND_Z_INVERSE:
			return new Orientation(Face.ZU, Face.YU);
		default:
			throw new IllegalArgumentException(AvErrors.ROTATION_NOT_RECOGNIZED);
		}
	}

	public static Axis getGyroArrayAxisOfRotationByOrientation(Orientation orientation) {
		return getDefaultAxisOfEffectByOrientation(orientation);
	}

	public static Axis getDirectionalThrusterAxisThrustByOrientation(Orientation orientation) {
		return getDefaultAxisOfEffectByOrientation(orientation);
	}

	protected static Axis getDefaultAxisOfEffectByOrientation(Orientation orientation) {
		return Nullable.m(() -> {
			return Axis.getAxisByFace(orientation.getLook());
		});
	}

}
