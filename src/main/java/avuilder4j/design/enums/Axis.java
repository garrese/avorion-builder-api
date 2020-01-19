package avuilder4j.design.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Axis {

	X,
	Y,
	Z;

	public static List<Axis> getAxisInvolvedInRotation(Rotation rotation) {
		if (rotation != null)
			return Arrays.stream(Axis.values()).filter((axis) -> !axis.equals(getAxisOfRotation(rotation)))
					.collect(Collectors.toList());
		else
			return null;
	}

	public static Axis getAxisInvolvedInRotationPair(Rotation rotation, Axis axis) {
		List<Axis> list = getAxisInvolvedInRotation(rotation);
		if (list.remove(axis)) {
			return list.get(0);
		} else {
			throw new IllegalArgumentException("The axis argument is not an axis involved in the rotation");
		}
	}

	public static Axis getAxisOfRotation(Rotation rotation) {
		switch (rotation) {
		case AROUND_X:
		case AROUND_X_INVERSE:
			return X;
		case AROUND_Y:
		case AROUND_Y_INVERSE:
			return Y;
		case AROUND_Z:
		case AROUND_Z_INVERSE:
			return Z;
		default:
			return null;
		}
	}

	public static Axis getAxisByFace(Face face) {
		switch (face) {
		case XU:
		case XL:
			return X;
		case YU:
		case YL:
			return Y;
		case ZU:
		case ZL:
			return Z;
		default:
			return null;
		}
	}

}
