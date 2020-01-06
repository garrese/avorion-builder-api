package avuilder4j.design.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Axis {

	X(0),
	Y(1),
	Z(2);

	private int index;

	private Axis(int index) {
		this.index = index;
	}

	public int getIndex() { return index; }

	public static List<Axis> getAxesInvolvedInCuboidRotation(Rotation rotation) {
		if (rotation != null)
			return Arrays.stream(Axis.values()).filter((axis) -> !axis.equals(getAxisOfRotation(rotation))).collect(Collectors.toList());
		else
			return null;
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
