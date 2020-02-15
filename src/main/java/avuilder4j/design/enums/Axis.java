package avuilder4j.design.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Axis {

	X,
	Y,
	Z;

	public static List<Axis> getAxesInvolvedInRotation(Axis rotationAxis) {
		return Arrays.stream(Axis.values()).filter((axis) -> !rotationAxis.equals(axis))
				.collect(Collectors.toList());
	}

	public static Axis getAxesInvolvedInRotationPartner(Axis rotationAxis, Axis axis) {
		List<Axis> list = getAxesInvolvedInRotation(rotationAxis);
		if (list.remove(axis)) {
			return list.get(0);
		} else {
			throw new IllegalArgumentException("The axis argument is not an axis involved in the rotation");
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
